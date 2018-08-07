package com.water.constant;

/**
 * @Author : 林吉达
 * @Description :缴费状态 t_pay_record 表 pay_state字段
 * @Date: Created in 9:45 2018/8/7
 * @Modified By:
 */
public enum PayState {
    PAY(1,"已缴费"),
    NOTPAY(-1,"暂未缴费");
    private Integer state;
    private String desc;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    PayState(Integer state, String desc) {
        this.state = state;
        this.desc = desc;
    }
}
