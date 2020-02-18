package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Menu;
import com.erocraft.query.MenuQuery;
import com.erocraft.service.IMenuService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    public IMenuService menuService;

    /**
    * 保存和修改公用的
    * @param menu  传递的实体
    * @return Ajaxresult转换结果
    */
	@PostMapping
    public AjaxResult add(@RequestBody Menu menu){
        try {
			menuService.insert(menu);
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
            menuService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

 /**
    * 保存和修改公用的
    * @param menu  传递的实体
    * @return Ajaxresult转换结果
    */
	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody Menu menu){
        try {
            if(menu.getId()!=null){
                menuService.updateById(menu);
            }else{
                menuService.insert(menu);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Menu get(@PathVariable("id")Long id)
    {
        return menuService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public List<Menu> list(){

        return menuService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Menu> json(@RequestBody MenuQuery query)
    {
        Page<Menu> page = new Page<Menu>(query.getPage(),query.getRows());
        page = menuService.selectPage(page);
        return new PageList<Menu>(page.getTotal(),page.getRecords());
    }
}
