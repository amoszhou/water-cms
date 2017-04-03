package com.water.repository;

import com.water.domain.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/4/3.
 */
public interface MeterRepository extends JpaRepository<Meter,Integer> {
}
