package com.erocraft.service;


import com.erocraft.CouseServiceApplication2020;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouseServiceApplication2020.class)
public class ICourseTypeServiceTest {

    @Autowired
    private ICourseTypeService courseTypeService;
    @Test
    public void testVelocityTmplate() {
        Map<String,Object> model = new HashMap<>();
        model.put("staticRoot","C:\\Users\\admin\\AppData\\Local\\Temp\\temp");
        model.put("courseTypes",courseTypeService.treeData(0L));
        VelocityUtils.staticByTemplate(model,
                "C:\\Users\\admin\\AppData\\Local\\Temp\\temp\\home.vm",
                "C:\\Users\\admin\\AppData\\Local\\Temp\\temp\\home.vm"+".html");
    }
}