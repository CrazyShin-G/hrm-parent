package com.erocraft.service;


import com.baomidou.mybatisplus.service.IService;
import com.erocraft.domain.CourseType;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author 1417
 * @since 2020-02-17
 */
public interface ICourseTypeService extends IService<CourseType> {

    List<CourseType> treeData(long pid);

    void staticIndexPageInit();

    List<Map<String, Object>> queryCrumbs(Long courseTypeId);
}
