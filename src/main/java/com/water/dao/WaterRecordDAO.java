package com.water.dao;

import com.water.domain.WaterRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WaterRecordDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<WaterRecord> queryList(Map<String, Object> map);

    int insertSelective(WaterRecord record);

    WaterRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaterRecord record);

    int insertBatch(List list);
}