package com.water.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户的水表
 * Created by Administrator on 2017/4/3.
 */
@Entity
@Table(name = "t_customer_meter")
public class CustomerMeter {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "cust_id")
    private Integer custId;

    @Column(name = "cust_code")
    private String custCode;

    /**
     * 水表编码
     */
    @Column
    private String code;

    /**
     * 水表口径
     */
    @Column
    private String size;

    /**
     * 安装日期
     */
    @Column(name = "enable_date")
    private Date enableDate;

    @Column(name = "price_type")
    private Integer priceType;

    @OneToOne
    @JoinColumn(name = "price_type", insertable = false, updatable = false)
    private PriceType priceTypeObj;

    @Column(name = "is_delete")
    private Integer isDelete;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public PriceType getPriceTypeObj() {
        return priceTypeObj;
    }

    public void setPriceTypeObj(PriceType priceTypeObj) {
        this.priceTypeObj = priceTypeObj;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
