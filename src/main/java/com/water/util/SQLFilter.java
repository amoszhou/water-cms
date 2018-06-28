package com.water.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author : 林吉达
 * @Description : SQL过滤
 * @Date : 22:17 2018/6/27
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        String  resultstr = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(resultstr.indexOf(keyword) != -1){
                return  "此条记录包含非法字符，请不要审核通过";
            }
        }

        return str;
    }
}
