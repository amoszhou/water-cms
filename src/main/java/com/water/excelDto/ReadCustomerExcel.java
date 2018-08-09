package com.water.excelDto;

import com.water.domain.Customer;

import java.util.List;

/**
 * @Author : 林吉达
 * @Description : 读取顾客excel
 * @Date: Created in 10:56 2018/8/9
 * @Modified By:
 */
public class ReadCustomerExcel {
    private String errorMsg;
    private List<Customer> excelList;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<Customer> getExcelList() {
        return excelList;
    }

    public void setExcelList(List<Customer> excelList) {
        this.excelList = excelList;
    }
}
