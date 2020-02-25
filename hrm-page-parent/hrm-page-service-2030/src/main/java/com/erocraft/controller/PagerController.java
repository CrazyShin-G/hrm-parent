package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Pager;
import com.erocraft.query.PagerQuery;
import com.erocraft.service.IPagerService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/pager")
public class PagerController {
    @Autowired
    public IPagerService pagerService;

	@PostMapping
    public AjaxResult add(@RequestBody Pager pager){
        try {
			pagerService.insert(pager);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            pagerService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除失败"+e.getMessage());
        }
    }

	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody Pager pager){
        try {
            if(pager.getId()!=null){
                pagerService.updateById(pager);
            }else{
                pagerService.insert(pager);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Pager get(@PathVariable("id")Long id)
    {
        return pagerService.selectById(id);
    }

    @GetMapping
    public List<Pager> list(){

        return pagerService.selectList(null);
    }

    @PostMapping("/list")
    public PageList<Pager> json(@RequestBody PagerQuery query)
    {
        Page<Pager> page = new Page<Pager>(query.getPage(),query.getRows());
        page = pagerService.selectPage(page);
        return new PageList<Pager>(page.getTotal(),page.getRecords());
    }
}
