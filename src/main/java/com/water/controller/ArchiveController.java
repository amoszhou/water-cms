package com.water.controller;

import com.water.constant.JsonResult;
import com.water.domain.Archive;
import com.water.domain.Employee;
import com.water.repository.ArchiveRepository;
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
@RequestMapping("/archive")
public class ArchiveController extends BaseController {

    @Autowired
    private ArchiveRepository archiveRepository;


    @RequestMapping(method = RequestMethod.GET)
    public JsonResult archives(HttpServletRequest request, int pageNum, int pageSize) {
        Employee user = getCurrentUser(request);
        Integer companyId = user.getFactoryId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<Archive> data = archiveRepository.findByHallId(companyId, pageable);
        return new JsonResult(true).setData(data);
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult createArchives(HttpServletRequest request, Archive archive) {
//        Employee user = getCurrentUser(request);
//        Integer companyId = user.getFactoryId();
        archiveRepository.saveAndFlush(archive);
        return new JsonResult(true);
    }
}
