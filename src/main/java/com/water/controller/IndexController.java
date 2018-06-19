package com.water.controller;

import com.water.domain.Employee;
import com.water.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhoumeihua on 2018/6/11.
 */
@Controller
public class IndexController {


    private UserRepository userRepository;

    @RequestMapping("/index")
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
//            Employee e = userRepository.findByUsername(username);
            Employee e = new Employee();
            e.setId(1);
            e.setFactoryId(1);
        }
        return "index";
    }
}
