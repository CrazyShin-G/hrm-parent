package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.CourseResource;
import com.erocraft.query.CourseResourceQuery;
import com.erocraft.service.ICourseResourceService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 14179
 */
@RestController
@RequestMapping("/courseResource")
public class CourseResourceController {
    @Autowired
    public ICourseResourceService courseResourceService;


	@PostMapping
    public AjaxResult add(@RequestBody CourseResource courseResource){
        try {
			courseResourceService.insert(courseResource);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            courseResourceService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody CourseResource courseResource){
        try {
            if(courseResource.getId()!=null){
                courseResourceService.updateById(courseResource);
            }else{
                courseResourceService.insert(courseResource);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public CourseResource get(@PathVariable("id")Long id)
    {
        return courseResourceService.selectById(id);
    }


    @GetMapping
    public List<CourseResource> list(){

        return courseResourceService.selectList(null);
    }


    @PostMapping("/list")
    public PageList<CourseResource> json(@RequestBody CourseResourceQuery query)
    {
        Page<CourseResource> page = new Page<CourseResource>(query.getPage(),query.getRows());
        page = courseResourceService.selectPage(page);
        return new PageList<CourseResource>(page.getTotal(),page.getRecords());
    }
}
