package com.water.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class PayRecord implements Serializable {
    private Integer id;
    //用水记录ID
    private Integer waterRecordId;
    //缴费状态（-1为未缴费，1为缴费）
    private Integer payState;
    //消费者id
    private Integer customerId;
    //消费者Code
    private String customerCode;
    //产生水费开始时间
    private LocalDate waterBeginDate;
    //产生水费结束时间
    private LocalDate waterEndDate;
    //支付方式(1支付宝，2微信，3现金,4账号余额,-1暂未缴费)
    private Integer payType;
    //正常水费
    private BigDecimal waterFee;
    //污水费
    private BigDecimal sewageFee;
    //滞纳金
    private BigDecimal lateFee;
    //汇总费用
    private BigDecimal totalFee;
    //
    private String waterRecordCode;
    //
    private Integer factoryId;
    //
    private String factoryName;

    private String waterRecordBeginDateForHtml;
    private Integer isDelete;

    private String waterRecordEndDateForHtml;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getWaterRecordBeginDateForHtml() {
        return waterRecordBeginDateForHtml;
    }

    public void setWaterRecordBeginDateForHtml(String waterRecordBeginDateForHtml) {
        this.waterRecordBeginDateForHtml = waterRecordBeginDateForHtml;
    }

    public String getWaterRecordEndDateForHtml() {
        return waterRecordEndDateForHtml;
    }

    public void setWaterRecordEndDateForHtml(String waterRecordEndDateForHtml) {
        this.waterRecordEndDateForHtml = waterRecordEndDateForHtml;
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

    public Integer getWaterRecordId() {
        return waterRecordId;
    }

    public void setWaterRecordId(Integer waterRecordId) {
        this.waterRecordId = waterRecordId;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getWaterBeginDate() {
        return waterBeginDate;
    }

    public void setWaterBeginDate(LocalDate waterBeginDate) {
          waterRecordBeginDateForHtml = waterBeginDate.toString();
        this.waterBeginDate = waterBeginDate;
    }

    public LocalDate getWaterEndDate() {
        return waterEndDate;
    }


    public void setWaterEndDate(LocalDate waterEndDate) {
        waterRecordEndDateForHtml = waterEndDate.toString();
        this.waterEndDate = waterEndDate;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getWaterRecordCode() {
        return waterRecordCode;
    }

    public void setWaterRecordCode(String waterRecordCode) {
        this.waterRecordCode = waterRecordCode;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
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
        PayRecord other = (PayRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWaterRecordId() == null ? other.getWaterRecordId() == null : this.getWaterRecordId().equals(other.getWaterRecordId()))
            && (this.getPayState() == null ? other.getPayState() == null : this.getPayState().equals(other.getPayState()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getWaterBeginDate() == null ? other.getWaterBeginDate() == null : this.getWaterBeginDate().equals(other.getWaterBeginDate()))
            && (this.getWaterEndDate() == null ? other.getWaterEndDate() == null : this.getWaterEndDate().equals(other.getWaterEndDate()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getWaterFee() == null ? other.getWaterFee() == null : this.getWaterFee().equals(other.getWaterFee()))
            && (this.getSewageFee() == null ? other.getSewageFee() == null : this.getSewageFee().equals(other.getSewageFee()))
            && (this.getLateFee() == null ? other.getLateFee() == null : this.getLateFee().equals(other.getLateFee()))
            && (this.getTotalFee() == null ? other.getTotalFee() == null : this.getTotalFee().equals(other.getTotalFee()))
            && (this.getWaterRecordCode() == null ? other.getWaterRecordCode() == null : this.getWaterRecordCode().equals(other.getWaterRecordCode()))
            && (this.getFactoryId() == null ? other.getFactoryId() == null : this.getFactoryId().equals(other.getFactoryId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWaterRecordId() == null) ? 0 : getWaterRecordId().hashCode());
        result = prime * result + ((getPayState() == null) ? 0 : getPayState().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getWaterBeginDate() == null) ? 0 : getWaterBeginDate().hashCode());
        result = prime * result + ((getWaterEndDate() == null) ? 0 : getWaterEndDate().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getWaterFee() == null) ? 0 : getWaterFee().hashCode());
        result = prime * result + ((getSewageFee() == null) ? 0 : getSewageFee().hashCode());
        result = prime * result + ((getLateFee() == null) ? 0 : getLateFee().hashCode());
        result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());
        result = prime * result + ((getWaterRecordCode() == null) ? 0 : getWaterRecordCode().hashCode());
        result = prime * result + ((getFactoryId() == null) ? 0 : getFactoryId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", waterRecordId=").append(waterRecordId);
        sb.append(", payState=").append(payState);
        sb.append(", customerId=").append(customerId);
        sb.append(", waterBeginDate=").append(waterBeginDate);
        sb.append(", waterEndDate=").append(waterEndDate);
        sb.append(", payType=").append(payType);
        sb.append(", waterFee=").append(waterFee);
        sb.append(", sewageFee=").append(sewageFee);
        sb.append(", lateFee=").append(lateFee);
        sb.append(", totalFee=").append(totalFee);
        sb.append(", waterRecordCode=").append(waterRecordCode);
        sb.append(", factoryId=").append(factoryId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}