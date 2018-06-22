package com.water.domain;

import java.io.Serializable;
import java.util.Date;

public class WaterRecord implements Serializable {
    private Integer id;

    private Integer chargeId;

    private Date createTime;

    private Integer createUser;

    private Integer currNumber;

    private String custCode;

    private Integer custId;

    private Integer fee;

    private Integer lastNumber;

    private Integer meterCode;

    private Integer pay;

    private String period;

    private Date updateTime;

    private Integer updateUser;

    private Integer isDelete;

    private static final long serialVersionUID = 1L;

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

    public Integer getCurrNumber() {
        return currNumber;
    }

    public void setCurrNumber(Integer currNumber) {
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

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(Integer lastNumber) {
        this.lastNumber = lastNumber;
    }

    public Integer getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(Integer meterCode) {
        this.meterCode = meterCode;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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
        WaterRecord other = (WaterRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getChargeId() == null ? other.getChargeId() == null : this.getChargeId().equals(other.getChargeId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCurrNumber() == null ? other.getCurrNumber() == null : this.getCurrNumber().equals(other.getCurrNumber()))
            && (this.getCustCode() == null ? other.getCustCode() == null : this.getCustCode().equals(other.getCustCode()))
            && (this.getCustId() == null ? other.getCustId() == null : this.getCustId().equals(other.getCustId()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getLastNumber() == null ? other.getLastNumber() == null : this.getLastNumber().equals(other.getLastNumber()))
            && (this.getMeterCode() == null ? other.getMeterCode() == null : this.getMeterCode().equals(other.getMeterCode()))
            && (this.getPay() == null ? other.getPay() == null : this.getPay().equals(other.getPay()))
            && (this.getPeriod() == null ? other.getPeriod() == null : this.getPeriod().equals(other.getPeriod()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getChargeId() == null) ? 0 : getChargeId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCurrNumber() == null) ? 0 : getCurrNumber().hashCode());
        result = prime * result + ((getCustCode() == null) ? 0 : getCustCode().hashCode());
        result = prime * result + ((getCustId() == null) ? 0 : getCustId().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getLastNumber() == null) ? 0 : getLastNumber().hashCode());
        result = prime * result + ((getMeterCode() == null) ? 0 : getMeterCode().hashCode());
        result = prime * result + ((getPay() == null) ? 0 : getPay().hashCode());
        result = prime * result + ((getPeriod() == null) ? 0 : getPeriod().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", chargeId=").append(chargeId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", currNumber=").append(currNumber);
        sb.append(", custCode=").append(custCode);
        sb.append(", custId=").append(custId);
        sb.append(", fee=").append(fee);
        sb.append(", lastNumber=").append(lastNumber);
        sb.append(", meterCode=").append(meterCode);
        sb.append(", pay=").append(pay);
        sb.append(", period=").append(period);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}