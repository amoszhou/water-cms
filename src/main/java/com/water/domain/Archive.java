package com.water.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 表册
 * Created by Administrator on 2017/3/29.
 */
@Entity
@Table(name = "t_archive")
public class Archive {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String code;

    @Column
    private String name;

    // TODO: 2017/3/29  增加用员工的ManyToOne关联
//    @ManyToOne
//    @JoinColumn(name="record_user")
    private Integer recordUser;

    @Column(name="hall_id")
    private Integer hallId;

    @ManyToOne
    @JoinColumn(name="hall_id")
    private BizHall hall;

    @Column(name="area_id")
    private Integer areaId;

    @ManyToOne
    @JoinColumn(name="area_id")
    private Area area;

    @Column(name="create_time")
    private Date createTime;


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

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
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

    public Integer getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(Integer recordUser) {
        this.recordUser = recordUser;
    }

    public BizHall getHall() {
        return hall;
    }

    public void setHall(BizHall hall) {
        this.hall = hall;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
