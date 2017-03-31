package com.water.controller;

import com.water.domain.BizHall;
import com.water.domain.Company;
import com.water.domain.User;
import com.water.repository.ArchiveRepository;
import com.water.repository.AreaRepository;
import com.water.repository.BizHallRepository;
import com.water.repository.CompanyRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
@RequestMapping("/foundation")
public class FoundationController extends BaseController {

    @Autowired
    private BizHallRepository bizHallRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private ArchiveRepository archiveRepository;


    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String allCompany(Model model) {
        List<Company> data = companyRepository.findAll();
        model.addAttribute("companyList", data);
        return "company-list";
    }


    public String bizHalls(HttpServletRequest request, Model model) {
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
//        List<BizHall> data = bizHallRepository.findByCompanyId(companyId);
//        int count
        return "bizhall-list";
    }

}
