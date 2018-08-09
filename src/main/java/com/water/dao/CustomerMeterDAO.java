package com.water.dao;

import com.water.domain.Customer;
import com.water.domain.CustomerMeter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMeterDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<CustomerMeter> queryList(Map<String, Object> map);

    int insertSelective(CustomerMeter record);

    CustomerMeter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerMeter record);

    Integer getIdByCustId(Integer custId);

    int insertBatch(List<Customer> list);

}