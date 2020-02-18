package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Systemdictionary;
import com.erocraft.query.SystemdictionaryQuery;
import com.erocraft.service.ISystemdictionaryService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/systemdictionary")
public class SystemdictionaryController {
    @Autowired
    public ISystemdictionaryService systemdictionaryService;

    /**
    * 保存和修改公用的
    * @param systemdictionary  传递的实体
    * @return Ajaxresult转换结果
    */
	@PostMapping
    public AjaxResult add(@RequestBody Systemdictionary systemdictionary){
        try {
			systemdictionaryService.insert(systemdictionary);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            systemdictionaryService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

 /**
    * 保存和修改公用的
    * @param systemdictionary  传递的实体
    * @return Ajaxresult转换结果
    */
	@PutMapping
    public AjaxResult update(@RequestBody Systemdictionary systemdictionary){
        try {
            systemdictionaryService.updateById(systemdictionary);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Systemdictionary get(@PathVariable("id")Long id)
    {
        return systemdictionaryService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping()
    public List<Systemdictionary> list(){

        return systemdictionaryService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Systemdictionary> json(@RequestBody SystemdictionaryQuery query)
    {
        Page<Systemdictionary> page = new Page<Systemdictionary>(query.getPage(),query.getRows());
        page = systemdictionaryService.selectPage(page);
        return new PageList<Systemdictionary>(page.getTotal(),page.getRecords());
    }
}
