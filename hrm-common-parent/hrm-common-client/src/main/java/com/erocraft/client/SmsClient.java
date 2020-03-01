package com.erocraft.client;


import com.erocraft.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author 14179
 */
@FeignClient(value = "HRM-COMMON", fallbackFactory = SmsClientFallbackFactory.class )
@RequestMapping("/sms")
public interface SmsClient {

    @PostMapping
    AjaxResult send(@RequestParam String params);
}
