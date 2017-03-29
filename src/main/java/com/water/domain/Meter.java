package com.water.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 水表
 * Created by Administrator on 2017/3/29.
 */
@Entity
@Table(name="t_meter")
public class Meter {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String brand;

    @Column
    private String name;

    /**型号*/
    @Column(length = 15)
    private String model;

    /**规格*/
    @Column(length = 10)
    private String specification;

    /**口径*/
    @Column(length = 3)
    private Integer size;

    @Column(name="create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
