package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Employee;
import com.erocraft.query.EmployeeQuery;
import com.erocraft.service.IEmployeeService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public IEmployeeService employeeService;

    /**
    * 保存和修改公用的
    * @param employee  传递的实体
    * @return Ajaxresult转换结果
    */
	@PostMapping
    public AjaxResult add(@RequestBody Employee employee){
        try {
			employeeService.insert(employee);
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
            employeeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

 /**
    * 保存和修改公用的
    * @param employee  传递的实体
    * @return Ajaxresult转换结果
    */
	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody Employee employee){
        try {
            if(employee.getId()!=null){
                employeeService.updateById(employee);
            }else{
                employeeService.insert(employee);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public Employee get(@PathVariable("id")Long id)
    {
        return employeeService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public List<Employee> list(){

        return employeeService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping("/list")
    public PageList<Employee> json(@RequestBody EmployeeQuery query)
    {
        Page<Employee> page = new Page<Employee>(query.getPage(),query.getRows());
        page = employeeService.selectPage(page);
        return new PageList<Employee>(page.getTotal(),page.getRecords());
    }
}
