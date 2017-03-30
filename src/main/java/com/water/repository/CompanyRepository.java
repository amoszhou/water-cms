package com.water.repository;

import com.water.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 周美华 on 2017/3/30.
 */
public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
