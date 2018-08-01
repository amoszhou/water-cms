package com.water.constant;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 11:22 2018/7/31
 * @Modified By:
 */
public enum EmployeeType {
    SUPER_MANAGER(0,"超级管理员"),
    NORMAL_MANAGER(1,"普通管理员");
    private Integer typeId;
    private String desc;

    EmployeeType(Integer typeId, String desc) {
        this.typeId = typeId;
        this.desc = desc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
