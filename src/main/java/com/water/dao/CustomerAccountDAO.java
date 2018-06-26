package com.water.dao;

import com.water.domain.CustomerAccount;

public interface CustomerAccountDAO {

    int insertSelective(CustomerAccount record);

    CustomerAccount selectByPrimaryKey(Integer id);
}