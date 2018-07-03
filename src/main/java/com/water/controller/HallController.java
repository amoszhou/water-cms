package com.water.controller;

import com.water.domain.IdAndNameDTO;
import com.water.domain.Hall;
import com.water.service.HallService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 11:22 2018/7/2
 * @Modified By:
 */
@RestController
@RequestMapping("/hall")
public class HallController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
   private HallService hallService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("HallController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = hallService.queryList(params);
            logger.info("HallController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("HallController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("HallController.queryList|", e);
            return R.error();
        }
    }


    /**
     * 保存
     */
    @PostMapping("/addHall")
    public R save(@RequestBody Hall dto){
        try {
            hallService.save(dto);
        } catch (Exception e) {
            logger.info("HallController/save|添加失败，原因：{}", e.getMessage());
            logger.error("HallController/save|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("HallController/info|id = {}", id);
        Hall hall = hallService.queryObject(id);
        try {
            if (hall != null) {
                logger.info("HallController.info|查询结果 employee = {}", hall.toString());
                return R.ok().put("hall", hall);
            } else {
                logger.info("HallController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("HallController/info|查询失败，原因：{}", e.getMessage());
            logger.error("HallController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateHall")
    public R update(@RequestBody Hall dto){
        try {
            hallService.update(dto);
        } catch (Exception e) {
            logger.info("HallController/update|修改失败，原因：{}", e.getMessage());
            logger.error("HallController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("HallController/delete|ids = {}", id);
        try{
            hallService.delete(id);
        }catch (Exception e){
            logger.info("HallController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("HallController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 取得所有的水厂的ID和Name
     */
    @GetMapping(value = "/getFactoryMessage")
    public List<IdAndNameDTO> getFactoryMessage(){
        logger.info("HallController/getFactoryMessage begin");
        List<IdAndNameDTO> list = new ArrayList<>();
        try {
            list = hallService.selectFactoryMessage();
            for (IdAndNameDTO idAndNameDTO :list) {
                  idAndNameDTO.setIdAndName(idAndNameDTO.getId()+":"+ idAndNameDTO.getName());
            }
        } catch (Exception e) {
            logger.info("HallController/getFactoryMessage|查询失败，原因：{}", e.getMessage());
            logger.error("HallController/getFactoryMessage|Exception:"+e.getMessage(), e);
        }
        return  list;
    }


}
