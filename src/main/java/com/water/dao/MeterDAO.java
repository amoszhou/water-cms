package com.water.dao;

import com.water.domain.Meter;

public interface MeterDAO {
    int insert(Meter record);

    int insertSelective(Meter record);

    Meter selectByPrimaryKey(Integer id);
}