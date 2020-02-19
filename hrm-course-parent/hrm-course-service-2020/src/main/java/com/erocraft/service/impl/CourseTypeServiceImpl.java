package com.erocraft.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.cache.ICourseTypeCache;
import com.erocraft.domain.CourseType;
import com.erocraft.mapper.CourseTypeMapper;
import com.erocraft.service.ICourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ICourseTypeCache courseTypeCache;
    @Override
    public List<CourseType> treeData(long pid) {

        List<CourseType>courseTypes = courseTypeCache.getTreeData();
        if (courseTypes!=null && courseTypes.size()>0){
            System.out.println("cache ....");
            return courseTypes;
        }else{
            System.out.println("db....");

            List<CourseType> courseTypesOfDb = treeDataLoop(pid);
            courseTypeCache.setTreeData(courseTypesOfDb);
            return courseTypesOfDb;
        }

    }

    private List<CourseType> treeDataLoop(long pid) {
        List<CourseType> allNodes = courseTypeMapper.selectList(null);

        Map<Long,CourseType>  allNodeDto = new HashMap<>();
        for (CourseType courseType : allNodes) {
            allNodeDto.put(courseType.getId(),courseType);
        }
        List<CourseType> result = new ArrayList<>();
        for (CourseType courseType : allNodes) {
            if (courseType.getPid().intValue()==0) {
                result.add(courseType);
            }else{

                Long pidTmp = courseType.getPid();
                CourseType parent = allNodeDto.get(pidTmp);
                parent.getChildren().add(courseType);

            }

        }
        return  result;
    }

    private List<CourseType> treeDataRecursion(long pid) {
        List<CourseType> children = courseTypeMapper.selectList(new EntityWrapper<CourseType>().eq("pid", pid));
        if (children==null || children.size()<1){
            return null;
        }
        for (CourseType child : children) {
            List<CourseType> cTmp = treeDataRecursion(child.getId());
            child.setChildren(cTmp);
        }

        return children;

    }

    @Override
    public boolean insert(CourseType entity) {

        courseTypeMapper.insert(entity);
        courseTypeCache.setTreeData(treeDataLoop(0));
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        courseTypeMapper.deleteById(id);
        courseTypeCache.setTreeData(treeDataLoop(0));
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        courseTypeMapper.updateById(entity);
        courseTypeCache.setTreeData(treeDataLoop(0));
        return true;
    }
}
