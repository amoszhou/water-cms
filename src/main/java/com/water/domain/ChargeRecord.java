package com.water.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 充值记录
 * Created by Administrator on 2017/3/26.
 */
@Entity
@Table(name = "t_charge_record")
public class ChargeRecord {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "cust_id")
    private Integer custId;

    @Column(name = "cust_code")
    private String custCode;

    @Column
    private Integer amount;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user")
    private Integer createUser;

    /**
     * 发票编号
     */
    @Column(name = "invoice_code")
    private String invoiceCode;
    /**
     * 支付方式
     */
    @Column(name = "pay_type")
    private Integer payType;


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

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
