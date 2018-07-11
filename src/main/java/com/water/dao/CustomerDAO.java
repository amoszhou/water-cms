package com.water.dao;

import com.water.domain.Customer;
import com.water.domain.IdAndNameDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<Customer> queryList(Map<String, Object> map);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    List<IdAndNameDTO> selectArchiveMessage(Map<String, Object> map);


}