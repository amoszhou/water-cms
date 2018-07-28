package com.water.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class CustomerMeter implements Serializable {
    //1---有效期内  2--已过期
    private Integer validDate;

    private Integer id;

    private String code;

    private String custCode;

    private Integer custId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate enableDate;

    private Integer isDelete;

    private Integer priceType;

    private String size;

    private String customerName;

    private String meterName;

    private String factoryName;

    private BigDecimal waterFee;

    private BigDecimal sewageFee;

    private String enableDateForHtml;

    private Integer meterId;

    private Integer factoryId;

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public BigDecimal getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(BigDecimal waterFee) {
        this.waterFee = waterFee;
    }

    public BigDecimal getSewageFee() {
        return sewageFee;
    }

    public void setSewageFee(BigDecimal sewageFee) {
        this.sewageFee = sewageFee;
    }

    public String getEnableDateForHtml() {
        return enableDateForHtml;
    }

    public void setEnableDateForHtml(String enableDateForHtml) {
        this.enableDateForHtml = enableDateForHtml;
    }

    public String getCustomerName() {
        return customerName;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public LocalDate getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(LocalDate enableDate) {
        this.enableDateForHtml = enableDate.toString();
        this.enableDate = enableDate;
    }

    public Integer getValidDate() {
        return validDate;
    }

    public void setValidDate(Integer validDate) {
        this.validDate = validDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CustomerMeter other = (CustomerMeter) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getCustCode() == null ? other.getCustCode() == null : this.getCustCode().equals(other.getCustCode()))
            && (this.getCustId() == null ? other.getCustId() == null : this.getCustId().equals(other.getCustId()))
            && (this.getEnableDate() == null ? other.getEnableDate() == null : this.getEnableDate().equals(other.getEnableDate()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getPriceType() == null ? other.getPriceType() == null : this.getPriceType().equals(other.getPriceType()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getCustCode() == null) ? 0 : getCustCode().hashCode());
        result = prime * result + ((getCustId() == null) ? 0 : getCustId().hashCode());
        result = prime * result + ((getEnableDate() == null) ? 0 : getEnableDate().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getPriceType() == null) ? 0 : getPriceType().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CustomerMeter{" +
                "validDate=" + validDate +
                ", id=" + id +
                ", code='" + code + '\'' +
                ", custCode='" + custCode + '\'' +
                ", custId=" + custId +
                ", enableDate=" + enableDate +
                ", isDelete=" + isDelete +
                ", priceType=" + priceType +
                ", size='" + size + '\'' +
                ", customerName='" + customerName + '\'' +
                ", meterName='" + meterName + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", waterFee=" + waterFee +
                ", sewageFee=" + sewageFee +
                ", enableDateForHtml='" + enableDateForHtml + '\'' +
                ", meterId=" + meterId +
                ", factoryId=" + factoryId +
                '}';
    }
}