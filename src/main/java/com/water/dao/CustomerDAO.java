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

    Customer getIdByCustCode(String code);

    int updateByPrimaryKeySelective(Customer record);

    List<IdAndNameDTO> selectArchiveMessage(Map<String, Object> map);

    /**
      * @Author : 林吉达
      * @Description : 批量插入
      * @Date : 14:55 2018/8/9
      */
    int insertBatch(List<Customer> list);

    /**
      * @Author : 林吉达
      * @Description : 根据List<Code> 插入批量的customerId
      * @Date : 11:46 2018/8/10
      */
     List<Customer> getIdAndCode(List list);
}