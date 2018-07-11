package com.water.dao;

import com.water.domain.Archive;
import com.water.domain.IdAndNameDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArchiveDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<Archive> queryList(Map<String, Object> map);

    int insertSelective(Archive record);

    Archive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Archive record);

    List<IdAndNameDTO> selectAreaMessage(Map<String, Object> map);

    List<IdAndNameDTO> selectHallMessage(Map<String, Object> map);


}