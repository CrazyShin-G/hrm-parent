package com.erocraft.cache;



import com.erocraft.domain.CourseType;

import java.util.List;

/**
 * @author 14179
 */
public interface ICourseTypeCache {
    List<CourseType> getTreeData();
    void setTreeData(List<CourseType> courseTypesOfDb);
}
