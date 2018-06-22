package com.water.dao;

import com.water.domain.BizHall;

public interface BizHallDAO {
    int insert(BizHall record);

    int insertSelective(BizHall record);

    BizHall selectByPrimaryKey(Integer id);
}