package com.erocraft.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.erocraft.client.RedisClient;
import com.erocraft.client.SmsClient;
import com.erocraft.domain.Sso;
import com.erocraft.mapper.SsoMapper;
import com.erocraft.service.IVerifycodeService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.StrUtils;
import com.erocraft.util.VerifyCodeUtils;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class VerifycodeServiceImpl implements IVerifycodeService {

    @Autowired
    private SsoMapper ssoMapper;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SmsClient smsClient;
    @Override
    public String getImageCode(String imageCodeKey) {
        try{
            String imageCode = VerifyCodeUtils.generateVerifyCode(6).toLowerCase();
            System.out.println(imageCode);
            redisClient.addForTime(imageCodeKey,imageCode,60*5);
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            VerifyCodeUtils.outputImage(100, 40, data, imageCode);
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;

    }

    @Override
    public AjaxResult sendSmsCode(Map<String, String> params) {
        String mobile = params.get("mobile");
        String imageCode = params.get("imageCode");
        String imageCodeKey = params.get("imageCodeKey");
        String imgCodeforReids = redisClient.get(imageCodeKey);
        if (imgCodeforReids==null || !imageCode.equals(imgCodeforReids)){
            return AjaxResult.me().setSuccess(false).setMessage("验证码不正确!");
        }
        if (mobile==null){
            return AjaxResult.me().setSuccess(false).setMessage("请输入正确的手机号码");
        }
        List<Sso> ssos = ssoMapper.selectList(new EntityWrapper<Sso>().eq("phone", mobile));
        if (ssos!=null && ssos.size()>0){
            return AjaxResult.me().setSuccess(false).setMessage("手机号已注册了！");
        }
        return sendSmsCode(mobile);
    }

    private AjaxResult sendSmsCode(String mobile) {
        String smsKey = "SMS_CODE:"+mobile;
        String smsCode = StrUtils.getComplexRandomString(4);
        String smsCodeForRedis= redisClient.get(smsKey);
        if (StringUtils.hasLength(smsCodeForRedis)){
            String[] split = smsCodeForRedis.split(":");
            String timeStr = split[1];
            long time = System.currentTimeMillis()-Long.valueOf(timeStr);
            if (time<1000*60){
                return AjaxResult.me().setSuccess(false).setMessage("不要重复发送短信验证码");
            }
            smsCode = split[0];
        }
        redisClient.addForTime(smsKey,smsCode+":"+System.currentTimeMillis(),60*5);
        System.out.println("[ero工坊]已经发送验证码"+smsCode+"到用户手机:"+mobile);
        Map<String,String> params = new HashMap<>();
        params.put("smsMob",mobile);
        params.put("smsText","验证码为:"+smsCode+",请在5分钟之内使用!");
        smsClient.send(JSONObject.toJSONString(params));
        return AjaxResult.me();
    }
}
