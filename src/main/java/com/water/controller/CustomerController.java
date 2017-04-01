package com.water.controller;


import com.water.domain.Customer;
import com.water.repository.CustomerRepository;
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
    public String findById(@PathVariable String code, Model model) {
//        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Customer customer = customerRepository.findByCode(code);
        model.addAttribute("c", customer);
        return "customer";
    }

    @RequestMapping()
    public String list(int pageNum, int pageSize, Model model) {
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<Customer> data = customerRepository.findAll(pageable);
        model.addAttribute("data", data);
        return "customerList";
    }

}
