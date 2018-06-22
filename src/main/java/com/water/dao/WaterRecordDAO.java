package com.water.dao;

import com.water.domain.WaterRecord;

public interface WaterRecordDAO {
    int insert(WaterRecord record);

    int insertSelective(WaterRecord record);

    WaterRecord selectByPrimaryKey(Integer id);
}