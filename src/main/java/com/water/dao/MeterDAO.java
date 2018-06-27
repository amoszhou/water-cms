package com.water.dao;

import com.water.domain.Meter;

public interface MeterDAO {


    int insertSelective(Meter record);

    Meter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Meter record);


}