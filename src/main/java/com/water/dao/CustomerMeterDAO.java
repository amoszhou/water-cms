package com.water.dao;

import com.water.domain.CustomerMeter;

public interface CustomerMeterDAO {
    int insert(CustomerMeter record);

    int insertSelective(CustomerMeter record);

    CustomerMeter selectByPrimaryKey(Integer id);
}