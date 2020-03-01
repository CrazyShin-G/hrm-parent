package com.erocraft.controller;


import com.alibaba.fastjson.JSONObject;
import com.erocraft.constants.SmsContants;
import com.erocraft.service.ISmsService;
import com.erocraft.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private ISmsService smsService;
    @PostMapping
    AjaxResult send(@RequestParam String params){
        System.out.println(params);
        Map<String,String> paramsTmp = JSONObject.parseObject(params, Map.class);
        paramsTmp.put("Uid", SmsContants.SMS_UID);
        paramsTmp.put("Key", SmsContants.SMS_KEY);
        return smsService.sendSmsCode(paramsTmp);
    }
}
