package com.erocraft.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.client.FastDfsClient;
import com.erocraft.client.RedisClient;
import com.erocraft.domain.PageConfig;
import com.erocraft.domain.Pager;
import com.erocraft.mapper.PageConfigMapper;
import com.erocraft.mapper.PagerMapper;
import com.erocraft.mapper.SiteMapper;
import com.erocraft.service.IPageConfigService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.RabbitMqConstants;
import com.erocraft.utils.VelocityUtils;
import com.erocraft.utils.ZipUtil;
import feign.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-24
 */
@Service
public class PageConfigServiceImpl extends ServiceImpl<PageConfigMapper, PageConfig> implements IPageConfigService {

    @Autowired
    private PagerMapper pagerMapper;

    @Autowired
    private FastDfsClient fastDfsClient;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private PageConfigMapper pageConfigMapper;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public void staticPage(String pageName, String dataKey){
        List<Pager> pagers = pagerMapper.selectList(new EntityWrapper<Pager>().eq("name", pageName));
        if (pagers == null || pagers.size()<1) return;
        Pager pager = pagers.get(0);
        String templateUrl = pager.getTemplateUrl();
        String tmpdir=System.getProperty("java.io.tmpdir");
        String unzipDir = tmpdir+"/temp/";
        String tempatePath = getTemplatePath(pager, templateUrl, tmpdir, unzipDir);
        Map model = getMapData(dataKey, unzipDir);
        String staticPath = tempatePath+".html";
        System.out.println("静态化页面地址："+staticPath);
        VelocityUtils.staticByTemplate(model,tempatePath,staticPath);
        AjaxResult ajaxResult = fastDfsClient.upload(new CommonsMultipartFile(createFileItem(new File(staticPath))));
        PageConfig pageConfig = addPageConfig(dataKey, pager, templateUrl, ajaxResult);
        String routingKey = siteMapper.selectById(pager.getSiteId()).getSn();
        Map<String,Object> params = new HashMap<>();
        params.put(RabbitMqConstants.FILE_SYS_TYPE,pageConfig.getDfsType());
        params.put(RabbitMqConstants.PAGE_URL,pageConfig.getPageUrl());
        params.put(RabbitMqConstants.PHYSICAL_PATH,pageConfig.getPhysicalPath());
        rabbitTemplate.convertAndSend(RabbitMqConstants.EXCHANGE_TOPICS_PAGE,routingKey,JSONObject.toJSONString(params));

    }

    private String getTemplatePath(Pager pager, String templateUrl, String tmpdir, String unzipDir) {
        FileOutputStream os = null;
        InputStream inputStream = null;
        try{
            Response response = fastDfsClient.download(templateUrl);
            inputStream = response.body().asInputStream();
            String unzipFile = tmpdir+"/temp.zip";
            os = new FileOutputStream(unzipFile);
            IOUtils.copy(inputStream, os);
            System.out.println("下载路径:" + unzipFile);
            ZipUtil.unzip(unzipFile,unzipDir);
            System.out.println("解压路径:" + unzipDir);
            String tempatePath = unzipDir+"/"+pager.getTemplateName();
            System.out.println("模板路径:" + tempatePath);
            return tempatePath;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    private Map getMapData(String dataKey, String unzipDir) {
        String jsonStr = redisClient.get(dataKey);
        Map model = JSONObject.parseObject(jsonStr,Map.class);
        model.put("staticRoot",unzipDir);
        return model;
    }

    private PageConfig addPageConfig(String dataKey, Pager pager, String templateUrl, AjaxResult ajaxResult) {
        PageConfig pageConfig = new PageConfig();
        pageConfig.setTemplateUrl(templateUrl);
        pageConfig.setTemplateName(pager.getTemplateName());
        pageConfig.setDataKey(dataKey);
        pageConfig.setPhysicalPath(pager.getPhysicalPath());
        pageConfig.setDfsType(0L);
        pageConfig.setPageUrl((String) ajaxResult.getResultObj());
        pageConfig.setPageId(pager.getId());
        pageConfigMapper.insert(pageConfig);
        return pageConfig;
    }

    private FileItem createFileItem(File file) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        FileItem item = factory.createItem("file", "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

}
