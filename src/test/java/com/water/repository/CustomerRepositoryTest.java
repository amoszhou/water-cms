package com.water.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.water.WaterCmsApplicationTests;
import com.water.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class CustomerRepositoryTest extends WaterCmsApplicationTests{

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCreate(){
        Customer c = new Customer();
        c.setCode("A0002");
        c.setCreateUser(1);
        c.setName("scala");
        c.setAddress("深圳市宝安区西乡");
        c.setAreaId(1);
        c.setBalance(0);
        c.setPriceType(1);
        c.setTel("13888888888");

        customerRepository.createCustomer(c);
    }

    @Test
    public void testFindAll(){
        Page<Customer> page = PageHelper.startPage(1,10);
        customerRepository.pageFindAll(null);
        System.out.println("===Customer size : " + page.size());
        System.out.println("===Customer(0) name : " + page.get(0).getName());

    }
}
