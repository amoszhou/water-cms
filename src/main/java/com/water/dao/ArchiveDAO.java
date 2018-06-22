package com.water.dao;

import com.water.domain.Archive;

public interface ArchiveDAO {
    int insert(Archive record);

    int insertSelective(Archive record);

    Archive selectByPrimaryKey(Integer id);
}