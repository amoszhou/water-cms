package com.water.dao;

import com.water.domain.Customer;

public interface CustomerDAO {

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);
}