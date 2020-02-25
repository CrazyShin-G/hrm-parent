package com.erocraft.cache.impl;


import com.alibaba.fastjson.JSONArray;
import com.erocraft.cache.ICourseTypeCache;
import com.erocraft.client.RedisClient;
import com.erocraft.domain.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 14179
 */
@Component
public class CourseTypeCacheImpl implements ICourseTypeCache {

    private final static String COURSETYPE_KEY_IN_CACHE = "courseType_Key_in_cache";

    @Autowired
    private RedisClient redisClient;

    @Override
    public List<CourseType> getTreeData() {
        String treeDataOfCache = redisClient.get(COURSETYPE_KEY_IN_CACHE);
        return JSONArray.parseArray(treeDataOfCache,CourseType.class);
    }

    @Override
    public void setTreeData(List<CourseType> courseTypesOfDb) {

        if (courseTypesOfDb == null || courseTypesOfDb.size()<1){
            redisClient.add(COURSETYPE_KEY_IN_CACHE,"[]");
        }else{
            String jsonArrStr = JSONArray.toJSONString(courseTypesOfDb);
            redisClient.add(COURSETYPE_KEY_IN_CACHE,jsonArrStr);
        }

    }
}
