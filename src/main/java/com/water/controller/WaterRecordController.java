package com.water.controller;

import com.water.domain.WaterRecord;
import com.water.service.WaterRecordService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 11:08 2018/7/21
 * @Modified By:
 */
@RestController
@RequestMapping("/waterRecord")
public class WaterRecordController {

    Logger logger = LoggerFactory.getLogger(WaterRecordController.class);

    @Autowired
    private WaterRecordService waterRecordService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("WaterRecordController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = waterRecordService.queryList(params);
            logger.info("WaterRecordController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("WaterRecordController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("WaterRecordController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addWaterRecord")
    public R save(@RequestBody WaterRecord dto){
        try {
            waterRecordService.save(dto);
        } catch (Exception e) {
            logger.info("WaterRecordController/save|添加失败，原因：{}", e.getMessage());
            logger.error("WaterRecordController/save|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("WaterRecordController/info|id = {}", id);
        WaterRecord waterRecord = waterRecordService.queryObject(id);
        try {
            if (waterRecord != null) {
                logger.info("WaterRecordController.info|查询结果 employee = {}", waterRecord.toString());
                return R.ok().put("waterRecord", waterRecord);
            } else {
                logger.info("WaterRecordController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("WaterRecordController/info|查询失败，原因：{}", e.getMessage());
            logger.error("WaterRecordController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }


}
