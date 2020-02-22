package com.erocraft.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Course;
import com.erocraft.query.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 1417
 * @since 2020-02-20
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> loadPageList(Page<Course> page, CourseQuery query);

    void onLine(Map<String, Object> params);

    void offLine(Map<String, Object> params);
}
