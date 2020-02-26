package com.erocraft.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Course;
import com.erocraft.domain.CourseDetail;
import com.erocraft.domain.CourseMarket;
import com.erocraft.index.doc.EsCourse;
import com.erocraft.index.repository.EsCourseRepository;
import com.erocraft.mapper.CourseDetailMapper;
import com.erocraft.mapper.CourseMapper;
import com.erocraft.mapper.CourseMarketMapper;
import com.erocraft.query.CourseQuery;
import com.erocraft.service.ICourseService;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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

        return super.updateById(entity);
    }



    @Override
    public AjaxResult onLine(Long[] ids) {

        try
        {
            List<Course> courses = courseMapper.selectBatchIds(Arrays.asList(ids));
            List<EsCourse> esCourses = courses2EsCourses(courses);
            repository.saveAll(esCourses);
            Map<String,Object> params = new HashMap<>();
            params.put("ids",ids);
            params.put("onLineTime",new Date());
            courseMapper.onLine(params);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上线失败！"+e.getMessage());
        }


    }


    @Override
    public AjaxResult offLine(Long[] ids) {
        try
        {
            for (Long id : ids) {
                repository.deleteById(id);
            }
            Map<String,Object> params = new HashMap<>();
            params.put("ids",ids);
            params.put("offLineTime",new Date());
            courseMapper.offLine(params);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("下线失败！"+e.getMessage());
        }

    }

    @Override
    public PageList<EsCourse> queryCourses(CourseQuery query) {

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        builder.withQuery(boolQuery);
        FieldSortBuilder order = SortBuilders.fieldSort(query.getSortField()).order(SortOrder.DESC);
        builder.withSort(order);
        builder.withPageable(PageRequest.of(query.getPage()-1,query.getRows()));
        NativeSearchQuery esQuery = builder.build();
        org.springframework.data.domain.Page<EsCourse> page =
                repository.search(esQuery);
        return new PageList<>(page.getTotalElements(),page.getContent());
    }

    private List<EsCourse> courses2EsCourses(List<Course> courses) {
        List<EsCourse> result = new ArrayList<>();
        for (Course course : courses) {
            result.add(course2EsCourse(course));
        }
        return  result;
    }

    private EsCourse course2EsCourse(Course course) {
        EsCourse result = new EsCourse();
        result.setId(course.getId());
        result.setName(course.getName());
        result.setUsers(course.getUsers());
        result.setCourseTypeId(course.getCourseTypeId());
        if (course.getCourseType() != null)
            result.setCourseTypeName(course.getCourseType().getName());
        result.setGradeId(course.getGrade());
        result.setGradeName(course.getGradeName());
        result.setStatus(course.getStatus());
        result.setTenantId(course.getTenantId());
        result.setTenantName(course.getTenantName());
        result.setUserId(course.getUserId());
        result.setUserName(course.getUserName());
        result.setStartTime(course.getStartTime());
        result.setEndTime(course.getEndTime());
        result.setIntro(null);
        result.setResources(null);
        result.setExpires(null);
        result.setPrice(null);
        result.setPriceOld(null);
        result.setQq(null);
        return result;
    }

}
