package com.water.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class WaterRecord implements Serializable {
    private Integer id;
    //消费记录ID   点击缴费的时候，生成一条消费记录。（若不是现金支付需要检查余额）
    private Integer chargeId;

    //水表显示数字
    private BigDecimal currNumber;
    //上一次水表显示数字
    private BigDecimal lastNumber;
    //消费者Code
    private String custCode;
    //消费者ID
    private Integer custId;
    //消费者名称
    private String customerName;
    //总费用
    private BigDecimal fee;
    //水表Id
    private Integer meterId;
    //水表名
    private String meterName;
    //水厂ID
    private Integer factoryId;
    //水厂名
    private String factoryName;
    //支付方式
    private Integer pay;

    private String code;
    //单次用水记录的用水量 水表显示数字-上一次水表显示数字  currNumber - lastNumber
    private BigDecimal usedWaterRecord;


    //用水记录开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate waterRecordBeginDate;
    //用水记录结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate waterRecordEndDate;

    private String waterRecordBeginDateForHtml;

    private String waterRecordEndDateForHtml;

    //
    private Integer createUser;

    private String createUserName;
    //
    private LocalDateTime updateTime;

    private String updateTimeForHTML;
    //
    private Integer updateUser;

    private String updateUserName;
    //
    private Integer isDelete;
    //
    private LocalDateTime createTime;

    private String createTimeForHTML;

    public void setCreateTime(LocalDateTime createTime) {
        this.createTimeForHTML = createTime.toString();
        this.createTime = createTime;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public String getCreateTimeForHTML() {
        return createTimeForHTML;
    }

    public void setCreateTimeForHTML(String createTimeForHTML) {
        this.createTimeForHTML = createTimeForHTML;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeForHTML() {
        return updateTimeForHTML;
    }

    public void setUpdateTimeForHTML(String updateTimeForHTML) {
        this.updateTimeForHTML = updateTimeForHTML;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    private static final long serialVersionUID = 1L;

    public BigDecimal getUsedWaterRecord() {
        return usedWaterRecord;
    }

    public void setUsedWaterRecord(BigDecimal usedWaterRecord) {
        this.usedWaterRecord = usedWaterRecord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public BigDecimal getCurrNumber() {
        return currNumber;
    }

    public void setCurrNumber(BigDecimal currNumber) {
        this.currNumber = currNumber;
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


    public BigDecimal getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(BigDecimal lastNumber) {
        this.lastNumber = lastNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public LocalDate getWaterRecordBeginDate() {
        return waterRecordBeginDate;
    }
    public LocalDate getWaterRecordEndDate() {
        return waterRecordEndDate;
    }
    public void setWaterRecordBeginDate(LocalDate waterRecordBeginDate) {
        waterRecordBeginDateForHtml = waterRecordBeginDate.toString();
        this.waterRecordBeginDate = waterRecordBeginDate;
    }



    public void setWaterRecordEndDate(LocalDate waterRecordEndDate) {
        waterRecordEndDateForHtml = waterRecordEndDate.toString();
        this.waterRecordEndDate = waterRecordEndDate;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        WaterRecord other = (WaterRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getChargeId() == null ? other.getChargeId() == null : this.getChargeId().equals(other.getChargeId()))
                && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
                && (this.getCurrNumber() == null ? other.getCurrNumber() == null : this.getCurrNumber().equals(other.getCurrNumber()))
                && (this.getCustCode() == null ? other.getCustCode() == null : this.getCustCode().equals(other.getCustCode()))
                && (this.getCustId() == null ? other.getCustId() == null : this.getCustId().equals(other.getCustId()))
                && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
                && (this.getLastNumber() == null ? other.getLastNumber() == null : this.getLastNumber().equals(other.getLastNumber()))
                && (this.getMeterId() == null ? other.getMeterId() == null : this.getMeterId().equals(other.getMeterId()))
                && (this.getPay() == null ? other.getPay() == null : this.getPay().equals(other.getPay()))
                && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getChargeId() == null) ? 0 : getChargeId().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCurrNumber() == null) ? 0 : getCurrNumber().hashCode());
        result = prime * result + ((getCustCode() == null) ? 0 : getCustCode().hashCode());
        result = prime * result + ((getCustId() == null) ? 0 : getCustId().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getLastNumber() == null) ? 0 : getLastNumber().hashCode());
        result = prime * result + ((getMeterId() == null) ? 0 : getMeterId().hashCode());
        result = prime * result + ((getPay() == null) ? 0 : getPay().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "WaterRecord{" +
                "id=" + id +
                ", chargeId=" + chargeId +
                ", currNumber=" + currNumber +
                ", lastNumber=" + lastNumber +
                ", custCode='" + custCode + '\'' +
                ", custId=" + custId +
                ", customerName='" + customerName + '\'' +
                ", fee=" + fee +
                ", meterId=" + meterId +
                ", meterName='" + meterName + '\'' +
                ", factoryId=" + factoryId +
                ", factoryName='" + factoryName + '\'' +
                ", pay=" + pay +
                ", code='" + code + '\'' +
                ", waterRecordBeginDate=" + waterRecordBeginDate +
                ", waterRecordEndDate=" + waterRecordEndDate +
                ", createUser=" + createUser +
                ", createUserName='" + createUserName + '\'' +
                ", updateTime=" + updateTime +
                ", updateTimeForHTML='" + updateTimeForHTML + '\'' +
                ", updateUser=" + updateUser +
                ", updateUserName='" + updateUserName + '\'' +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", createTimeForHTML='" + createTimeForHTML + '\'' +
                '}';
    }
}