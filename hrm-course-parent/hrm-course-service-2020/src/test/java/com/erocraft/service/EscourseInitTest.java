package com.erocraft.service;


import com.erocraft.CouseServiceApplication2020;
import com.erocraft.index.doc.EsCourse;
import com.erocraft.index.repository.EsCourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouseServiceApplication2020.class)
public class EscourseInitTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private EsCourseRepository repository;

    @Test
    public void initTest() {
        elasticsearchTemplate.createIndex(EsCourse.class);
        elasticsearchTemplate.putMapping(EsCourse.class);
        System.out.println(repository);
    }
}
