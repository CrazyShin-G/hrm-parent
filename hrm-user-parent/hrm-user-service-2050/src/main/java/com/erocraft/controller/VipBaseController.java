package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.VipBase;
import com.erocraft.query.VipBaseQuery;
import com.erocraft.service.IVipBaseService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/vipBase")
public class VipBaseController {
    @Autowired
    public IVipBaseService vipBaseService;

	@PostMapping
    public AjaxResult add(@RequestBody VipBase vipBase){
        try {
			vipBaseService.insert(vipBase);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            vipBaseService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除失败"+e.getMessage());
        }
    }

	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody VipBase vipBase){
        try {
            if(vipBase.getId()!=null){
                vipBaseService.updateById(vipBase);
            }else{
                vipBaseService.insert(vipBase);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public VipBase get(@PathVariable("id")Long id)
    {
        return vipBaseService.selectById(id);
    }

    @GetMapping
    public List<VipBase> list(){

        return vipBaseService.selectList(null);
    }
    
    @PostMapping("/list")
    public PageList<VipBase> json(@RequestBody VipBaseQuery query)
    {
        Page<VipBase> page = new Page<VipBase>(query.getPage(),query.getRows());
        page = vipBaseService.selectPage(page);
        return new PageList<VipBase>(page.getTotal(),page.getRecords());
    }
}
