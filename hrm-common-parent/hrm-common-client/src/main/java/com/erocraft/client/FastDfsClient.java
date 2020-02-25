package com.erocraft.client;


import com.erocraft.util.AjaxResult;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 14179
 */
@FeignClient(value = "HRM-COMMON", configuration = FeignMultipartSupportConfig.class,
        fallbackFactory = FastDfsClientFallbackFactory.class )
@RequestMapping("/fastDfs")
public interface FastDfsClient {

    @PostMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    AjaxResult upload(@RequestPart(required = true, value = "file") MultipartFile file);


    @GetMapping
     Response download(@RequestParam(required = true, value = "path") String path);
}
