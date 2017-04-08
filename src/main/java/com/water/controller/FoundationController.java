package com.water.controller;

import com.water.constant.JsonResult;
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
    private CompanyRepository companyRepository;

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


    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public JsonResult archives(HttpServletRequest request, int pageNum, int pageSize) {
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<Archive> data = archiveRepository.findByHallId(companyId, pageable);
        return new JsonResult(true).setData(data);
    }

    @RequestMapping(value = "/priceType", method = RequestMethod.GET)
    public JsonResult priceTypes(HttpServletRequest request, Model model, int pageNum, int pageSize) {
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<PriceType> data = priceTypeRepository.findByCompanyId(companyId, pageable);
        return new JsonResult(true).setData(data);
    }

    /**
     * 新增价格
     *
     * @param request
     * @param model
     * @param priceType
     * @return
     */
    @RequestMapping(value = "/priceType", method = RequestMethod.POST)
    public JsonResult createPriceTypes(HttpServletRequest request, Model model, PriceType priceType) {
//        User user = getCurrentUser(request);
//        Integer companyId = user.getCompanyId();
        priceTypeRepository.saveAndFlush(priceType);
        return new JsonResult(true);
    }
}
