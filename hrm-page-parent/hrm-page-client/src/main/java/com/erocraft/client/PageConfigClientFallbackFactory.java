package com.erocraft.client;


import com.erocraft.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 14179
 */
@Component
public class PageConfigClientFallbackFactory implements FallbackFactory<PageConfigClient> {
    @Override
    public PageConfigClient create(Throwable throwable) {
        return new PageConfigClient() {
            @Override
            public AjaxResult staticPage(String pageName, String dataKey) {
                return AjaxResult.me().setSuccess(false).setMessage("失败");
            }
        };
    }
}
