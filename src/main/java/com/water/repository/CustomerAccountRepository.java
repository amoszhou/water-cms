package com.water.repository;

import com.water.domain.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/4/11.
 */
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer> {

    CustomerAccount findByCustId(Integer custId);

    @Modifying(clearAutomatically = true)
    @Query("update CustomerAccount ca set ca.balance = ca.balance + ?1,ca.version = ca.version+1,ca.updateUser = ?2 where ca.custId =?3 and ca.version=?4 ")
    int updateBalance(int cent, int operator, int custId, int version);
}
