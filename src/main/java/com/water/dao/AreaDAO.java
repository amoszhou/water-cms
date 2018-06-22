package com.water.dao;

import com.water.domain.Area;

public interface AreaDAO {
    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);
}