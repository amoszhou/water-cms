package com.water.controller;

import com.water.domain.Factory;
import com.water.service.FactoryService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description : 水厂Controller
 * @Date: Created in 11:45 2018/6/26
 * @Modified By:
 */
@RestController
@RequestMapping("/factory")
public class FactotyController {

    Logger logger = LoggerFactory.getLogger(FactotyController.class);

    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("FactotyController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = factoryService.queryList(params);
            logger.info("FactotyController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("FactotyController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("FactotyController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addFactory")
    public R save(@RequestBody Factory dto){
        try {
            factoryService.save(dto);
        } catch (Exception e) {
            logger.info("FactotyController/save|添加失败，原因：{}", e.getMessage());
            logger.error("FactotyController/save|Exception:"+e.getMessage(), e);
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
        Factory factory = factoryService.queryObject(id);
        try {
            if (factory != null) {
                logger.info("FactotyController.info|查询结果 activityInfo = {}", factory.toString());
                return R.ok().put("factory", factory);
            } else {
                logger.info("FactotyController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("FactotyController/info|查询失败，原因：{}", e.getMessage());
            logger.error("FactotyController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateFactory")
    public R update(@RequestBody Factory dto){
        try {
            factoryService.update(dto);
        } catch (Exception e) {
            logger.info("FactotyController/update|修改失败，原因：{}", e.getMessage());
            logger.error("FactotyController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("FactotyController/delete|ids = {}", id);
        try{
            factoryService.delete(id);
        }catch (Exception e){
            logger.info("FactotyController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("FactotyController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }

        return R.ok();
    }

}
