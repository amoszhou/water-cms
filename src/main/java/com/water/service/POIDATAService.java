package com.water.service;

import com.water.config.HttpServletRequestUtil;
import com.water.dao.CustomerAccountDAO;
import com.water.dao.CustomerDAO;
import com.water.dao.CustomerMeterDAO;
import com.water.domain.Customer;
import com.water.excelDto.ReadCustomerExcel;
import com.water.util.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 10:54 2018/8/9
 * @Modified By:
 */
@Service
public class POIDATAService {

    Logger logger = LoggerFactory.getLogger(POIDATAService.class);

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CustomerAccountDAO customerAccountDAO;
    @Autowired
    private CustomerMeterDAO customerMeterDAO;

    /**
     * @Author : 林吉达
     * @Description : 检查并解析Excel数据,保存相关客户数据
     * @Date : 11:02 2018/8/9
     */
    public String checkExcelData(MultipartFile file, String fileName) {
        String errorMsg = "导入资源错误";
        InputStream fs = null;
        try {
            fs = file.getInputStream();
            //根据版本选择创建Workbook的方式
            Workbook workbook = null;
            //根据文件名判断文件是2003版本还是2007版本
            if (ValidatorUtils.isExcel2007(fileName)) {
                workbook = new XSSFWorkbook(fs);
            } else {
                workbook = new HSSFWorkbook(fs);
            }

            if (workbook == null) return errorMsg;

            //解析Excel
            ReadCustomerExcel readExcel = readCustomerExcelData(workbook);
            if (readExcel == null) return errorMsg;

            //保存客户资源数据和分配记录
            if (readExcel.getExcelList() != null && readExcel.getExcelList().size() > 0) {
                saveCustomerData(readExcel.getExcelList());
                if (readExcel.getErrorMsg() == null || readExcel.getErrorMsg().length() <= 0)
                    return null;
            }

            return readExcel.getErrorMsg();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return errorMsg;
    }


    /**
     * @Author : 林吉达
     * @Description : 解析Excel数据
     * @Date : 11:02 2018/8/9
     */
    private ReadCustomerExcel readCustomerExcelData(Workbook wb) {
       Integer employeeId = HttpServletRequestUtil.getUserId();
        if (wb == null) return null;
        StringBuilder errorMsg = new StringBuilder();
        //sheet
        Sheet sheet = wb.getSheetAt(0);
        //总行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到Excel的列数(前提是有行数)，从第二行算起
        if (totalRows >= 2 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }

        List<Customer> list = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        //换行符
        String br = "<br/>";
        //遍历行
        for (int i = 1; i < totalRows; i++) {
            String rowMsg = "";
            Row row = sheet.getRow(i);
            if (null == row) {
                errorMsg.append(br + "第" + (i+1) + "行数据有问题，请仔细检查,此行插入失败");
                continue;
            }

            Customer customer = new Customer();
            customer.setCreateUser(employeeId);
            customer.setUpdateUser(employeeId);

            //遍历列
            for (int j = 0; j < totalCells; j++) {
                Cell cell = row.getCell(j);
                if (null == cell) {
                    rowMsg += br + "第" + (i + 1) + "行,第"+ (j + 1) + "列数据有问题，请仔细检查,此行插入失败";
                    continue;
                }
                switch (j) {
                    case 0:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String code = formatter.formatCellValue(cell);
                        if (code == null || code.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客编码不能为空,此行插入失败";
                        else
                            customer.setCode(code);
                        break;
                    case 1:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String name = formatter.formatCellValue(cell);
                        if (name == null || name.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客姓名不能为空,此行插入失败";
                        else
                            customer.setName(name);
                        break;
                    case 2:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String phone = formatter.formatCellValue(cell);
                        if (phone == null || phone.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客电话不能为空,此行插入失败";
                        else
                            customer.setPhone(phone);
                        break;
                    case 3:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        Integer factoyId = Integer.parseInt(formatter.formatCellValue(cell));
                        if (factoyId == null)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客所属水厂不能为空,此行插入失败";
                        else
                            customer.setFactoryId(factoyId);
                        break;
                    case 4:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String idCard = formatter.formatCellValue(cell);
                        if (idCard == null || idCard.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客身份证不能为空,此行插入失败";
                        else
                            customer.setIdCard(idCard);
                        break;
                    case 5:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String address = formatter.formatCellValue(cell);
                        if (address == null || address.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客住址不能为空,此行插入失败";
                        else
                            customer.setAddress(address);
                        break;
                        //  case 6,7 为了生成顾客账户 customerAccount
                    case 6:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        Integer priceTypeId = Integer.parseInt(formatter.formatCellValue(cell));
                        if (priceTypeId == null)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客所属价格Id不能为空,此行插入失败";
                        else
                            customer.setPriceTypeId(priceTypeId);
                        break;
                    case 7:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        Integer meterId = Integer.parseInt(formatter.formatCellValue(cell));
                        if (meterId == null)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客所属水表Id不能为空,此行插入失败";
                        else{
                            customer.setMeterId(meterId);
                            customer.setMeterCustCode(customer.getCode()+":"+meterId);
                        }

                        break;
                    case 8:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String area  = formatter.formatCellValue(cell);
                        if(StringUtils.isEmpty(area))
                            break;
                        Integer areaId = Integer.parseInt(area);
                        customer.setAreaId(areaId);
                        break;
                    case 9:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String hall  = formatter.formatCellValue(cell);
                        if(StringUtils.isEmpty(hall))
                            break;
                        Integer hallId = Integer.parseInt(hall);
                        customer.setHallId(hallId);
                        break;
                    case 10:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String tel = formatter.formatCellValue(cell);
                    /*    if (tel == null || tel.length() <= 0)
                            rowMsg += br + "第"+(i+1)+"行,第"+(j+1)+"列顾客身份证不能为空；";*/
                        customer.setTel(tel);
                        break;
                    case 11:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String archive  = formatter.formatCellValue(cell);
                        if(StringUtils.isEmpty(archive))
                            break;
                        Integer archiveId = Integer.parseInt(archive);
                        customer.setArchiveId(archiveId);
                        break;
                  /*  case 3:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String userNo = formatter.formatCellValue(cell);
                        if (userNo == null || userNo.length() <= 0)
                            rowMsg += br + "工号不能为空；";
                        customer.setUserNo(userNo);
                        break;*/
                }
            }
            //行无错误消息
            if (rowMsg == null || rowMsg.isEmpty())
                list.add(customer);
            //添加到列表错误
            errorMsg.append(rowMsg);
        }
        //返回结果
        ReadCustomerExcel readExcel = new ReadCustomerExcel();
        readExcel.setErrorMsg(errorMsg.toString());
        readExcel.setExcelList(list);
        return readExcel;
    }


    /**
     * @Author : 林吉达
     * @Description : 将excel 的数据保存到db
     * @Date : 11:03 2018/8/9
     */
    @Transactional
    public void saveCustomerData(List<Customer> excelList) {
        try {
            //插入customer表
            customerDAO.insertBatch(excelList);
            //插入t_customer_account
            customerAccountDAO.insertBatch(excelList);
            //t_customer_meter
            customerMeterDAO.insertBatch(excelList);

        /*    for(Customer customer : excelList)
                System.out.println(customer.getId());*/
            /* for(Customer customer : excelList)
                    System.out.println(customer.getPhone()+":"+customer.getName());*/
        } catch (Exception ex) {
            logger.error("批量导入顾客资源异常：" + ex.getMessage(),ex);
        }
    }
}
