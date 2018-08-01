package com.water.annotation;

import com.water.config.HttpServletRequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 14:16 2018/8/1
 * @Modified By:
 */
@Component
@Aspect
public class FactoryIdsAspect {

    private Logger logger = LoggerFactory.getLogger(FactoryIdsAspect.class);



    /**
     * 定义有一个切入点，范围为web包下的类
     */
    @Pointcut("@annotation(com.water.annotation.FactoryIds)")
    public void installFactotyIds() {
    }

    @Before("installFactotyIds()")
    public void doBeforeMethod(JoinPoint joinPoint) {
        logger.info("FactoryIdsAspect/doBeforeMethod JoinPoint:{} ",joinPoint);
        Object[] obj = joinPoint.getArgs();
        for (Object argItem : obj) {
            logger.info("---->now-->argItem:" + argItem);
            if (argItem instanceof Map) {
                Map paramVO = (Map) argItem;
               paramVO.put("factoryIds", HttpServletRequestUtil.getRequst().getSession().getAttribute("factoryIds"));
            }
            logger.info("---->after-->argItem:" + argItem);
        }

    }

   /* @Around("installFactotyIds()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }*/


}
