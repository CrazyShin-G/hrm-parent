package com.erocraft.controller;


import com.erocraft.util.AjaxResult;
import com.erocraft.util.RedisUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @PostMapping
    public AjaxResult add(
            @RequestParam(value = "key",required = true) String key,
            @RequestParam(value = "value",required = true) String value)
    {
        try
        {
            RedisUtils.INSTANCE.set(key,value);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("添加失败");
        }
    }

    @PostMapping("/time")
    AjaxResult addForTime(@RequestParam(value = "key",required = true) String key
            ,@RequestParam(value = "value",required = true) String value,@RequestParam(value = "time",required = true)Integer time){
        try
        {
            RedisUtils.INSTANCE.set(key,value,time);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("添加失败");
        }
    }

    @DeleteMapping
    public AjaxResult del(@RequestParam(value = "key",required = true) String key)
    {
        try
        {
            RedisUtils.INSTANCE.del(key);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败");
        }
    }

    @GetMapping
    public String get(@RequestParam(value = "key",required = true) String key)
    {
        try
        {
            return RedisUtils.INSTANCE.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
