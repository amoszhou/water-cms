package com.water.repository;

import com.water.domain.CustomerMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
public interface CustomerMeterRepository extends JpaRepository<CustomerMeter, Integer> {

    List<CustomerMeter> findByIsDelete(Integer isDelete);
}
