package com.erocraft.service;

import cn.itsource.CouseServiceApplication2020;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouseServiceApplication2020.class)
public class ICourseTypeServiceTest {

    @Autowired
    private ICourseTypeService courseTypeService;
    @Test
    public void treeData() {

    }
}