package com.water.repository;

import com.water.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/3/26.
 */
public interface CustomerRepository  extends JpaRepository<Customer,Integer>{

}
