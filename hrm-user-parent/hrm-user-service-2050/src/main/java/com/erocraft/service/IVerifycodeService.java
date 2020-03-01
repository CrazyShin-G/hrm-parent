package com.erocraft.service;



import com.erocraft.util.AjaxResult;

import java.util.Map;

/**
 * @author 14179
 */
public interface IVerifycodeService {

    String getImageCode(String imageCodeKey);


    AjaxResult sendSmsCode(Map<String, String> params);
}
