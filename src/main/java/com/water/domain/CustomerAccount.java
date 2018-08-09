package com.water.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomerAccount implements Serializable {
    private Integer id;

    private BigDecimal balance;

    private Integer custId;

    private LocalDateTime updateTime;

    private Integer updateUser;

    private Integer version;

    private Integer isDelete;

    private String customerName;

    private String updateTimeForHTML;

    private BigDecimal raiseMoney;

    private Integer factoryId;

    private String custCode;

    private String updateUserName;

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public BigDecimal getRaiseMoney() {
        return raiseMoney;
    }

    public void setRaiseMoney(BigDecimal raiseMoney) {
        this.raiseMoney = raiseMoney;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUpdateTimeForHTML() {
        return updateTimeForHTML;
    }

    public void setUpdateTimeForHTML(String updateTimeForHTML) {
        this.updateTimeForHTML = updateTimeForHTML;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        setUpdateTimeForHTML(updateTime.toString());
        this.updateTime = updateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
        CustomerAccount other = (CustomerAccount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getCustId() == null ? other.getCustId() == null : this.getCustId().equals(other.getCustId()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getCustId() == null) ? 0 : getCustId().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", custId=" + custId +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", version=" + version +
                ", isDelete=" + isDelete +
                ", customerName='" + customerName + '\'' +
                ", updateTimeForHTML='" + updateTimeForHTML + '\'' +
                ", raiseMoney=" + raiseMoney +
                ", factoryId=" + factoryId +
                ", custCode='" + custCode + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                '}';
    }
}