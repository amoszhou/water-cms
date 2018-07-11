package com.water.controller;

import com.water.domain.Factory;
import com.water.domain.IdAndNameDTO;
import com.water.domain.Meter;
import com.water.service.FactoryService;
import com.water.service.MeterService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 14:10 2018/7/11
 * @Modified By:
 */
@RequestMapping("/meter")
@RestController
public class MeterController {

    Logger logger = LoggerFactory.getLogger(MeterController.class);

    @Autowired
    private MeterService meterService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("MeterController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = meterService.queryList(params);
            logger.info("MeterController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("MeterController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("MeterController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addMeter")
    public R save(@RequestBody Meter dto){
        try {
            meterService.save(dto);
        } catch (Exception e) {
            logger.info("MeterController/save|添加失败，原因：{}", e.getMessage());
            logger.error("MeterController/save|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("FactotyController/info|id = {}", id);
        Meter meter = meterService.queryObject(id);
        try {
            if (meter != null) {
                logger.info("MeterController.info|查询结果 employee = {}", meter.toString());
                return R.ok().put("meter", meter);
            } else {
                logger.info("MeterController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("MeterController/info|查询失败，原因：{}", e.getMessage());
            logger.error("MeterController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateMeter")
    public R update(@RequestBody Meter dto){
        try {
            meterService.update(dto);
        } catch (Exception e) {
            logger.info("MeterController/update|修改失败，原因：{}", e.getMessage());
            logger.error("MeterController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("MeterController/delete|ids = {}", id);
        try{
            meterService.delete(id);
        }catch (Exception e){
            logger.info("MeterController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("MeterController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

}
