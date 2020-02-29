package com.erocraft.client;


import com.erocraft.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 14179
 */
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
            public AjaxResult addForTime(String key, String value, Integer time) {
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
