package com.water.dao;

import com.water.domain.Area;
import com.water.domain.Factory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AreaDAO {
    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<Area> queryList(Map<String, Object> map);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

}