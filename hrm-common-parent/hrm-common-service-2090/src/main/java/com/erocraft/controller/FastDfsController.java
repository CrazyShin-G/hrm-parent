package com.erocraft.controller;


import com.erocraft.util.AjaxResult;
import com.erocraft.util.FastDfsApiOpr;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import feign.Response;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/fastDfs")
public class FastDfsController {

    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult upload(@RequestPart(required = true,value = "file")MultipartFile file){
        try {
            System.out.println(file.getOriginalFilename() + ":" + file.getSize());
            String originalFilename = file.getOriginalFilename();
            // xxx.jpg
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            System.out.println(extName);
            String filePath =  FastDfsApiOpr.upload(file.getBytes(), extName);
            return AjaxResult.me().setResultObj(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败"+e.getMessage());
        }
    }

    @GetMapping
    void download(@RequestParam(required = true,value = "path") String path, HttpServletResponse response){
        ByteArrayInputStream bis = null;
        ServletOutputStream outputStream = null;
        try{
            String pathTmp = path.substring(1);
            String groupName =  pathTmp.substring(0, pathTmp.indexOf("/"));
            String remotePath = pathTmp.substring(pathTmp.indexOf("/")+1);
            System.out.println(groupName);
            System.out.println(remotePath);
            System.out.println(1);
            byte[] data = FastDfsApiOpr.download(groupName, remotePath);

            bis = new ByteArrayInputStream(data);
            System.out.println(3);
            outputStream = response.getOutputStream();
            IOUtils.copy(bis,outputStream);
            System.out.println(4);
        }catch (Exception e
        ){
            System.out.println(5);
            e.printStackTrace();
        }
        finally {
        }
    }

    @DeleteMapping
    public AjaxResult del(@RequestParam(required = true,value = "path") String path){
        String pathTmp = path.substring(1);
        String groupName =  pathTmp.substring(0, pathTmp.indexOf("/"));
        String remotePath = pathTmp.substring(pathTmp.indexOf("/")+1);
        System.out.println(groupName);
        System.out.println(remotePath);
        FastDfsApiOpr.delete(groupName, remotePath);
        return  AjaxResult.me();
    }
}
