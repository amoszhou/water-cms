package com.water.repository;

import com.water.domain.Customer;
import com.water.repository.provider.CustomerProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/26.
 */
@Mapper
public interface CustomerRepository {

    int createCustomer(Customer customer);

    Customer findById(int id);

    Customer findByCode(String code);

    List<Customer> pageFindAll(Map<String,Object> map);
}
