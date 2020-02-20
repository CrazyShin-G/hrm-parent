package com.erocraft.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.CourseDetail;
import com.erocraft.query.CourseDetailQuery;
import com.erocraft.service.ICourseDetailService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/courseDetail")
public class CourseDetailController {
    @Autowired
    public ICourseDetailService courseDetailService;

	@PostMapping
    public AjaxResult add(@RequestBody CourseDetail courseDetail){
        try {
			courseDetailService.insert(courseDetail);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }


    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            courseDetailService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }


	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody CourseDetail courseDetail){
        try {
            if(courseDetail.getId()!=null){
                courseDetailService.updateById(courseDetail);
            }else{
                courseDetailService.insert(courseDetail);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public CourseDetail get(@PathVariable("id")Long id)
    {
        return courseDetailService.selectById(id);
    }


    @GetMapping
    public List<CourseDetail> list(){

        return courseDetailService.selectList(null);
    }


    @PostMapping("/list")
    public PageList<CourseDetail> json(@RequestBody CourseDetailQuery query)
    {
        Page<CourseDetail> page = new Page<CourseDetail>(query.getPage(),query.getRows());
        page = courseDetailService.selectPage(page);
        return new PageList<CourseDetail>(page.getTotal(),page.getRecords());
    }
}
