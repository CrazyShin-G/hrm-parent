package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Sso;
import com.erocraft.query.SsoQuery;
import com.erocraft.service.ISsoService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/sso")
public class SsoController {
    @Autowired
    public ISsoService ssoService;

    @PostMapping("/login")

    public AjaxResult login(@RequestBody Sso sso){
       return ssoService.login(sso);
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody Map<String,String> params)
    {
        return ssoService.register(params);
    }

	@PostMapping
    public AjaxResult add(@RequestBody Sso sso){
        try {
			ssoService.insert(sso);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            ssoService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除失败"+e.getMessage());
        }
    }

	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody Sso sso){
        try {
            if(sso.getId()!=null){
                ssoService.updateById(sso);
            }else{
                ssoService.insert(sso);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Sso get(@PathVariable("id")Long id)
    {
        return ssoService.selectById(id);
    }

    @GetMapping
    public List<Sso> list(){

        return ssoService.selectList(null);
    }

    @PostMapping("/list")
    public PageList<Sso> json(@RequestBody SsoQuery query)
    {
        Page<Sso> page = new Page<Sso>(query.getPage(),query.getRows());
        page = ssoService.selectPage(page);
        return new PageList<Sso>(page.getTotal(),page.getRecords());
    }
}
