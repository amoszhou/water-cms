/*
package com.water.controller;

import com.water.constant.JsonResult;
import com.water.domain.*;
import com.water.repository.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

*/
/**
 * Created by Administrator on 2017/3/30.
 *//*

@Controller
@RequestMapping("/foundation")
public class FoundationController extends BaseController {


    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private PriceTypeRepository priceTypeRepository;

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String allCompany(Model model) {
        List<WaterFactory> data = companyRepository.findAll();
        model.addAttribute("companyList", data);
        return "company-list";
    }




    */
/**
     * 新增价格
     *
     * @param request
     * @param model
     * @param priceType
     * @return
     *//*

    @RequestMapping(value = "/priceType", method = RequestMethod.POST)
    public JsonResult createPriceTypes(HttpServletRequest request, Model model, PriceType priceType) {
//        Employee user = getCurrentUser(request);
//        Integer companyId = user.getFactoryId();
        priceTypeRepository.saveAndFlush(priceType);
        return new JsonResult(true);
    }
}
*/
