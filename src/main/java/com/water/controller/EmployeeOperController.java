package com.water.controller;

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


    //登录失败
    private final static int LOGINFAIL = -1;

    @GetMapping("/login")
    public ResultDTO employeeLogin(@RequestParam(value = "telPhone")String telPhone
            ,@RequestParam(value = "password")String password){
        Employee employee = new Employee();
        try {
            if(telPhone ==null || password == null)
                return ResultDTO.buildSuccessResult(LOGINFAIL);

            employee.setTelPhone(telPhone);
            employee.setPassword(password);
            return ResultDTO.buildSuccessResult(employeeService.employeeLogin(employee));
        } catch (Exception e) {
            logger.info("EmployeeOperController/employeeLogin|登录失败，数据:{},原因：{}", employee, e.getMessage());
            logger.error("EmployeeOperController/employeeLogin|数据:{}|Exception:" + e.getMessage(), employee, e);
        }
        return ResultDTO.buildSuccessResult(LOGINFAIL);
    }

}
