package com.water.service;

import com.water.dao.CustomerDAO;
import com.water.domain.Customer;
import com.water.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 15:20 2018/8/8
 * @Modified By:
 */
@Service
public class CommonService {
    Logger logger = LoggerFactory.getLogger(CommonService.class);
    @Autowired
    private CustomerDAO customerDAO;

    public Integer getCustIdByCode(String custCode){
        Customer customer = customerDAO.getIdByCustCode(custCode);
        if(customer ==  null)
            throw new BizException("输入的顾客编码有误！无法找到该顾客！");
        return customer.getId();
    }

    public Customer getCustomerByCode(String custCode){
        Customer customer = customerDAO.getIdByCustCode(custCode);
        if(customer ==  null)
            throw new BizException("输入的顾客编码有误！无法找到该顾客！");
        return customer;
    }

}
