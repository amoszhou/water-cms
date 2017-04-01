package com.water.controller;

import com.water.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/3/30.
 */

public class BaseController {

    private static final String COMPANY_KEY = "COMPANY_ID";

    private static final String USER_KEY = "session_user_id";

    public HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    /**
     * 获取当前登陆的用户
     *
     * @param request
     * @return
     */
    public User getCurrentUser(HttpServletRequest request) {
        return (User) getSession(request).getAttribute(USER_KEY);
    }

    public int getValidPageNum(int pageNum) {
        return pageNum < 1 ? 1 : pageNum;
    }

    public int getValidPageSize(int pageSize) {
        return pageSize < 1 ? 10 : pageSize;
    }
}
