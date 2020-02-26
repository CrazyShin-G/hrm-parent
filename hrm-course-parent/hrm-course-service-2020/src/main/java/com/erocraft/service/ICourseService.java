package com.erocraft.service;


import com.baomidou.mybatisplus.service.IService;
import com.erocraft.domain.Course;
import com.erocraft.index.doc.EsCourse;
import com.erocraft.query.CourseQuery;
import com.erocraft.util.AjaxResult;
import com.erocraft.util.PageList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1417
 * @since 2020-02-20
 */
public interface ICourseService extends IService<Course> {

    PageList<Course> selectpageList(CourseQuery query);

    AjaxResult onLine(Long[] ids);

    AjaxResult offLine(Long[] ids);

    PageList<EsCourse> queryCourses(CourseQuery query);
}
