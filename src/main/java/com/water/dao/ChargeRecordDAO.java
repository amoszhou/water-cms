package com.water.dao;

import com.water.domain.ChargeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChargeRecordDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<ChargeRecord> queryList(Map<String, Object> map);

    int insertSelective(ChargeRecord record);

    ChargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChargeRecord record);


}