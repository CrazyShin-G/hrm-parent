package com.erocraft.client;


import com.erocraft.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;


/**
 * @author 14179
 */
@FeignClient(value = "HRM-COMMON", fallbackFactory = RedisClientFallbackFactory.class )
@RequestMapping("/redis")
public interface RedisClient {
    @PostMapping
    AjaxResult add(@RequestParam(value = "key", required = true) String key, @RequestParam(value = "value", required = true) String value);
    @PostMapping("/time")
    AjaxResult addForTime(@RequestParam(value = "key", required = true) String key
            , @RequestParam(value = "value", required = true) String value, @RequestParam(value = "time", required = true) Integer time);
    @DeleteMapping
    AjaxResult del(@RequestParam(value = "key", required = true) String key);
    @GetMapping
    String get(@RequestParam(value = "key", required = true) String key);
}
