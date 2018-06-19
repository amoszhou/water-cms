package com.water.domain;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/30.
 */
@Entity
@Table(name = "t_employee")
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String realName;
    @Column
    private String telPhone;
    @Column
    private Integer factoryId;

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }
}
