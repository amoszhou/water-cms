package com.water.controller;

import com.water.domain.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/3/30.
 */

public class BaseController {

    private static final String FACTORY_KEY = "FACTORY_ID";

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
    public Employee getCurrentUser(HttpServletRequest request) {
        // TODO: 2018/6/11  remove after login has been done
        Employee e = new Employee();
        e.setFactoryId(1);
        e.setId(1);
        return e;
//        return (Employee) getSession(request).getAttribute(USER_KEY);
    }

    public int getValidPageNum(int pageNum) {
        return pageNum < 1 ? 1 : pageNum;
    }

    public int getValidPageSize(int pageSize) {
        return pageSize < 1 ? 10 : pageSize;
    }
}
