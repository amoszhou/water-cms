package com.water.service;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 14:44 2018/7/28
 * @Modified By:
 */
public class TESTDTO {


    private Integer x ;
    private Integer y ;

    @Override
    public String toString() {
        return "TESTDTO{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
