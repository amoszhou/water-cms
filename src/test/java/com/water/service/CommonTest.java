package com.water.service;

import com.water.dao.EmployeeDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test1() {

        System.out.println(employeeDAO.selectByPrimaryKey(1));
    }

}
