package com.erocraft.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Course;
import com.erocraft.domain.CourseDetail;
import com.erocraft.domain.CourseMarket;
import com.erocraft.index.repository.EsCourseRepository;
import com.erocraft.mapper.CourseDetailMapper;
import com.erocraft.mapper.CourseMapper;
import com.erocraft.mapper.CourseMarketMapper;
import com.erocraft.query.CourseQuery;
import com.erocraft.service.ICourseService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-20
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseDetailMapper courseDetailMapper;
    @Autowired
    private CourseMarketMapper courseMarketMapper;

    @Autowired
    private EsCourseRepository repository;
    @Override
    public PageList<Course> selectpageList(CourseQuery query) {
        Page<Course> page = new Page<>(query.getPage(),query.getRows());
        List<Course> courses = courseMapper.loadPageList(page,query);
        return new PageList<>(page.getTotal(),courses);
    }


    @Override
    public boolean insert(Course entity) {
        System.out.println(entity);
        entity.setStatus(0);
        courseMapper.insert(entity);
        Long courseId = entity.getId();
        CourseDetail courseDetail = entity.getCourseDetail();
        courseDetail.setCourseId(courseId);
        courseDetailMapper.insert(courseDetail);
        CourseMarket courseMarket = entity.getCourseMarket();
        courseMarket.setCourseId(courseId);
        courseMarketMapper.insert(courseMarket);
        return true;
    }

    @Override
    public boolean updateById(Course entity) {
        //@TODO 修改也要同步修改，删除也要同步删除
        return super.updateById(entity);
    }



    @Override
    public AjaxResult onLine(Long[] ids) {

//        try
//        {
//            //1添加索引库
//            // 通过id查询数据库里面课程
//            List<Course> courses = courseMapper
//                    .selectBatchIds(Arrays.asList(ids));
//            //转换为doc
//            List<EsCourse> esCourses = courses2EsCourses(courses);
//            //批量添加就ok
//            repository.saveAll(esCourses);
//            //2 修改数据库状态和上线时间 - ids,time
//            Map<String,Object> params = new HashMap<>();
//            params.put("ids",ids);
//            params.put("offLineTime",new Date());
//            courseMapper.onLine(params);
//            return AjaxResult.me();
//        }catch (Exception e){
//            e.printStackTrace();
//            return AjaxResult.me().setSuccess(false).setMessage("上线失败！"+e.getMessage());
//        }

        return null;

    }

    @Override
    public AjaxResult offLine(Long[] ids) {
        return null;
    }
}
