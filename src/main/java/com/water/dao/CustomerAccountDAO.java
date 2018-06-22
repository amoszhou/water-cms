package com.water.dao;

import com.water.domain.CustomerAccount;

public interface CustomerAccountDAO {
    int insert(CustomerAccount record);

    int insertSelective(CustomerAccount record);

    CustomerAccount selectByPrimaryKey(Integer id);
}