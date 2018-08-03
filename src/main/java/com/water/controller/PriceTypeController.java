package com.water.controller;

import com.water.domain.Meter;
import com.water.domain.PriceType;
import com.water.exception.BizException;
import com.water.service.MeterService;
import com.water.service.PriceTypeService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 17:46 2018/7/12
 * @Modified By:
 */
@RequestMapping("/priceType")
@RestController
public class PriceTypeController {

    Logger logger = LoggerFactory.getLogger(PriceTypeController.class);

    @Autowired
    private PriceTypeService priceTypeService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("PriceTypeController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = priceTypeService.queryList(params);
            logger.info("PriceTypeController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("PriceTypeController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("PriceTypeController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addPriceType")
    public R save(@RequestBody PriceType dto){
        try {
            priceTypeService.save(dto);
        }catch (BizException e){
            logger.info("PriceTypeController/save|添加失败，原因：{}", e.getMessage());
            logger.error("PriceTypeController/save|Exception:"+e.getMessage(), e);
            return R.error(e.getMessage());
        }
        catch (Exception e) {
            logger.info("PriceTypeController/save|添加失败，原因：{}", e.getMessage());
            logger.error("PriceTypeController/save|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("PriceTypeController/info|id = {}", id);
        PriceType priceType = priceTypeService.queryObject(id);
        try {
            if (priceType != null) {
                logger.info("PriceTypeController.info|查询结果 employee = {}", priceType.toString());
                return R.ok().put("priceType", priceType);
            } else {
                logger.info("PriceTypeController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("PriceTypeController/info|查询失败，原因：{}", e.getMessage());
            logger.error("PriceTypeController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updatePriceType")
    public R update(@RequestBody PriceType dto){
        try {
            priceTypeService.update(dto);
        } catch (Exception e) {
            logger.info("PriceTypeController/update|修改失败，原因：{}", e.getMessage());
            logger.error("PriceTypeController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("PriceTypeController/delete|ids = {}", id);
        try{
            priceTypeService.delete(id);
        }catch (Exception e){
            logger.info("PriceTypeController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("PriceTypeController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


}
