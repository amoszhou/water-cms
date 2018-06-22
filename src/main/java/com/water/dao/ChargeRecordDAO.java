package com.water.dao;

import com.water.domain.ChargeRecord;

public interface ChargeRecordDAO {
    int insert(ChargeRecord record);

    int insertSelective(ChargeRecord record);

    ChargeRecord selectByPrimaryKey(Integer id);
}