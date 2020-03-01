package com.erocraft.service;



import com.erocraft.util.AjaxResult;

import java.util.Map;

/**
 * @author 14179
 */
public interface ISmsService {

    AjaxResult sendSmsCode(Map<String, String> params);
}
