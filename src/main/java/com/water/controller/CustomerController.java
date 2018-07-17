package com.water.controller;

import com.water.domain.Customer;
import com.water.service.CustomerService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 16:03 2018/7/10
 * @Modified By:
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("CustomerController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = customerService.queryList(params);
            logger.info("CustomerController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("CustomerController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("CustomerController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addCustomer")
    public R save(@RequestBody Customer dto){
        try {
            customerService.save(dto);
        } catch (Exception e) {
            logger.info("CustomerController/save|添加失败，原因：{}", e.getMessage());
            logger.error("CustomerController/save|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("AreaController/info|id = {}", id);
        Customer customer = customerService.queryObject(id);
        try {
            if (customer != null) {
                logger.info("CustomerController.info|查询结果 employee = {}", customer.toString());
                return R.ok().put("customer", customer);
            } else {
                logger.info("CustomerController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("CustomerController/info|查询失败，原因：{}", e.getMessage());
            logger.error("CustomerController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateCustomer")
    public R update(@RequestBody Customer dto){
        try {
            customerService.update(dto);
        } catch (Exception e) {
            logger.info("CustomerController/update|修改失败，原因：{}", e.getMessage());
            logger.error("CustomerController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("CustomerController/delete|ids = {}", id);
        try{
            customerService.delete(id);
        }catch (Exception e){
            logger.info("CustomerController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("CustomerController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }




}
