package com.water.controller;

import com.water.config.Globals;
import com.water.config.HttpServletRequestUtil;
import com.water.domain.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhoumeihua on 2018/6/11.
 */


@Controller
public class IndexController {


 /*   private UserRepository userRepository;*/

    @RequestMapping("/index")
    public String index(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        //获取用户名
        model.addAttribute("userName", HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERNAME));
        //获取用户type
        if ((Integer) HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERTYPE) == 1) {
            model.addAttribute(Globals.USERTYPE, "普通管理员");
            model.addAttribute(Globals.USERTYPEENUM,  2);
        } else {
            model.addAttribute(Globals.USERTYPE, "超级管理员");
            model.addAttribute(Globals.USERTYPEENUM, 1);
        }


        return "cover";
    }

    @RequestMapping("/app")
    public String app(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        return "app";
    }

    @RequestMapping("/factory")
    public String factory(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

        return "factory";
    }

    @RequestMapping("/admin/login")
    public String admin(Model model) {

        return "login";
    }


    @RequestMapping("/employee")
    public String employee(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");
        return "employee";
    }

    @RequestMapping("/hall")
    public String hall(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

        return "hall";
    }

    @RequestMapping("/priceType")
    public String priceType(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

        return "priceType";
    }

    @RequestMapping("/waterRecord")
    public String waterRecord(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

        return "waterRecord";
    }

    @RequestMapping("/payRecord")
    public String payRecord(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

        return "payRecord";
    }

    @RequestMapping("/customerMeter")
    public String customerMeter(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");
        return "customerMeter";
    }

    @RequestMapping("/chargeRecord")
    public String chargeRecord(Model model) {
        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");
        return "chargeRecord";
    }

    @RequestMapping("/customer")
    public String customer(Model model) {
        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");
        return "customer";
    }

    @RequestMapping("/meter")
    public String meter(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

        return "meter";
    }

    @RequestMapping("/customerAccount")
    public String customerAccount(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

        return "customerAccount";
    }

    @RequestMapping("/area")
    public String area(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

        return "area";
    }

    @RequestMapping("/archive")
    public String archive(Model model) {

        model.addAttribute("addPer", "true");
        model.addAttribute("updatePer", "true");
        model.addAttribute("deletePer", "true");

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
