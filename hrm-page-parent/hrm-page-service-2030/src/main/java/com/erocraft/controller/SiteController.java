package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Site;
import com.erocraft.query.SiteQuery;
import com.erocraft.service.ISiteService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/site")
public class SiteController {
    @Autowired
    public ISiteService siteService;

	@PostMapping
    public AjaxResult add(@RequestBody Site site){
        try {
			siteService.insert(site);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            siteService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除失败"+e.getMessage());
        }
    }

	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody Site site){
        try {
            if(site.getId()!=null){
                siteService.updateById(site);
            }else{
                siteService.insert(site);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Site get(@PathVariable("id")Long id)
    {
        return siteService.selectById(id);
    }

    @GetMapping
    public List<Site> list(){

        return siteService.selectList(null);
    }

    @PostMapping("/list")
    public PageList<Site> json(@RequestBody SiteQuery query)
    {
        Page<Site> page = new Page<Site>(query.getPage(),query.getRows());
        page = siteService.selectPage(page);
        return new PageList<Site>(page.getTotal(),page.getRecords());
    }
}
