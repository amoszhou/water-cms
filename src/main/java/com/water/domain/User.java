package com.water.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/3/30.
 */
//@Entity
//@Table(name="t_user")
public class User {

    private Integer id;

    private Integer companyId;


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
