package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Permission;
import com.erocraft.query.PermissionQuery;
import com.erocraft.service.IPermissionService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    public IPermissionService permissionService;

    /**
    * 保存和修改公用的
    * @param permission  传递的实体
    * @return Ajaxresult转换结果
    */
	@PostMapping
    public AjaxResult add(@RequestBody Permission permission){
        try {
			permissionService.insert(permission);
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
            permissionService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

 /**
    * 保存和修改公用的
    * @param permission  传递的实体
    * @return Ajaxresult转换结果
    */
	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody Permission permission){
        try {
            if(permission.getId()!=null){
                permissionService.updateById(permission);
            }else{
                permissionService.insert(permission);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Permission get(@PathVariable("id")Long id)
    {
        return permissionService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public List<Permission> list(){

        return permissionService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Permission> json(@RequestBody PermissionQuery query)
    {
        Page<Permission> page = new Page<Permission>(query.getPage(),query.getRows());
        page = permissionService.selectPage(page);
        return new PageList<Permission>(page.getTotal(),page.getRecords());
    }
}
