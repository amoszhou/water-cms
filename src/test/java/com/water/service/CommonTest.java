package com.water.service;

import com.water.dao.AreaDAO;
import com.water.dao.EmployeeDAO;
import com.water.util.SQLFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @Autowired
    private AreaDAO areaDAO;

    @Test
    public void test1() {
        Map map = new HashMap(2);
        /* map.put("limit", 1);
        map.put("page", 1);*/
        List<Integer> factoryIds = new ArrayList<>();
        factoryIds.add(1);
        map.put("factoryIds", factoryIds);
        System.out.println(areaDAO.queryList(map));
    }



    @Test
    public void test2() {



        BigDecimal decimal = BigDecimal.valueOf(0.2);
        System.out.println(decimal);
        BigDecimal setScale = decimal.setScale(2,BigDecimal.ROUND_HALF_DOWN);
        System.out.println(setScale);

    }
}
