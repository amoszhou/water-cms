package com.water.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Customer implements Serializable {
    private Integer id;
   //地址
    private String address;
    //表册ID
    private String archiveId;
    //表册名
    private String archiveName;
    //片区ID
    private Integer areaId;
    //编码
    private String code;
    //创建时间
    private LocalDateTime createTime;
    //创建人
    private Integer createUser;
    //水厂ID
    private Integer factoryId;
    //水厂名
    private String factoryName;
    //家庭账户ID
    private Integer familyCount;
    //
    private Integer frequency;
    //片区ID
    private Integer hallId;
    //身份证
    private String idCard;
    //
    private Integer isDelete;
    //名称
    private String name;
    //电话
    private String phone;
    //电话
    private String tel;
    //更新时间
    private LocalDateTime updateTime;
    //更新人
    private Integer updateUser;

    private String createTimeForHTML;

    private String updateTimeForHTML;

    public String getCreateTimeForHTML() {
        return createTimeForHTML;
    }

    public void setCreateTimeForHTML(String createTimeForHTML) {
        this.createTimeForHTML = createTimeForHTML;
    }

    public String getUpdateTimeForHTML() {
        return updateTimeForHTML;
    }

    public void setUpdateTimeForHTML(String updateTimeForHTML) {
        this.updateTimeForHTML = updateTimeForHTML;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTimeForHTML = createTime.toString();
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Integer familyCount) {
        this.familyCount = familyCount;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTimeForHTML = updateTime.toString();
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
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
        Customer other = (Customer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getArchiveId() == null ? other.getArchiveId() == null : this.getArchiveId().equals(other.getArchiveId()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getFactoryId() == null ? other.getFactoryId() == null : this.getFactoryId().equals(other.getFactoryId()))
            && (this.getFamilyCount() == null ? other.getFamilyCount() == null : this.getFamilyCount().equals(other.getFamilyCount()))
            && (this.getFrequency() == null ? other.getFrequency() == null : this.getFrequency().equals(other.getFrequency()))
            && (this.getHallId() == null ? other.getHallId() == null : this.getHallId().equals(other.getHallId()))
            && (this.getIdCard() == null ? other.getIdCard() == null : this.getIdCard().equals(other.getIdCard()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getArchiveId() == null) ? 0 : getArchiveId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getFactoryId() == null) ? 0 : getFactoryId().hashCode());
        result = prime * result + ((getFamilyCount() == null) ? 0 : getFamilyCount().hashCode());
        result = prime * result + ((getFrequency() == null) ? 0 : getFrequency().hashCode());
        result = prime * result + ((getHallId() == null) ? 0 : getHallId().hashCode());
        result = prime * result + ((getIdCard() == null) ? 0 : getIdCard().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", address=").append(address);
        sb.append(", archiveId=").append(archiveId);
        sb.append(", areaId=").append(areaId);
        sb.append(", code=").append(code);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", factoryId=").append(factoryId);
        sb.append(", familyCount=").append(familyCount);
        sb.append(", frequency=").append(frequency);
        sb.append(", hallId=").append(hallId);
        sb.append(", idCard=").append(idCard);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", tel=").append(tel);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}