package com.water.dao;

import com.water.domain.Factory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FactoryDAO {


    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<Factory> queryList(Map<String, Object> map);



    int insertSelective(Factory record);

    Factory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Factory record);


}