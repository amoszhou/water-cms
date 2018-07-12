package com.water.controller;

import com.water.domain.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhoumeihua on 2018/6/11.*/


@Controller
public class IndexController {


 /*   private UserRepository userRepository;*/

    @RequestMapping("/index")
    public String index(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        return "cover";
    }

    @RequestMapping("/app")
    public String app(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        return "app";
    }

    @RequestMapping("/factory")
    public String factory(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        model.addAttribute("deletePer","true");

        return "factory";
    }


    @RequestMapping("/employee")
    public String employee(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        model.addAttribute("deletePer","true");
        return "employee";
    }

    @RequestMapping("/hall")
    public String hall(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        model.addAttribute("deletePer","true");

        return "hall";
    }
    @RequestMapping("/priceType")
    public String priceType(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        model.addAttribute("deletePer","true");

        return "priceType";
    }

    @RequestMapping("/meter")
    public String meter(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        model.addAttribute("deletePer","true");

        return "meter";
    }

    @RequestMapping("/area")
    public String area(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        model.addAttribute("deletePer","true");

        return "area";
    }

    @RequestMapping("/archive")
    public String archive(Model model) {

        model.addAttribute("addPer","true");
        model.addAttribute("updatePer","true");
        model.addAttribute("deletePer","true");

        return "archive";
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

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
