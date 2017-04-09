package com.water.repository;

import com.water.domain.ChargeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/4/9.
 */
public interface ChargeRecordRepository extends JpaRepository<ChargeRecord, Integer> {
}
