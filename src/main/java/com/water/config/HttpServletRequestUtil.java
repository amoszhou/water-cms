package com.water.config;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 11:31 2018/8/1
 * @Modified By:
 */
public  class  HttpServletRequestUtil {

    public static HttpServletRequest getRequst() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        return request;
    }

    public static void removeAttr(){
        getRequst().getSession().removeAttribute(Globals.FACTORYIDS);
        getRequst().getSession().removeAttribute(Globals.USERTYPE);
        getRequst().getSession().removeAttribute(Globals.USERID);
    }

    public static Integer getUserId(){
        return  (Integer)getRequst().getSession().getAttribute(Globals.USERKEY);
    }


}
