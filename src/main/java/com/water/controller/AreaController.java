/*
package com.water.controller;

import com.water.constant.JsonResult;
import com.water.domain.Area;
import com.water.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

*/
/**
 * Created by Administrator on 2017/4/8.
 *//*

@RequestMapping("/area")
public class AreaController extends BaseController{

    @Autowired
    private AreaRepository areaRepository;


    */
/**
     * 查询某个营业厅下面的区域
     * @param pageNum
     * @param pageSize
     * @param hallId
     * @return
     *//*

    @RequestMapping(method = RequestMethod.GET)
    public JsonResult areas(int pageNum, int pageSize, Integer hallId) {
//        Employee user = getCurrentUser(request);
//        Integer companyId = user.getFactoryId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<Area> data = areaRepository.findByHallId(hallId, pageable);
        return new JsonResult(true).setData(data);
    }

    */
/**
     * 在某个营业厅下面增加区域
     * @param pageNum
     * @param pageSize
     * @param hallId
     * @return
     *//*

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult createArea(int pageNum, int pageSize,Integer hallId) {
//        Employee user = getCurrentUser(request);
//        Integer companyId = user.getFactoryId();
        Pageable pageable = new PageRequest(getValidPageNum(pageNum), getValidPageSize(pageSize), Sort.Direction.DESC, "id");
        Page<Area> data = areaRepository.findByHallId(hallId, pageable);
        return new JsonResult(true).setData(data);
    }

}
*/
