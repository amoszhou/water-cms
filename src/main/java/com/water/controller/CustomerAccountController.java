package com.water.controller;

import com.water.constant.ChargeType;
import com.water.domain.CustomerAccount;
import com.water.domain.IdAndNameDTO;
import com.water.domain.PriceType;
import com.water.exception.BizException;
import com.water.service.CustomerAccountService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 9:30 2018/7/17
 * @Modified By:
 */
@RestController
@RequestMapping("/customerAccount")
public class CustomerAccountController {

    Logger logger = LoggerFactory.getLogger(PriceTypeController.class);

    @Autowired
    private CustomerAccountService customerAccountService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        logger.info("CustomerAccountController.queryList|begin ---> params : {}", params.toString());
        try {
            R result = customerAccountService.queryList(params);
            logger.info("CustomerAccountController.queryList|end ---> result : {}", result.get("page").toString());
            return result;
        } catch (Exception e) {
            logger.info("CustomerAccountController.queryList|查询失败，原因：{}", e.getMessage());
            logger.error("CustomerAccountController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addCustomerAccount")
    public R save(@RequestBody CustomerAccount dto) {
        try {
            customerAccountService.save(dto);
        }catch (BizException e){
            return R.error(e.getMessage());
        } catch (Exception e) {
            logger.info("CustomerAccountController/save|添加失败，原因：{}", e.getMessage());
            logger.error("CustomerAccountController/save|Exception:" + e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("CustomerAccountController/info|id = {}", id);
        CustomerAccount customerAccount = customerAccountService.queryObject(id);
        try {
            if (customerAccount != null) {
                logger.info("CustomerAccountController.info|查询结果 employee = {}", customerAccount.toString());
                return R.ok().put("customerAccount", customerAccount);
            } else {
                logger.info("CustomerAccountController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("CustomerAccountController/info|查询失败，原因：{}", e.getMessage());
            logger.error("CustomerAccountController/info|Exception:" + e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateCustomerAccount")
    public R update(@RequestBody CustomerAccount dto) {
        try {
            int result = customerAccountService.update(dto, ChargeType.YUCUN.getChargeTpe());
            if (result != 0)
                return R.ok();
        }catch (BizException e){
            return R.error(e.getMessage());
        }
        catch (Exception e) {
            logger.info("CustomerAccountController/update|修改失败，原因：{}", e.getMessage());
            logger.error("CustomerAccountController/update|Exception:" + e.getMessage(), e);
            return R.error();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id) {
        logger.info("CustomerAccountController/delete|ids = {}", id);
        try {
            customerAccountService.delete(id);
        } catch (Exception e) {
            logger.info("CustomerAccountController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("CustomerAccountController/delete|Exception:" + e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 取得所有的消费者的ID和Name
     */
    @GetMapping(value = "/getCustomerMessageList")
    public List<IdAndNameDTO> getFactoryMessage(){
        logger.info("CustomerAccountController/getCustomerMessageList begin");
        List<IdAndNameDTO> list = new ArrayList<>();
        try {
            list = customerAccountService.selectCustomerMessage();
            for (IdAndNameDTO idAndNameDTO :list) {
                idAndNameDTO.setIdAndName(idAndNameDTO.getCode()+":"+ idAndNameDTO.getName());
            }
        } catch (Exception e) {
            logger.info("CustomerAccountController/getCustomerMessageList|查询失败，原因：{}", e.getMessage());
            logger.error("CustomerAccountController/getCustomerMessageList|Exception:"+e.getMessage(), e);
        }
        return  list;
    }


}
