package com.water.controller;


import com.water.domain.Customer;
import com.water.domain.User;
import com.water.repository.CustomerRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/26.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private Logger logger = Logger.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("{code}")
    public String findById(@PathVariable String code, HttpServletRequest request, Model model) {
//        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        User user = getCurrentUser(request);
        Customer customer = customerRepository.findByCodeAndCompanyId(code, user.getCompanyId());
        model.addAttribute("c", customer);
        return "customer";
    }

    /**
     * 分页查询本公司的所有住户
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request, int pageNum, int pageSize, Model model) {
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        User user = getCurrentUser(request);
        Page<Customer> data = customerRepository.findByCompanyId(user.getCompanyId(), pageable);
        model.addAttribute("data", data);
        return "customerList";
    }

    /**
     * 创建新住户
     *
     * @param c
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public boolean createCustomer(HttpServletRequest request, Customer c) {
        User user = getCurrentUser(request);
        c.setCreateTime(new Date());
        c.setCreateUser(user.getId());
        customerRepository.saveAndFlush(c);
        return true;
    }


    @RequestMapping(value = "/{customerId}/meter", method = RequestMethod.POST)
    public boolean addMeterForCustomer(HttpServletRequest request, @PathVariable Integer customerId) {
        // TODO: 2017/4/5 添加水表
        return true;
    }

}
