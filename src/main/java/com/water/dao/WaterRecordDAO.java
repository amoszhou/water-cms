package com.water.dao;

import com.water.domain.WaterRecord;

public interface WaterRecordDAO {

    int insertSelective(WaterRecord record);

    WaterRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaterRecord record);


}