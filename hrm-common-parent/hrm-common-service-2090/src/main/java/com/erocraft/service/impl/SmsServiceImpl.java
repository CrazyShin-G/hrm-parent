package com.erocraft.service.impl;


import com.erocraft.service.ISmsService;
import com.erocraft.util.AjaxResult;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;

@Service
public class SmsServiceImpl implements ISmsService {

    @Override
    public AjaxResult sendSmsCode(Map<String, String> params)  {

        PostMethod post = null;
        try {
            HttpClient client = new HttpClient();
            post = new PostMethod("http://utf8.api.smschinese.cn");
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            List<NameValuePair> datas = new ArrayList<>();
            for (String key : params.keySet()) {
                datas.add( new NameValuePair(key, params.get(key) ));
            }
            NameValuePair[] data = datas.toArray(new NameValuePair[]{});
            post.setRequestBody(data);

            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
            System.out.println("statusCode:"+statusCode);
            System.out.println(result);
            if (statusCode==200){
                return AjaxResult.me().setMessage(result);
            }else{
                return AjaxResult.me().setSuccess(false).setMessage(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
        return null;
    }
}
