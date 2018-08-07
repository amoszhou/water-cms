package com.water.controller;

import com.water.config.HttpServletRequestUtil;
import com.water.domain.Area;
import com.water.service.AreaService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 17:43 2018/7/3
 * @Modified By:
 */
@RestController
@RequestMapping("/area")
public class AreaController {

    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("AreaController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = areaService.queryList(params);
            logger.info("AreaController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("AreaController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("AreaController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addArea")
    public R save(@RequestBody Area dto){
        try {

            areaService.save(dto);
        } catch (Exception e) {
            logger.info("AreaController/save|添加失败，原因：{}", e.getMessage());
            logger.error("AreaController/save|Exception:"+e.getMessage(), e);
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
        Area area = areaService.queryObject(id);
        try {
            if (area != null) {
                logger.info("AreaController.info|查询结果 employee = {}", area.toString());
                return R.ok().put("area", area);
            } else {
                logger.info("AreaController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("AreaController/info|查询失败，原因：{}", e.getMessage());
            logger.error("AreaController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateArea")
    public R update(@RequestBody Area dto){
        try {
            areaService.update(dto);
        } catch (Exception e) {
            logger.info("AreaController/update|修改失败，原因：{}", e.getMessage());
            logger.error("AreaController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("AreaController/delete|ids = {}", id);
        try{
            areaService.delete(id);
        }catch (Exception e){
            logger.info("AreaController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("AreaController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }

        return R.ok();
    }


}
