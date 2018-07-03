package com.water.dao;

import com.water.domain.IdAndNameDTO;
import com.water.domain.Hall;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HallDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<Hall> queryList(Map<String, Object> map);

    List<IdAndNameDTO> selectFactoryMessage(Map<String, Object> map);

    int insertSelective(Hall record);

    Hall selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hall record);

}