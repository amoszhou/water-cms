package com.water.controller;

import com.water.domain.Archive;
import com.water.domain.IdAndNameDTO;
import com.water.service.ArchiveService;
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
 * @Description : 表册Controller
 * @Date: Created in 14:14 2018/7/4
 * @Modified By:
 */
@RestController
@RequestMapping("/archive")
public class ArchiveController {


    Logger logger = LoggerFactory.getLogger(ArchiveController.class);

    @Autowired
    private ArchiveService archiveService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        logger.info("ArchiveController.queryList|begin ---> params : {}",params.toString());
        try{
            R result = archiveService.queryList(params);
            logger.info("ArchiveController.queryList|end ---> result : {}",result.get("page").toString());
            return result;
        }catch (Exception e) {
            logger.info("ArchiveController.queryList|查询失败，原因：{}",e.getMessage());
            logger.error("ArchiveController.queryList|", e);
            return R.error();
        }
    }

    /**
     * 保存
     */
    @PostMapping("/addArchive")
    public R save(@RequestBody Archive dto){
        try {
            archiveService.save(dto);
        } catch (Exception e) {
            logger.info("ArchiveController/save|添加失败，原因：{}", e.getMessage());
            logger.error("ArchiveController/save|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping(value = "/{id}/info")
    public R info(@PathVariable Integer id) {
        logger.info("ArchiveController/info|id = {}", id);
        Archive archive = archiveService.queryObject(id);
        try {
            if (archive != null) {
                logger.info("ArchiveController.info|查询结果 employee = {}", archive.toString());
                return R.ok().put("archive", archive);
            } else {
                logger.info("ArchiveController.info|查询失败，没有该结果");
                return R.error("不存在该记录");
            }
        } catch (Exception e) {
            logger.info("ArchiveController/info|查询失败，原因：{}", e.getMessage());
            logger.error("ArchiveController/info|Exception:"+e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/updateArchive")
    public R update(@RequestBody Archive dto){
        try {
            archiveService.update(dto);
        } catch (Exception e) {
            logger.info("ArchiveController/update|修改失败，原因：{}", e.getMessage());
            logger.error("ArchiveController/update|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/{id}/del")
    public R delete(@PathVariable Long id){
        logger.info("ArchiveController/delete|ids = {}", id);
        try{
            archiveService.delete(id);
        }catch (Exception e){
            logger.info("ArchiveController/delete|删除失败，原因：{}", e.getMessage());
            logger.error("ArchiveController/delete|Exception:"+e.getMessage(), e);
            return R.error();
        }
        return R.ok();
    }

    /**
     * 取得所有的片区的ID和Name
     */
    @GetMapping(value = "/getAreaMessage")
    public List<IdAndNameDTO> getAreaMessage(){
        logger.info("ArchiveController/getAreaMessage begin");
        List<IdAndNameDTO> list = new ArrayList<>();
        try {
            list = archiveService.selectAreaMessage();
            for (IdAndNameDTO idAndNameDTO :list) {
                idAndNameDTO.setIdAndName(idAndNameDTO.getId()+":"+ idAndNameDTO.getName());
            }
        } catch (Exception e) {
            logger.info("ArchiveController/getAreaMessage|查询失败，原因：{}", e.getMessage());
            logger.error("ArchiveController/getAreaMessage|Exception:"+e.getMessage(), e);
        }
        return  list;
    }

    /**
     * 取得所有的营业厅的ID和Name
     */
    @GetMapping(value = "/getHallMessage")
    public List<IdAndNameDTO> getHallMessage(){
        logger.info("ArchiveController/getHallMessage begin");
        List<IdAndNameDTO> list = new ArrayList<>();
        try {
            list = archiveService.selectHallMessage();
            for (IdAndNameDTO idAndNameDTO :list) {
                idAndNameDTO.setIdAndName(idAndNameDTO.getId()+":"+ idAndNameDTO.getName());
            }
        } catch (Exception e) {
            logger.info("ArchiveController/getHallMessage|查询失败，原因：{}", e.getMessage());
            logger.error("ArchiveController/getHallMessage|Exception:"+e.getMessage(), e);
        }
        return  list;
    }

    /**
     * 取得所有的表册的ID和Name
     */
    @GetMapping(value = "/getArchiveMessage")
    public List<IdAndNameDTO> getArchiveMessage(){
        logger.info("ArchiveController/getArchiveMessage begin");
        List<IdAndNameDTO> list = new ArrayList<>();
        try {
            list = archiveService.getArchiveMessage();
            for (IdAndNameDTO idAndNameDTO :list) {
                idAndNameDTO.setIdAndName(idAndNameDTO.getId()+":"+ idAndNameDTO.getName());
            }
        } catch (Exception e) {
            logger.info("ArchiveController/getArchiveMessage|查询失败，原因：{}", e.getMessage());
            logger.error("ArchiveController/getArchiveMessage|Exception:"+e.getMessage(), e);
        }
        return  list;
    }



}
