package com.water.constant;

/**
 * Created by Administrator on 2017/4/11.
 */
public enum ChargeType {
    /**
     * 正常缴费
     */
    NORMAL_PAY(1),
    /**
     * 预存
     */
    PRE_PAY(2);

    private int value;

    ChargeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
