package com.water.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/11.
 */
@Entity
@Table(name = "t_customer_account")
public class CustomerAccount {


    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "cust_id")
    private Integer custId;

    @Column
    private Integer balance;

    @Column
    private Integer version;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user")
    private Integer updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}
