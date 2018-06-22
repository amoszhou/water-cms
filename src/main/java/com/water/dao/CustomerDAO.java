package com.water.dao;

import com.water.domain.Customer;

public interface CustomerDAO {
    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);
}