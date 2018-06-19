package com.water.controller;

import com.water.constant.JsonResult;
import com.water.domain.BizHall;
import com.water.domain.Employee;
import com.water.repository.BizHallRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/4/8.
 */
@RequestMapping("/bizHall")
public class BizHallController extends BaseController {


    @Autowired
    private BizHallRepository bizHallRepository;


    /**
     * 营业厅列表
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult bizHalls(HttpServletRequest request, int pageNum, int pageSize) {
        Employee user = getCurrentUser(request);
        Integer companyId = user.getFactoryId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<BizHall> data = bizHallRepository.findByCompanyId(companyId, pageable);
        return new JsonResult(true).setData(data);
    }

    /**
     * 新增营业厅
     * @param bizHall
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult createBizHalls(BizHall bizHall) {
        bizHallRepository.saveAndFlush(bizHall);
        return new JsonResult(true);
    }



}