package com.water.service;

import com.water.domain.Customer;
import com.water.domain.CustomerMeter;
import com.water.repository.CustomerMeterRepository;
import com.water.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基础数据管理
 * Created by 周美华 on 2017/4/1.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerMeterRepository customerMeterRepository;

    /**
     * 查询所有可用的用户
     *
     * @return
     */
    public List<Customer> findAllEnableCustomer() {
        // TODO: 2017/4/3 status需要定义
        return customerRepository.findByIsDelete(1);
    }

    /**
     * 查询所有可用的水表
     *
     * @return
     */
    public List<CustomerMeter> findAllEnableMeter() {
        // TODO: 2017/4/3 status需要定义
        return customerMeterRepository.findByIsDelete(1);
    }

}
