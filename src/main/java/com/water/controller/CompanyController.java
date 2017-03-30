package com.water.controller;

import com.water.domain.Company;
import com.water.repository.ArchiveRepository;
import com.water.repository.AreaRepository;
import com.water.repository.BizHallRepository;
import com.water.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private BizHallRepository bizHallRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private ArchiveRepository archiveRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allCompany(Model model) {
        List<Company> data = companyRepository.findAll();
        model.addAttribute("companyList",data);
        return "company_list";
    }
}
