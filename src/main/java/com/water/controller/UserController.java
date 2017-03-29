package com.water.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/26.
 */
@Controller
@RequestMapping("/customer")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @RequestMapping("{code}")
//    public String findById(@PathVariable String code, Model model) {
//        Customer customer = customerRepository.findByCode(code);
//        model.addAttribute("c", customer);
//        return "customer";
//    }
//
//    @RequestMapping()
//    public String list(int pageNum, int pageSize,Model model) {
//        Page<Customer> page = PageHelper.startPage(pageNum, pageSize);
//        List<Customer> data = customerRepository.pageFindAll(null);
//
//        return "customerList";
//    }

}
