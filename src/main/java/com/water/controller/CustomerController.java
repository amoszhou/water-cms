package com.water.controller;

import com.water.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 16:03 2018/7/10
 * @Modified By:
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;



}
