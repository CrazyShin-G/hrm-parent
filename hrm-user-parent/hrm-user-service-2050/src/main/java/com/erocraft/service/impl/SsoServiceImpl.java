package com.erocraft.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.client.RedisClient;
import com.erocraft.domain.Sso;
import com.erocraft.domain.VipBase;
import com.erocraft.mapper.SsoMapper;
import com.erocraft.mapper.VipBaseMapper;
import com.erocraft.service.ISsoService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.StrUtils;
import com.erocraft.util.encrypt.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 * 会员登录账号 服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-27
 */
@Service
public class SsoServiceImpl extends ServiceImpl<SsoMapper, Sso> implements ISsoService {

    @Autowired
    private SsoMapper ssoMapper;

    @Autowired
    private VipBaseMapper vipBaseMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public AjaxResult login(Sso sso) {
        if (!StringUtils.hasLength(sso.getPhone()) || !StringUtils.hasLength(sso.getPassword())) {
            return AjaxResult.me().setSuccess(false).setMessage("请输入用户名或密码!");
        }
        List<Sso> ssoList = ssoMapper.selectList(new EntityWrapper<Sso>()
                .eq("phone", sso.getPhone()));
        if (ssoList==null || ssoList.size()<1) {
            return  AjaxResult.me().setSuccess(false).setMessage("用户不存在!");
        }

        Sso ssoExsit = ssoList.get(0);

        System.out.println(sso.getPassword());
        System.out.println(ssoExsit.getSalt());
        String md5Pwd = MD5.getMD5(sso.getPassword() + ssoExsit.getSalt());
        System.out.println(md5Pwd);
        if (!ssoExsit.getPassword().equals(md5Pwd)){
            return  AjaxResult.me().setSuccess(false).setMessage("请输入正确的用户名或密码!");
        }

        String accessToken = UUID.randomUUID().toString();
        redisClient.addForTime(accessToken, JSONObject.toJSONString(ssoExsit),30*60);
        return AjaxResult.me().setResultObj(accessToken);
    }

    @Override
    public Sso querySso(String accessToken) {
        String sso = redisClient.get(accessToken);
        return JSONObject.parseObject(sso,Sso.class);
    }

    public static void main(String[] args) {

        String md5 = MD5.getMD5("1" + "szYCdrDehOmArvMU49htAIqgKWIVSTkT");
        System.out.println(md5);
    }

    @Override
    public AjaxResult register(Map<String, String> params) {
        String mobile = params.get("mobile");
        String password = params.get("password");
        String smsCode = params.get("smsCode");
        AjaxResult ajaxResult = validateParam(mobile,password,smsCode);
        if (!ajaxResult.isSuccess()) {
            return ajaxResult;
        }
        Sso sso = new Sso();
        sso.setPhone(mobile);
        String salt = StrUtils.getComplexRandomString(32);
        sso.setSalt(salt);
        String md5Password = MD5.getMD5(password + salt);
        sso.setPassword(md5Password);
        sso.setNickName(mobile);
        sso.setSecLevel(0);
        sso.setBitState(7L);
        sso.setCreateTime(System.currentTimeMillis());
        ssoMapper.insert(sso);
        VipBase vipBase = new VipBase();
        vipBase.setCreateTime(System.currentTimeMillis());
        vipBase.setSsoId(sso.getId());
        vipBase.setRegChannel(1);
        vipBase.setRegTime(System.currentTimeMillis());
        vipBaseMapper.insert(vipBase);
        return AjaxResult.me();
    }



    private AjaxResult validateParam(String mobile,String password,String smsCode){
        if (!StringUtils.hasLength(mobile) || !StringUtils.hasLength(password)) {
            return AjaxResult.me().setSuccess(false).setMessage("请输入用户名或密码!");
        }
        List<Sso> ssoList = ssoMapper.selectList(new EntityWrapper<Sso>().eq("phone", mobile));
        if (ssoList!=null&&ssoList.size()>0)
            return AjaxResult.me().setSuccess(false).setMessage("您已经注册过了!");
        String smsCodeStr = redisClient.get("SMS_CODE:" + mobile);
        String smsCodeOfRedis = smsCodeStr.split(":")[0];
        if (!smsCodeOfRedis.equals(smsCode))
            return AjaxResult.me().setSuccess(false).setMessage("请输入正确短信验证码");

        return AjaxResult.me();
    }
}
