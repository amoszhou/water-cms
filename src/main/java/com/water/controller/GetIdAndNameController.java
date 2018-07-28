package com.water.controller;

import com.water.domain.IdAndNameDTO;
import com.water.service.HallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 10:28 2018/7/26
 * @Modified By:
 */
@RestController
@RequestMapping("/getIdAndName")
public class GetIdAndNameController {

    Logger logger = LoggerFactory.getLogger(GetIdAndNameController.class);

    @Autowired
    private HallService hallService;


    /**
     * 取得所有的水表的ID和Name
     */
    @GetMapping(value = "/getMeterMessage")
    public List<IdAndNameDTO> getMeterMessage(){
        logger.info("GetIdAndNameController/getMeterMessage begin");
        List<IdAndNameDTO> list = new ArrayList<>();
        try {
            list = hallService.getMeterMessage();
            for (IdAndNameDTO idAndNameDTO :list) {
                idAndNameDTO.setIdAndName(idAndNameDTO.getId()+":"+ idAndNameDTO.getName());
            }
        } catch (Exception e) {
            logger.info("GetIdAndNameController/getMeterMessage|查询失败，原因：{}", e.getMessage());
            logger.error("GetIdAndNameController/getMeterMessage|Exception:"+e.getMessage(), e);
        }
        return  list;
    }

    /**
     * 取得所有的水表的ID和Name
     */
    @GetMapping(value = "/getPriceTypeMessage")
    public List<IdAndNameDTO> getPriceTypeMessage(){
        logger.info("GetIdAndNameController/getPriceTypeMessage begin");
        List<IdAndNameDTO> list = new ArrayList<>();
        try {
            list = hallService.getPriceTypeMessage();
            for (IdAndNameDTO idAndNameDTO :list) {
                idAndNameDTO.setIdAndName(idAndNameDTO.getId()+":"+ idAndNameDTO.getName());
            }
        } catch (Exception e) {
            logger.info("GetIdAndNameController/getPriceTypeMessage|查询失败，原因：{}", e.getMessage());
            logger.error("GetIdAndNameController/getPriceTypeMessage|Exception:"+e.getMessage(), e);
        }
        return  list;
    }


}
