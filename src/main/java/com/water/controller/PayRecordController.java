package com.water.controller;

import com.water.domain.PayRecord;
import com.water.domain.PriceType;
import com.water.service.PayRecordService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 17:14 2018/7/21
 * @Modified By:
 */
@RestController
@RequestMapping("payRecord")
public class PayRecordController {

    Logger logger = LoggerFactory.getLogger(PayRecordController.class);

    @Autowired
    private PayRecordService payRecordService;


    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("PayRecordController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = payRecordService.queryList(params);
            logger.info("PayRecordController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("PayRecordController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("PayRecordController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("PayRecordController/info|id = {}", id);
        PayRecord payRecord = payRecordService.queryObject(id);
        try {
            if (payRecord != null) {
                logger.info("PayRecordController.info|查询结果 employee = {}", payRecord.toString());
                return R.ok().put("payRecord", payRecord);
            } else {
                logger.info("PayRecordController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("PayRecordController/info|查询失败，原因：{}", e.getMessage());
            logger.error("PayRecordController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updatePayRecord")
    public R update(@RequestBody PayRecord dto){
        try {
            payRecordService.update(dto);
        } catch (Exception e) {
            logger.info("PayRecordController/update|修改失败，原因：{}", e.getMessage());
            logger.error("PayRecordController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


}
