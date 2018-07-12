package com.water.dao;

import com.water.domain.PriceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PriceTypeDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<PriceType> queryList(Map<String, Object> map);

    int insertSelective(PriceType record);

    PriceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PriceType record);

}