package com.water.controller;

import com.water.domain.Employee;
import com.water.service.EmployeeService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 16:57 2018/6/28
 * @Modified By:
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("EmployeeController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = employeeService.queryList(params);
            logger.info("EmployeeController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("EmployeeController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("EmployeeController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addEmployee")
    public R save(@RequestBody Employee dto){
        try {
            employeeService.save(dto);
        } catch (Exception e) {
            logger.info("EmployeeController/save|添加失败，原因：{}", e.getMessage());
            logger.error("EmployeeController/save|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("EmployeeController/info|id = {}", id);
        Employee employee = employeeService.queryObject(id);
        try {
            if (employee != null) {
                logger.info("EmployeeController.info|查询结果 employee = {}", employee.toString());
                return R.ok().put("factory", employee);
            } else {
                logger.info("EmployeeController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("EmployeeController/info|查询失败，原因：{}", e.getMessage());
            logger.error("EmployeeController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateEmployee")
    public R update(@RequestBody Employee dto){
        try {
            employeeService.update(dto);
        } catch (Exception e) {
            logger.info("EmployeeController/update|修改失败，原因：{}", e.getMessage());
            logger.error("EmployeeController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("EmployeeController/delete|ids = {}", id);
        try{
            employeeService.delete(id);
        }catch (Exception e){
            logger.info("EmployeeController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("EmployeeController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }

        return R.ok();
    }


}
