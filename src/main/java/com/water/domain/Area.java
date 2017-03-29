package com.water.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 区域
 * Created by Administrator on 2017/3/26.
 */
@Entity
@Table(name = "t_area")
public class Area{

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;
    @Column
    private String code;

    @Column(name="hall_id")
    private Integer hallId;

    @ManyToOne
    @JoinColumn(name = "hall_id",insertable=false , updatable=false,foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    private BizHall hall;

    @Column(name = "create_user")
    private Integer createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_delete")
    private Integer isDelete;

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

    public Integer getCreateUser() {
        return createUser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public BizHall getHall() {
        return hall;
    }

    public void setHall(BizHall hall) {
        this.hall = hall;
    }
}
