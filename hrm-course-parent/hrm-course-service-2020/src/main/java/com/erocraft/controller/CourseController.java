package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Course;
import com.erocraft.query.CourseQuery;
import com.erocraft.service.ICourseService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    public ICourseService courseService;

	@PostMapping
    public AjaxResult add(@RequestBody Course course){
        try {
			courseService.insert(course);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            courseService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody Course course){
        try {
 /*            @TODO 以后登录成功都能获取,现在使用holder来模拟
            登录成功后设置到UserInfoHolder，以后所有模块要使用都直接使用UserInfoHolder
            course.setTenantId(UserInfoHolder.getTenant().getId());
            course.setTenantName(UserInfoHolder.getTenant().getCompanyName());
            course.setUserId(UserInfoHolder.getLoginUser().getId());
            course.setUserName(UserInfoHolder.getLoginUser().getUsername());*/
            if(course.getId()!=null){
                courseService.updateById(course);
            }else{
                courseService.insert(course);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Course get(@PathVariable("id")Long id)
    {
        return courseService.selectById(id);
    }


    @GetMapping
    public List<Course> list(){

        return courseService.selectList(null);
    }


    @PostMapping("/list")
    public PageList<Course> json(@RequestBody CourseQuery query)
    {
        return courseService.selectpageList(query);
    }


    @PostMapping("/onLine")
    public AjaxResult onLine(Long[] ids){

        return courseService.onLine(ids);
    }

    @PostMapping("/offLine")
    public AjaxResult offLine(Long[] ids){
        return courseService.offLine(ids);
    }
}
