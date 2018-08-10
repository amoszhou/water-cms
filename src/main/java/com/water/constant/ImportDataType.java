package com.water.constant;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 17:30 2018/8/9
 * @Modified By:
 */
public enum ImportDataType {
    IMPORTCUSTOMER(1,"导入顾客信息"),
    IMPORTWATERRECORD(2,"导入用水记录")
    ;
    private Integer type;
    private String desc;

    ImportDataType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
