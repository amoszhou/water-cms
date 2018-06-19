package com.water.util;

/**
 * Created by zhoumeihua on 2018/6/11.
 */
public class FactoryHolder {

    private static ThreadLocal<Integer> factoryId = new ThreadLocal<>();


    public static Integer getCurrentFactory() {
        return factoryId.get();
    }

    public static void setCurrentFactory(Integer id) {
        factoryId.set(id);
    }
}
