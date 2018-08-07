package com.water.dao;

import com.water.domain.CustomerAccount;
import com.water.domain.IdAndNameDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerAccountDAO {

    int updateDeleteState(@Param("id") Long id);

    int queryTotal(Map<String, Object> map);

    List<CustomerAccount> queryList(Map<String, Object> map);

    int insertSelective(CustomerAccount record);

    CustomerAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerAccount record);

    List<IdAndNameDTO> selectCustomerMessage(Map<String, Object> map);

    int selectByCustId(Integer customerId);

    CustomerAccount  getByCustId(Integer id);


}