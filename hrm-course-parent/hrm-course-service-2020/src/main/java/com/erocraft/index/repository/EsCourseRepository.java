package com.erocraft.index.repository;

import com.erocraft.index.doc.EsCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

//在service通过注入它就可以完成索引库操作了
public interface EsCourseRepository extends ElasticsearchRepository<EsCourse,Long> {
}
