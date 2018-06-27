package com.water.dao;

import com.water.domain.Area;

public interface AreaDAO {


    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);


}