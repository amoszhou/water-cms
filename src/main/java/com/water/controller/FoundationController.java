package com.water.controller;

import com.water.domain.*;
import com.water.repository.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private PriceTypeRepository priceTypeRepository;

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String allCompany(Model model) {
        List<Company> data = companyRepository.findAll();
        model.addAttribute("companyList", data);
        return "company-list";
    }


    @RequestMapping(value = "/bizHall", method = RequestMethod.GET)
    public String bizHalls(HttpServletRequest request, Model model,int pageNum,int pageSize) {
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<BizHall> data = bizHallRepository.findByCompanyId(companyId, pageable);
        model.addAttribute("data", data);
        return "bizhall-list";
    }


    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public String areas(HttpServletRequest request, Model model,int pageNum,int pageSize) {
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<Area> data = areaRepository.findByHallId(companyId, pageable);
        model.addAttribute("data", data);
        return "area-list";
    }

    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public String archives(HttpServletRequest request, Model model,int pageNum,int pageSize){
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<Archive> data = archiveRepository.findByHallId(companyId, pageable);
        model.addAttribute("data", data);
        return "archive-list";
    }

    @RequestMapping(value = "/priceType", method = RequestMethod.GET)
    public String priceTypes(HttpServletRequest request, Model model,int pageNum,int pageSize){
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<PriceType> data = priceTypeRepository.findByCompanyId(companyId, pageable);
        model.addAttribute("data", data);
        return "archive-list";
    }

}
