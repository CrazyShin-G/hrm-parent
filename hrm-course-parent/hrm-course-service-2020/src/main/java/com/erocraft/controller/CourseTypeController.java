package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.CourseType;
import com.erocraft.query.CourseTypeQuery;
import com.erocraft.service.ICourseTypeService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    public ICourseTypeService courseTypeService;

	@PostMapping
    public AjaxResult add(@RequestBody CourseType courseType){
        try {
			courseTypeService.insert(courseType);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败！"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            courseTypeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除失败！"+e.getMessage());
        }
    }

	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody CourseType courseType){
        try {
            if(courseType.getId()!=null){
                courseTypeService.updateById(courseType);
            }else{
                courseTypeService.insert(courseType);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存失败！"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public CourseType get(@PathVariable("id")Long id)
    {
        return courseTypeService.selectById(id);
    }

    @GetMapping
    public List<CourseType> list(){

        return courseTypeService.selectList(null);
    }

    @PostMapping("/list")
    public PageList<CourseType> json(@RequestBody CourseTypeQuery query)
    {
        Page<CourseType> page = new Page<CourseType>(query.getPage(),query.getRows());
        page = courseTypeService.selectPage(page);
        return new PageList<CourseType>(page.getTotal(),page.getRecords());
    }

    @GetMapping("/treeData")
    public List<CourseType> treeData(){
        return courseTypeService.treeData(0L);
    }

}
