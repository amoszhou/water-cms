package com.water.repository;

import com.water.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByCodeAndCompanyId(String code, Integer companyId);

    Page<Customer> findByCompanyId(Integer companyId, Pageable pageable);
}
