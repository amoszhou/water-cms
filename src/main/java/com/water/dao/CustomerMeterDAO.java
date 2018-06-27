package com.water.dao;

import com.water.domain.CustomerMeter;

public interface CustomerMeterDAO {

    int insertSelective(CustomerMeter record);

    CustomerMeter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerMeter record);


}