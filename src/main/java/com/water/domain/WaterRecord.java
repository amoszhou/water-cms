package com.water.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 用水记录
 * Created by Administrator on 2017/3/26.
 */
@Entity
@Table(name = "t_water_record")
public class WaterRecord {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "cust_id")
    private Integer custId;

    @ManyToOne
    @JoinColumn(name = "cust_id", insertable = false, updatable = false)
    private Customer customer;

    /**
     * 客户编码
     */
    @Column(name = "cust_code")
    private String custCode;
    /**
     * 水表编码
     */
    @Column(name = "meter_code")
    private Integer meterCode;

    /**
     * 月份
     */
    @Column
    private String period;
    /**
     * 上月表数
     */
    @Column(name = "last_number")
    private Integer lastNumber;
    /**
     * 本月表数
     */
    @Column(name = "curr_number")
    private Integer currNumber;

    /**
     * 产生的费用
     */
    @Column
    private Integer fee;
    /**
     * 是否已经支付
     * 0--未支付
     * 1--已支付
     */
    @Column
    private Integer pay;
    /**
     * 缴费记录
     */
    @Column(name = "charge_id")
    private Integer chargeId;

    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "create_user")
    private Integer createUser;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(Integer lastNumber) {
        this.lastNumber = lastNumber;
    }

    public Integer getCurrNumber() {
        return currNumber;
    }

    public void setCurrNumber(Integer currNumber) {
        this.currNumber = currNumber;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Integer getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(Integer meterCode) {
        this.meterCode = meterCode;
    }
}
