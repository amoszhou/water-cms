package com.water.constant;

/**
 * Created by Administrator on 2017/3/26.
 */
public class PageUtils {

    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";

    public static final int DEFAULT_PAGE_SIZE = 10;

    public static int getStartOffset(int pageNum, int pageSize) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return (pageNum - 1) * pageSize;
    }

    public static int getStartOffset(int pageNum) {
        return getStartOffset(pageNum, DEFAULT_PAGE_SIZE);
    }

}
