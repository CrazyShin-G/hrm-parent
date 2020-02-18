package com.erocraft.controller;


import com.erocraft.util.AjaxResult;
import com.erocraft.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/fastDfs")
public class FastDfsController {
    @PostMapping
    public AjaxResult upload(@RequestParam(required = true,value = "file")MultipartFile file){
        try {
            System.out.println(file.getOriginalFilename() + ":" + file.getSize());
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            System.out.println(extName);
            String filePath =  FastDfsApiOpr.upload(file.getBytes(), extName);
            return AjaxResult.me().setResultObj(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败!"+e.getMessage());
        }
    }



    @DeleteMapping
    public AjaxResult del(@RequestParam(required = true,value = "path") String path){
        String pathTmp = path.substring(1);
        String groupName =  pathTmp.substring(0, pathTmp.indexOf("/"));
        String remotePath = pathTmp.substring(pathTmp.indexOf("/")+1);
        System.out.println(groupName);
        System.out.println(remotePath);
        FastDfsApiOpr.delete(groupName, remotePath);
        return  AjaxResult.me();
    }
}
