package com.erocraft.controller;


import com.erocraft.service.IVerifycodeService;
import com.erocraft.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/verifycode")
public class VerifycodeController {
    @Autowired
    private IVerifycodeService verifycodeService;

    @PostMapping("/imageCode/{imageCodeKey}")
    public String imageCode(@PathVariable("imageCodeKey") String imageCodeKey)
    {
       return verifycodeService.getImageCode(imageCodeKey);
    }
    @PostMapping("/sendSmsCode")
    public AjaxResult sendSmsCode(@RequestBody Map<String,String> params)
    {
/*        发送短信验证码到那个手机
        mobile: this.formParams.mobile,
                //图片验证码信息要发过去 后台要校验图片验证
                imageCode:this.formParams.imageCode,
            imageCodeKey:localStorage.getItem("imageCodeKey")*/
           return verifycodeService.sendSmsCode(params);

    }


}
