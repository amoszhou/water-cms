package com.water.controller;

import com.water.config.Globals;
import com.water.config.HttpServletRequestUtil;
import com.water.domain.Employee;
import com.water.service.EmployeeService;
import com.water.util.R;
import com.water.util.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 10:49 2018/7/31
 * @Modified By:
 */
@RestController
@RequestMapping("/employeeOper")
public class EmployeeOperController {

    Logger logger = LoggerFactory.getLogger(EmployeeOperController.class);
    @Autowired
    private EmployeeService employeeService;

    //登出成功
    private final static int SUCCESSFLAG = 1;

    //登录失败
    private final static int FAILFLAG = -1;

    @GetMapping("/login")
    public ResultDTO employeeLogin(@RequestParam(value = "telPhone") String telPhone
            , @RequestParam(value = "password") String password) {
        Employee employee = new Employee();
        try {
            if (telPhone == null || password == null)
                return ResultDTO.buildSuccessResult(FAILFLAG);

            employee.setTelPhone(telPhone);
            employee.setPassword(password);
            return ResultDTO.buildSuccessResult(employeeService.employeeLogin(employee));
        } catch (Exception e) {
            logger.info("EmployeeOperController/employeeLogin|登录失败，数据:{},原因：{}", employee, e.getMessage());
            logger.error("EmployeeOperController/employeeLogin|数据:{}|Exception:" + e.getMessage(), employee, e);
        }
        return ResultDTO.buildSuccessResult(FAILFLAG);
    }

    @GetMapping("/logout")
    public ResultDTO employeeLogOut() {
        try {
            HttpServletRequestUtil.removeAttr();
        } catch (Exception e) {
            logger.error("EmployeeOperController/employeeLogOut|Exception:" + e.getMessage(), e);
        }
        return ResultDTO.buildSuccessResult(SUCCESSFLAG);
    }

    /**
     * @Author : 林吉达
     * @Description : 修改密码
     * @Date : 11:24 2018/8/2
     */
    @PostMapping("/modifyMyPassword")
    public ResultDTO modifyMyPassword(@RequestParam(value = "oldPassword") String oldPassword
            , @RequestParam(value = "newPassword") String newPassword
            , @RequestParam(value = "twicePassword") String twicePassword) {
        Map map = new HashMap();
        try {
            if (oldPassword == null || newPassword == null || twicePassword == null
                    || oldPassword.trim().length() == 0 || newPassword.trim().length() == 0
                    || twicePassword.trim().length() == 0 || !newPassword.equals(twicePassword) || oldPassword.equals(newPassword))
                return ResultDTO.buildSuccessResult(FAILFLAG);

            map.put("oldPassword",oldPassword);
            map.put("newPassword",newPassword);
            map.put("telPhone", HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERID));
            return ResultDTO.buildSuccessResult(employeeService.modifyPassword(map));
        } catch (Exception e) {
            logger.info("EmployeeOperController/modifyMyPassword|修改密码失败，数据:{},原因：{}", map.toString(), e.getMessage());
            logger.error("EmployeeOperController/modifyMyPassword|数据:{}|Exception:" + e.getMessage(),  map.toString(), e);
        }
        return ResultDTO.buildSuccessResult(FAILFLAG);
    }

}
