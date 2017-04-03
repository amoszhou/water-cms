package com.water.repository;

import com.water.domain.WaterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
public interface WaterRecordRepository extends JpaRepository<WaterRecord, Integer> {

    List<WaterRecord> findByPeriodAndCustCode(String period, String custCode);

    @Modifying(clearAutomatically = true)
    @Query("update WaterRecord r set r.currNumber = ?1 , r.updateUser = ?2, r.updateTime =?3 where r.id = ?4 ")
    int updateCurrentNumber(int currNumber, int updateUser, Date date, Integer id);
}
