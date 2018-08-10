package com.water.excelDto;

import com.water.domain.Customer;
import com.water.domain.WaterRecord;

import java.util.List;

/**
 * @Author : 林吉达
 * @Description : 读取用水记录excel
 * @Date: Created in 17:34 2018/8/9
 * @Modified By:
 */
public class ReadWaterRecordExcel {
    private String errorMsg;
    private List<WaterRecord> excelList;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<WaterRecord> getExcelList() {
        return excelList;
    }

    public void setExcelList(List<WaterRecord> excelList) {
        this.excelList = excelList;
    }
}
