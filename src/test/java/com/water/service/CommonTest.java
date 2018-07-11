package com.water.service;

import com.water.dao.ArchiveDAO;
import com.water.dao.AreaDAO;
import com.water.dao.EmployeeDAO;
import com.water.util.SQLFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
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
    @Autowired
    private ArchiveDAO archiveDAO;


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
        Random r = new Random();
        int[] result = new int[4];
      for(int i = 0 ; i < 4 ; i ++){
       result[i] = r.nextInt(10);
      }
        for(int i = 0 ; i < 4 ; i ++){
            System.out.println(result[i]);
        }

     /*   Map map = new HashMap();
        archiveDAO.queryList(map);*/
    }
}
