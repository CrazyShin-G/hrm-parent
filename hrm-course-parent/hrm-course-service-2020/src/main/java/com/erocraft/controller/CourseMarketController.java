package com.erocraft.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.CourseMarket;
import com.erocraft.query.CourseMarketQuery;
import com.erocraft.service.ICourseMarketService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author 14179
 */
@RestController
@RequestMapping("/courseMarket")
public class CourseMarketController {
    @Autowired
    public ICourseMarketService courseMarketService;

	@PostMapping
    public AjaxResult add(@RequestBody CourseMarket courseMarket){
        try {
			courseMarketService.insert(courseMarket);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            courseMarketService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }


	@PutMapping
    public AjaxResult addOrUpdate(@RequestBody CourseMarket courseMarket){
        try {
            if(courseMarket.getId()!=null){
                courseMarketService.updateById(courseMarket);
            }else{
                courseMarketService.insert(courseMarket);
            }

            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public CourseMarket get(@PathVariable("id")Long id)
    {
        return courseMarketService.selectById(id);
    }


    @GetMapping
    public List<CourseMarket> list(){

        return courseMarketService.selectList(null);
    }


    @PostMapping("/list")
    public PageList<CourseMarket> json(@RequestBody CourseMarketQuery query)
    {
        Page<CourseMarket> page = new Page<CourseMarket>(query.getPage(),query.getRows());
        page = courseMarketService.selectPage(page);
        return new PageList<CourseMarket>(page.getTotal(),page.getRecords());
    }
}
