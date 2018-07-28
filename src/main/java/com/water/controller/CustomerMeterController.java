package com.water.controller;

import com.water.domain.CustomerMeter;
import com.water.service.CustomerMeterService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 17:22 2018/7/23
 * @Modified By:
 */
@RequestMapping("/customerMeter")
@RestController
public class CustomerMeterController {

    Logger logger = LoggerFactory.getLogger(CustomerMeterController.class);

    @Autowired
    private CustomerMeterService customerMeterService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("CustomerMeterController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = customerMeterService.queryList(params);
            logger.info("CustomerMeterController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("CustomerMeterController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("CustomerMeterController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addCustomerMeter")
    public R save(@RequestBody CustomerMeter dto){
        try {
            customerMeterService.save(dto);
        } catch (Exception e) {
            logger.info("CustomerMeterController/save|添加失败，原因：{}", e.getMessage());
            logger.error("CustomerMeterController/save|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("CustomerMeterController/info|id = {}", id);
        CustomerMeter customerMeter = customerMeterService.queryObject(id);
        try {
            if (customerMeter != null) {
                logger.info("CustomerMeterController.info|查询结果 customerMeter = {}", customerMeter.toString());
                return R.ok().put("customerMeter", customerMeter);
            } else {
                logger.info("CustomerMeterController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("CustomerMeterController/info|查询失败，原因：{}", e.getMessage());
            logger.error("CustomerMeterController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateCustomerMeter")
    public R update(@RequestBody CustomerMeter dto){
        try {
            customerMeterService.update(dto);
        } catch (Exception e) {
            logger.info("CustomerMeterController/update|修改失败，原因：{}", e.getMessage());
            logger.error("CustomerMeterController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("CustomerMeterController/delete|ids = {}", id);
        try{
            customerMeterService.delete(id);
        }catch (Exception e){
            logger.info("CustomerMeterController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("CustomerMeterController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }

        return R.ok();
    }

}
