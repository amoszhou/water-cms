package com.water.controller;

import com.water.constant.JsonResult;
import com.water.domain.PriceType;
import com.water.domain.User;
import com.water.repository.PriceTypeRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/4/9.
 */
@RequestMapping("/price")
public class PriceController extends BaseController {

    @Autowired
    private PriceTypeRepository priceTypeRepository;


    @RequestMapping(method = RequestMethod.GET)
    public JsonResult priceTypes(HttpServletRequest request, int pageNum, int pageSize) {
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<PriceType> data = priceTypeRepository.findByCompanyId(companyId, pageable);
        return new JsonResult(true).setData(data);
    }

    /**
     * 添加价格类型。
     *
     * @param request
     * @param price
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult createPriceType(HttpServletRequest request, PriceType price) {
        User user = getCurrentUser(request);
        Integer companyId = user.getCompanyId();
        price.setCompanyId(companyId);
        priceTypeRepository.saveAndFlush(price);
        return new JsonResult(true);
    }
}
