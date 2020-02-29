package com.erocraft.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.erocraft.client.RedisClient;
import com.erocraft.domain.Sso;
import com.erocraft.mapper.SsoMapper;
import com.erocraft.service.IVerifycodeService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.VerifyCodeUtils;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author 14179
 */
@Service
public class VerifycodeServiceImpl implements IVerifycodeService {

    @Autowired
    private SsoMapper ssoMapper;

    @Autowired
    private RedisClient redisClient;
    @Override
    public String getImageCode(String imageCodeKey) {
        try{
            String imageCode = VerifyCodeUtils.generateVerifyCode(6).toLowerCase();
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
            return AjaxResult.me().setSuccess(false).setMessage("图片验证码不正确!");
        }
        if (mobile==null){
            return AjaxResult.me().setSuccess(false).setMessage("请输入正确的手机号码");
        }
        List<Sso> ssos = ssoMapper
                .selectList(new EntityWrapper<Sso>().eq("phone", mobile));
        if (ssos!=null && ssos.size()>0){
            return AjaxResult.me().setSuccess(false).setMessage("你的手机号已经注册了");
        }
        return sendSmsCode(mobile);
    }
    //@TODO
    private AjaxResult sendSmsCode(String mobile) {
        return AjaxResult.me();
    }
}
