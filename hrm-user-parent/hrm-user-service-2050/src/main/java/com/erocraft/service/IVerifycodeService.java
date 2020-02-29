package com.erocraft.service;



import com.erocraft.util.AjaxResult;
import java.util.Map;

/**
 * @author 14179
 */
public interface IVerifycodeService {
    /**
     * 获取验证码
     * @param imageCodeKey
     * @return
     */
    String getImageCode(String imageCodeKey);

    /**
     * 发送短信验证码
     * @param params
     * @return
     */
    AjaxResult sendSmsCode(Map<String, String> params);
}
