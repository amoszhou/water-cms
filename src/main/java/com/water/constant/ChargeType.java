package com.water.constant;

/**
 * @Author : 林吉达
 * @Description : 消费类型  t_charge_record 表  charge_type字段
 * @Date: Created in 10:13 2018/8/7
 * @Modified By:
 */
public enum ChargeType {

    YUCUN(1,"预存账户余额"),
    JIAOFEI(2,"缴费")
    ;
    private Integer chargeTpe;
    private String desc;

    ChargeType(Integer chargeTpe, String desc) {
        this.chargeTpe = chargeTpe;
        this.desc = desc;
    }

    public Integer getChargeTpe() {
        return chargeTpe;
    }

    public void setChargeTpe(Integer chargeTpe) {
        this.chargeTpe = chargeTpe;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
