package com.water.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Archive implements Serializable {
    private Integer id;

    private String createTimeForHtml;

    private Integer areaId;

    private String code;

    private LocalDateTime createTime;

    private Integer hallId;

    private String name;

    private Integer recordUser;

    private Integer isDelete;

    private String hallName;

    private String areaName;

    private Integer factoryId;

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getCreateTimeForHtml() {
        return createTimeForHtml;
    }

    public void setCreateTimeForHtml(String createTimeForHtml) {
        this.createTimeForHtml = createTimeForHtml;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.createTimeForHtml = createTime.toString();
        this.createTime = createTime;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(Integer recordUser) {
        this.recordUser = recordUser;
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
        Archive other = (Archive) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getHallId() == null ? other.getHallId() == null : this.getHallId().equals(other.getHallId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRecordUser() == null ? other.getRecordUser() == null : this.getRecordUser().equals(other.getRecordUser()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getHallId() == null) ? 0 : getHallId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRecordUser() == null) ? 0 : getRecordUser().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "id=" + id +
                ", createTimeForHtml='" + createTimeForHtml + '\'' +
                ", areaId=" + areaId +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                ", hallId=" + hallId +
                ", name='" + name + '\'' +
                ", recordUser=" + recordUser +
                ", isDelete=" + isDelete +
                ", hallName='" + hallName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", factoryId=" + factoryId +
                '}';
    }
}