package com.water.dao;

import com.water.domain.Factory;
import com.water.domain.Meter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MeterDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<Meter> queryList(Map<String, Object> map);

    int insertSelective(Meter record);

    Meter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Meter record);


}