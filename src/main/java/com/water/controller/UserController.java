package com.water.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.water.domain.Customer;
import com.water.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/26.
 */
@Controller
@RequestMapping("/customer")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("{code}")
    public String findById(@PathVariable String code, Model model) {
        Customer customer = customerRepository.findByCode(code);
        model.addAttribute("c", customer);
        return "customer";
    }

    @RequestMapping()
    public String list(int pageNum, int pageSize,Model model) {
        Page<Customer> page = PageHelper.startPage(pageNum, pageSize);
        List<Customer> data = customerRepository.pageFindAll(null);

        return "customerList";
    }

}
