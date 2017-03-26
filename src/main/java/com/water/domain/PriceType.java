package com.water.domain;

/**
 * 水费配置表
 * Created by Administrator on 2017/3/26.
 */
public class PriceType {

    private Integer id;
    private String name;
    private Integer price;


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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
