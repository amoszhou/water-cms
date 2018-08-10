package com.water.controller;

import com.water.service.POIDATAService;
import com.water.util.R;
import com.water.util.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : 林吉达
 * @Description :  使用poi 导入导出excel
 * @Date: Created in 10:50 2018/8/9
 * @Modified By:
 */
@RestController
@RequestMapping("/poi")
public class POIDATAController {

    Logger logger = LoggerFactory.getLogger(POIDATAController.class);
    @Autowired
    private POIDATAService poidataService;



    /**
      * @Author : 林吉达
      * @Description : 导入用水记录excel (会自动生成缴费记录)
      * @Date : 9:48 2018/8/10
      */
    @PostMapping(value = "/importWaterRecordExcelData")
    public R importWaterRecordExcelData(@RequestParam(value = "fileName") MultipartFile file) {
        if (file == null || file.getSize() <= 0)
            return R.error("文件为空");
        //文件名称
        String fileName = file.getOriginalFilename();
        //验证文件格式，只支持.xls,.xlsx
        if (!ValidatorUtils.validateExcel(fileName))
            return R.error("文件格式错误，仅支持xls或xlsx文件");
        //检查并解析Excel数据
        String readResult = poidataService.checkWaterExcelData(file, fileName);
        if (readResult == null || readResult.length() <= 0)
            return R.ok();
        return R.error(readResult);
    }


    /**
     * @Author : 林吉达
     * @Description : 导入顾客信息excel (会自动生成顾客账户,顾客水表关系)
     * @Date : 10:51 2018/8/9
     */
    @PostMapping(value = "/importCustomerExcelData")
    public R importExcelData(@RequestParam(value = "fileName") MultipartFile file) {
        if (file == null || file.getSize() <= 0)
            return R.error("文件为空");
        //文件名称
        String fileName = file.getOriginalFilename();
        //验证文件格式，只支持.xls,.xlsx
        if (!ValidatorUtils.validateExcel(fileName))
            return R.error("文件格式错误，仅支持xls或xlsx文件");
        //检查并解析Excel数据
        String readResult = poidataService.checkExcelData(file, fileName);
        if (readResult == null || readResult.length() <= 0)
            return R.ok();
        return R.error(readResult);
    }


}
