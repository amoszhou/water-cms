package com.water.dao;

import com.water.domain.Factory;

public interface FactoryDAO {

    int insertSelective(Factory record);

    Factory selectByPrimaryKey(Integer id);
}