package com.water.domain;

import javax.persistence.*;

/**
 * 水费配置表
 * Created by Administrator on 2017/3/26.
 */
@Entity
@Table(name="t_price_type")
public class PriceType {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    /**
     * 水价。 price + sewage = 计算水价
     */
    @Column
    private Integer price;

    /**污水费*/
    @Column
    private Integer sewage;


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

    public Integer getSewage() {
        return sewage;
    }

    public void setSewage(Integer sewage) {
        this.sewage = sewage;
    }
}
