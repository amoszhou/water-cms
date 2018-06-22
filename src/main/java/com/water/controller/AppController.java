package com.water.controller;

import com.water.service.AppService;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * UAS-应用管理
 * Created by yuanyuzhao on 2017/10/9.
 */

@RestController
@RequestMapping("/app")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        try{
            R result = appService.queryList(params);
            return result;
        }catch (Exception e) {
            logger.error("查询应用列表失败,原因{}",e);
            return R.error();
        }
    }

  /*  @RequestMapping(value="/add",method = RequestMethod.POST)
    public R addApp(@RequestBody App app) {
        try{
            ValidatorUtils.validateEntity(app);
            appService.saveApp(app);
            return R.ok();
        } catch (AppBusinessException e) {
            logger.info("新增应用记录失败,原因{}", e.getMessage());
            return R.error(BusCode.APP_RECORD_ADD_FAIL,e.getMessage());
        } catch (Exception e) {
            logger.error("新增应用记录失败,原因{}",e);
            return R.error();
        }
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
    public R delApp(@PathVariable Integer id) {
        try{
            appService.deleteApp(id);
            return R.ok();
        } catch (AppBusinessException e){
            logger.info("删除应用记录失败,原因{}", e.getMessage());
            return R.error(BusCode.APP_RECORD_DEL_FAIL,e.getMessage());
        } catch (Exception e){
            logger.error("删除应用记录失败,原因{}",e);
            return R.error();
        }

    }

    @RequestMapping(value = "/{id}/query", method = RequestMethod.GET)
    public R getApp(@PathVariable Integer id) {
        try{
            App app = appService.getApp(id);
            return R.ok().put("app",app);
        } catch (AppBusinessException e){
            logger.info(e.getMessage());
            return R.error(BusCode.APP_RECORD_QUERY_FAIL,e.getMessage());
        } catch (Exception e){
            logger.error("查询应用记录失败,原因{}",e);
            return R.error();
        }

    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public R updateApp(@RequestBody App app) {
        try{
            ValidatorUtils.validateEntity(app);
            appService.updateApp(app);
            return R.ok();
        } catch (AppBusinessException e) {
            logger.info("更新应用记录失败,原因{}", e.getMessage());
            return R.error(BusCode.APP_RECORD_UPDATE_FAIL,e.getMessage());
        } catch (DuplicateKeyException e){
            logger.info("更新应用记录失败,原因{}", e.getMessage());
            return R.error(BusCode.APP_RECORD_UPDATE_FAIL,"应用标识不能重复");
        } catch (Exception e) {
            logger.error("更新应用记录失败,原因{}",e);
            return R.error();
        }
    }*/

}
