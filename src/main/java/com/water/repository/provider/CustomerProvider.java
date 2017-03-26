package com.water.repository.provider;

import com.water.constant.PageUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/26.
 */
public class CustomerProvider {

    public String pageFindCustomer(final Map<String, Object> map) {
        return new SQL() {
            {
                SELECT(" * ");
                FROM("customer");
                if (map.containsKey(PageUtils.PAGE_NUM)) {
                    WHERE("id >= (select id from customer limit " + PageUtils.getStartOffset((Integer) map.get(PageUtils.PAGE_NUM)) + ", 1 ) limit " + (Integer) map.get(PageUtils.PAGE_SIZE));

                }
            }
        }.toString();
    }
}
