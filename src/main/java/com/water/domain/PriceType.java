package com.water.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceType implements Serializable {

    private Integer id;

    private Integer factoryId;

    private String name;

    private BigDecimal price;

    private BigDecimal sewage;

    private Integer isDelete;

    private String factoryName;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSewage() {
        return sewage;
    }

    public void setSewage(BigDecimal sewage) {
        this.sewage = sewage;
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
        PriceType other = (PriceType) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getFactoryId() == null ? other.getFactoryId() == null : this.getFactoryId().equals(other.getFactoryId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getSewage() == null ? other.getSewage() == null : this.getSewage().equals(other.getSewage()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFactoryId() == null) ? 0 : getFactoryId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getSewage() == null) ? 0 : getSewage().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "PriceType{" +
                "id=" + id +
                ", factoryId=" + factoryId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sewage=" + sewage +
                ", isDelete=" + isDelete +
                ", factoryName='" + factoryName + '\'' +
                ", type=" + type +
                '}';
    }
}