package com.erocraft.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.CourseType;
import com.erocraft.mapper.CourseTypeMapper;
import com.erocraft.service.ICourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-17
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Override
    public List<CourseType> treeData(long pid) {
        return treeDataRecursion(pid);
    }

    private List<CourseType> treeDataRecursion(long pid) {
        List<CourseType> children = courseTypeMapper
                .selectList(new EntityWrapper<CourseType>().eq("pid", pid));
        if (children==null || children.size()<1){
            return null;
        }
        for (CourseType child : children) {
            List<CourseType> cTmp = treeDataRecursion(child.getId());
            child.setChildren(cTmp);
        }

        return children;

    }
}
