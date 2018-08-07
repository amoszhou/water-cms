package com.water.constant;

/**
 * @Author : 林吉达
 * @Description :支付方式  t_pay_record表 pay_type字段
 * @Date: Created in 9:41 2018/8/7
 * @Modified By:
 */
public enum PayType {
    ZHIFUBAO(1,"支付宝"),
    WECHAT(2,"微信"),
    CASH(3,"现金"),
    BALANCE(4,"余额"),
    NOTPAYYET(-1,"暂未缴费"),
    CHONGZHI(5,"充值类型，无支付类型")  // t_charge_record表 的 pay_type字段
    ;
    private Integer payType;
    private String desc;

    PayType(Integer payType, String desc) {
        this.payType = payType;
        this.desc = desc;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
