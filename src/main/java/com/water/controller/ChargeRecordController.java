package com.water.controller;

import com.water.domain.ChargeRecord;
import com.water.service.ChargeRecordService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 11:35 2018/7/17
 * @Modified By:
 */
@RestController
@RequestMapping("/chargeRecord")
public class ChargeRecordController {

    Logger logger = LoggerFactory.getLogger(ChargeRecordController.class);

    @Autowired
    private ChargeRecordService chargeRecordService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        logger.info("ChargeRecordController.queryList|begin ---> params : {}", params.toString());
        try {
            R result = chargeRecordService.queryList(params);
            logger.info("ChargeRecordController.queryList|end ---> result : {}", result.get("page").toString());
            return result;
        } catch (Exception e) {
            logger.info("ChargeRecordController.queryList|查询失败，原因：{}", e.getMessage());
            logger.error("ChargeRecordController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addChargeRecord")
    public R save(@RequestBody ChargeRecord dto) {
        try {
            chargeRecordService.save(dto);
        } catch (Exception e) {
            logger.info("ChargeRecordController/save|添加失败，原因：{}", e.getMessage());
            logger.error("ChargeRecordController/save|Exception:" + e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("ChargeRecordController/info|id = {}", id);
        ChargeRecord chargeRecord = chargeRecordService.queryObject(id);
        try {
            if (chargeRecord != null) {
                logger.info("ChargeRecordController.info|查询结果 employee = {}", chargeRecord.toString());
                return R.ok().put("chargeRecord", chargeRecord);
            } else {
                logger.info("ChargeRecordController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("ChargeRecordController/info|查询失败，原因：{}", e.getMessage());
            logger.error("ChargeRecordController/info|Exception:" + e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateChargeRecord")
    public R update(@RequestBody ChargeRecord dto) {
        try {
          chargeRecordService.update(dto);
        } catch (Exception e) {
            logger.info("ChargeRecordController/update|修改失败，原因：{}", e.getMessage());
            logger.error("ChargeRecordController/update|Exception:" + e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id) {
        logger.info("ChargeRecordController/delete|ids = {}", id);
        try {
            chargeRecordService.delete(id);
        } catch (Exception e) {
            logger.info("ChargeRecordController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("ChargeRecordController/delete|Exception:" + e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


}
