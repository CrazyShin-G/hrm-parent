package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Role;
import com.erocraft.query.RoleQuery;
import com.erocraft.service.IRoleService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public IRoleService roleService;

    /**
    * 保存和修改公用的
    * @param role  传递的实体
    * @return Ajaxresult转换结果
    */
	@PostMapping
    public AjaxResult add(@RequestBody Role role){
        try {
			roleService.insert(role);
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
            roleService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

 /**
    * 保存和修改公用的
    * @param role  传递的实体
    * @return Ajaxresult转换结果
    */
	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody Role role){
        try {
            if(role.getId()!=null){
                roleService.updateById(role);
            }else{
                roleService.insert(role);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Role get(@PathVariable("id")Long id)
    {
        return roleService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public List<Role> list(){

        return roleService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Role> json(@RequestBody RoleQuery query)
    {
        Page<Role> page = new Page<Role>(query.getPage(),query.getRows());
        page = roleService.selectPage(page);
        return new PageList<Role>(page.getTotal(),page.getRecords());
    }
}
