package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.PageConfig;
import com.erocraft.query.PageConfigQuery;
import com.erocraft.service.IPageConfigService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/pageConfig")
public class PageConfigController {
    @Autowired
    public IPageConfigService pageConfigService;

    @PostMapping("/pageStatic")
    AjaxResult staticPage(
            @RequestParam(value = "pageName",required = true) String pageName,
            @RequestParam(value = "dataKey",required = true)String dataKey){
        try {
            pageConfigService.staticPage(pageName,dataKey);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("静态化失败"+e.getMessage());
        }
    }

	@PostMapping
    public AjaxResult add(@RequestBody PageConfig pageConfig){
        try {
			pageConfigService.insert(pageConfig);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            pageConfigService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除失败"+e.getMessage());
        }
    }

	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody PageConfig pageConfig){
        try {
            if(pageConfig.getId()!=null){
                pageConfigService.updateById(pageConfig);
            }else{
                pageConfigService.insert(pageConfig);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PageConfig get(@PathVariable("id")Long id)
    {
        return pageConfigService.selectById(id);
    }

    @GetMapping
    public List<PageConfig> list(){

        return pageConfigService.selectList(null);
    }

    @PostMapping("/list")
    public PageList<PageConfig> json(@RequestBody PageConfigQuery query)
    {
        Page<PageConfig> page = new Page<PageConfig>(query.getPage(),query.getRows());
        page = pageConfigService.selectPage(page);
        return new PageList<PageConfig>(page.getTotal(),page.getRecords());
    }
}
