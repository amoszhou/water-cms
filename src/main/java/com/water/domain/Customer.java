package com.water.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户
 * Created by Administrator on 2017/3/26.
 */
@Entity
@Table(name = "t_customer")
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 用户编码
     */
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private Integer balance;
    @Column
    private String address;
    @Column
    private String tel;
    @Column
    private String phone;
    @Column
    private String idCard;

    @Column(name="company_id")
    private Integer companyId;

    @Column(name = "area_id")
    private Integer areaId;

    @ManyToOne
    @JoinColumn(name = "area_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Area area;

    @Column(name = "hall_id")
    private Integer hallId;

    @ManyToOne
    @JoinColumn(name = "hall_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private BizHall hall;

    /**
     * 缴费频率
     */
    @Column
    private Integer frequency;
    /**
     * 家庭成员人数
     */
    @Column
    private Integer familyCount;

    @Column(name = "archive_id")
    private String archiveId;
    @ManyToOne
    @JoinColumn(name = "archive_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Archive archive;

    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "create_user")
    private Integer createUser;
    @Column(name = "update_user")
    private Integer updateUser;
    @Column(name = "is_delete")
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Integer familyCount) {
        this.familyCount = familyCount;
    }

    public String getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public BizHall getHall() {
        return hall;
    }

    public void setHall(BizHall hall) {
        this.hall = hall;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
