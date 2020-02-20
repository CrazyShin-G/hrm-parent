package com.erocraft.client;

import cn.itsource.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Component;

@Component
public class RedisClientFallbackFactory implements FallbackFactory<RedisClient> {
    @Override
    public RedisClient create(Throwable throwable) {
        return new RedisClient() {
            @Override
            public AjaxResult add(String key, String value) {
                return AjaxResult.me().setSuccess(false).setMessage("redis调用失败");
            }

            @Override
            public AjaxResult del(String key) {
                return AjaxResult.me().setSuccess(false).setMessage("redis调用失败");
            }

            @Override
            public String get(String key) {
                return "redis调用失败";
            }
        };
    }
}
