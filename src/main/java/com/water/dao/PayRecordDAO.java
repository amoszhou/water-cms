package com.water.dao;

import com.water.domain.PayRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PayRecordDAO {


    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<PayRecord> queryList(Map<String, Object> map);

    int insertSelective(PayRecord record);

    PayRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PayRecord record);


}