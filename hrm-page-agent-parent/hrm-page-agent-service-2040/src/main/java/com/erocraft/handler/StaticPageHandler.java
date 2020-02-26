package com.erocraft.handler;


import com.alibaba.fastjson.JSONObject;
import com.erocraft.client.FastDfsClient;
import com.erocraft.config.RabbitMqConfig;
import com.erocraft.util.RabbitMqConstants;
import com.rabbitmq.client.Channel;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author 14179
 */
@Component
public class StaticPageHandler {
    @RabbitListener(queues = RabbitMqConfig.QUEUE_PAGE_STATIC)
    public void handle(String msg, Message message, Channel channel){
        System.out.println("接收消息："+msg);
        Map map = JSONObject.parseObject(msg, Map.class);
        Integer fileSysType = (Integer) map.get(RabbitMqConstants.FILE_SYS_TYPE);
        String pageUrl = (String) map.get(RabbitMqConstants.PAGE_URL);
        String physicalPath = (String) map.get(RabbitMqConstants.PHYSICAL_PATH);
        switch(fileSysType){
            case 0 :
                downloadAndCopyOfFastDfs(pageUrl,physicalPath);
                break;
            case 1 :
                downloadAndCopyOfHdfs(pageUrl,physicalPath);
                break;
        }
    }

    @Autowired
    private FastDfsClient fastDfsClient;
    private void downloadAndCopyOfFastDfs(String pageUrl, String physicalPath) {
        InputStream is = null;
        FileOutputStream os = null;
        try{
            Response response = fastDfsClient.download(pageUrl);
            is = response.body().asInputStream();
            System.out.println(physicalPath);
            os = new FileOutputStream(physicalPath);
            IOUtils.copy(is,os) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {

            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {

                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    //@TODO hdfs以后支持
    private void downloadAndCopyOfHdfs(String pageUrl, String physicalPath) {
    }
}
