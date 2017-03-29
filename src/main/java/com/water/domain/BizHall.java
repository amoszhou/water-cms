package com.water.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/29.
 */
@Entity
@Table(name = "t_bizhall")
public class BizHall {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="company_id")
    private Integer companyId;
    @ManyToOne
    @JoinColumn(name = "company_id",insertable=false , updatable=false,foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private Company company;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String manager;

    @Column
    private String tel;

    @Column(name = "is_delete")
    private Integer isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
