package com.water.dao;

import com.water.domain.ChargeRecord;

public interface ChargeRecordDAO {


    int insertSelective(ChargeRecord record);

    ChargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChargeRecord record);


}