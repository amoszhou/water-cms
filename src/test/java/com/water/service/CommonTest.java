package com.water.service;

import com.water.dao.EmployeeDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 19:09 2018/6/21
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private FactoryService factoryService;

    @Test
    public void test1() {
        Map map = new HashMap();
        map.put("limit",1);
        map.put("page",1);

        System.out.println(factoryService.queryList(map));
    }

}
