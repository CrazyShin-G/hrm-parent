package com.erocraft.client;


import com.erocraft.util.AjaxResult;
import feign.Response;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 14179
 */
@Component
public class FastDfsClientFallbackFactory implements FallbackFactory<FastDfsClient> {
    @Override
    public FastDfsClient create(Throwable throwable) {
        return new FastDfsClient() {
            @Override
            public AjaxResult upload(MultipartFile file) {
                return new AjaxResult().setSuccess(false).setMessage("上传失败");
            }

            @Override
            public Response download(String path) {
                return null;
            }
        };
    }
}
