package com.water.dao;

import com.water.domain.PriceType;

public interface PriceTypeDAO {
    int insert(PriceType record);

    int insertSelective(PriceType record);

    PriceType selectByPrimaryKey(Integer id);
}