package com.erocraft.client;


import com.erocraft.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 14179
 */
@FeignClient(value = "HRM-PAGE",fallbackFactory = PageConfigClientFallbackFactory.class  )
@RequestMapping("/pageConfig")
public interface PageConfigClient {

    @PostMapping("/pageStatic")
    AjaxResult staticPage(
            @RequestParam(value = "pageName", required = true) String pageName,
            @RequestParam(value = "dataKey", required = true) String dataKey);
}
