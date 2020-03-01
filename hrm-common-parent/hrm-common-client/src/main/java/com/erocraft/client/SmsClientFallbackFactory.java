package com.erocraft.client;


import com.erocraft.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 14179
 */
@Component
public class SmsClientFallbackFactory implements FallbackFactory<SmsClient> {
    @Override
    public SmsClient create(Throwable throwable) {
        return new SmsClient() {
            @Override
            public AjaxResult send(String params) {
                return null;
            }
        };
    }
}
