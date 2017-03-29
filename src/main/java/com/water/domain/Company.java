package com.water.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/29.
 */
@Entity
@Table(name = "t_company")
public class Company {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @Basic
    private String name;

    @Basic
    @Column
    private String manager;

    @Basic
    @Column
    private String tel;

    @Column
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
