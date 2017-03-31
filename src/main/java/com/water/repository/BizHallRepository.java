package com.water.repository;

import com.water.domain.BizHall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by 周美华 on 2017/3/30.
 */
public interface BizHallRepository extends JpaRepository<BizHall, Integer> {

//    @Query("select  bh from BizHall bh where bh.companyId = ?1")
    Page<BizHall> findByCompanyId(Integer companyId, Pageable pageable);

//    int countByCompanyId(Integer companyId);
}
