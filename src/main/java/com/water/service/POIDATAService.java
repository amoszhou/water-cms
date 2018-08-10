package com.water.service;

import com.water.config.HttpServletRequestUtil;
import com.water.dao.*;
import com.water.domain.Customer;
import com.water.domain.PayRecord;
import com.water.domain.PriceType;
import com.water.domain.WaterRecord;
import com.water.excelDto.ReadCustomerExcel;
import com.water.excelDto.ReadWaterRecordExcel;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @Description : 解析Excel数据 customer
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
                errorMsg.append(br + "第" + (i + 1) + "行数据有问题，请仔细检查,此行插入失败");
                continue;
            }

            Customer customer = new Customer();
            customer.setCreateUser(employeeId);
            customer.setUpdateUser(employeeId);

            //遍历列
            for (int j = 0; j < totalCells; j++) {
                Cell cell = row.getCell(j);
                if (null == cell) {
                    rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列数据有问题，请仔细检查,此行插入失败";
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
                        else {
                            customer.setMeterId(meterId);
                            customer.setMeterCustCode(customer.getCode() + ":" + meterId);
                        }

                        break;
                    case 8:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String area = formatter.formatCellValue(cell);
                        if (StringUtils.isEmpty(area))
                            break;
                        Integer areaId = Integer.parseInt(area);
                        customer.setAreaId(areaId);
                        break;
                    case 9:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String hall = formatter.formatCellValue(cell);
                        if (StringUtils.isEmpty(hall))
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
                        String archive = formatter.formatCellValue(cell);
                        if (StringUtils.isEmpty(archive))
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
            logger.error("批量导入顾客资源异常：" + ex.getMessage(), ex);
        }
    }


//------------------WATERRECORD 以下是读取waterRecord Excel的逻辑  --------------------------------------------------------


    /**
     * @Author : 林吉达
     * @Description : 检查并解析Excel数据,保存相关客户数据
     * @Date : 11:02 2018/8/9
     */
    public String checkWaterExcelData(MultipartFile file, String fileName) {
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
            ReadWaterRecordExcel readExcel = readWaterRecordExcelData(workbook);
            if (readExcel == null) return errorMsg;

            //保存用水记录和缴费记录
            if (readExcel.getExcelList() != null && readExcel.getExcelList().size() > 0) {
                String errorMsgTwo = saveWaterRecordData(readExcel.getExcelList());
                if(errorMsgTwo != "" || errorMsgTwo.length() >=1 ){
                    readExcel.setErrorMsg(readExcel.getErrorMsg()+errorMsgTwo);
                }
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
    private ReadWaterRecordExcel readWaterRecordExcelData(Workbook wb) {
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

        List<WaterRecord> list = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        //换行符
        String br = "<br/>";
        //遍历行
        for (int i = 1; i < totalRows; i++) {
            String rowMsg = "";
            Row row = sheet.getRow(i);
            if (null == row) {
                errorMsg.append(br + "第" + (i + 1) + "行数据有问题，请仔细检查,此行插入失败");
                continue;
            }

            WaterRecord waterRecord = new WaterRecord();
            waterRecord.setCreateUser(employeeId);
            waterRecord.setUpdateUser(employeeId);

            //遍历列
            for (int j = 0; j < totalCells; j++) {
                Cell cell = row.getCell(j);
                if (null == cell) {
                    rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列数据有问题，请仔细检查,此行插入失败";
                    continue;
                }
                switch (j) {
                    case 0:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String custCode = formatter.formatCellValue(cell);
                        if (custCode == null || custCode.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客编码不能为空,此行插入失败";
                        else
                            waterRecord.setCustCode(custCode);
                        break;
                    case 1:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String currentNumberString = formatter.formatCellValue(cell);
                        if (currentNumberString == null || currentNumberString.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列水表显示数字不能为空,此行插入失败";
                        else {
                            BigDecimal currentNumber = new BigDecimal(currentNumberString);
                            waterRecord.setCurrNumber(currentNumber);
                        }
                        break;
                    case 2:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String lastNumberString = formatter.formatCellValue(cell);
                        if (lastNumberString == null || lastNumberString.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列水表上次显示数字不能为空,此行插入失败";
                        else {
                            BigDecimal currentNumber = new BigDecimal(lastNumberString);
                            waterRecord.setLastNumber(currentNumber);
                        }
                        break;
                    case 3:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if(StringUtils.isEmpty(formatter.formatCellValue(cell)) ||formatter.formatCellValue(cell).equals("") ){
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客所属水厂不能为空,此行插入失败";
                            break;
                        }
                        Integer factoyId = Integer.parseInt(formatter.formatCellValue(cell));
                       /* if (factoyId == null)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列顾客所属水厂不能为空,此行插入失败";
                        else*/
                            waterRecord.setFactoryId(factoyId);
                        break;
                    case 4:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String beginDate = formatter.formatCellValue(cell);
                        if (beginDate == null || beginDate.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列用水记录开始时间不能为空,此行插入失败";
                        else {

                            waterRecord.setWaterRecordBeginDate(LocalDate.parse(beginDate));
                        }
                        break;
                    case 5:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String endDate = formatter.formatCellValue(cell);
                        if (endDate == null || endDate.length() <= 0)
                            rowMsg += br + "第" + (i + 1) + "行,第" + (j + 1) + "列用水记录结束时间不能为空,此行插入失败";
                        else {
                            waterRecord.setWaterRecordEndDate(LocalDate.parse(endDate));
                        }
                        break;

        /*            case 6:
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
                        customer.setTel(tel);
                        break;
                    case 11:
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String archive  = formatter.formatCellValue(cell);
                        if(StringUtils.isEmpty(archive))
                            break;
                        Integer archiveId = Integer.parseInt(archive);
                        customer.setArchiveId(archiveId);
                        break;*/

                }
            }
            //行无错误消息
            if (rowMsg == null || rowMsg.isEmpty())
                list.add(waterRecord);
            //添加到列表错误
            errorMsg.append(rowMsg);
        }
        //返回结果
        ReadWaterRecordExcel readExcel = new ReadWaterRecordExcel();
        readExcel.setErrorMsg(errorMsg.toString());
        readExcel.setExcelList(list);
        return readExcel;
    }

    @Autowired
    private PriceTypeDAO priceTypeDAO;
    @Autowired
    private WaterRecordDAO waterRecordDAO;
    @Autowired
    private PayRecordDAO payRecordDAO;

    /**
     * @Author : 林吉达
     * @Description :
     * @Date : 17:33 2018/8/9
     */
    @Transactional
    public String saveWaterRecordData(List<WaterRecord> excelList) {
        String errorMsg = "";
        //换行符
        String br = "<br/>";
        try {

            //根据custCode查出custId
            List<Customer> customers = customerDAO.getIdAndCode(excelList);
            List<Customer> tepmCustomer;
            List<PriceType> tepmPriceType;

            //初始化custId
            for (int i = 0; i < excelList.size(); i++) {
                String y = excelList.get(i).getCustCode();
                tepmCustomer = customers.stream().filter(x -> x.getCode().equals(y)).collect(Collectors.toList());
                if (tepmCustomer.size() == 0) {
                    errorMsg += br + "找不到顾客编码为" + excelList.get(i).getCustCode() + "的顾客，请审查是否存在这个顾客编码";

                    logger.warn("POIDATAService/saveWaterRecordData找不到顾客编码对应的custId 移除了第{}条数据:{}",i,excelList.get(i).toString());
                    excelList.remove(i);
                    --i;
                    continue;
                }
                excelList.get(i).setCustId(tepmCustomer.get(0).getId());
            }
            List<PriceType> priceTypes = priceTypeDAO.getPriceByCustId(excelList);

            //找到custId对应的priceType 返回resultPriceType
            for(int i = 0; i< excelList.size() ; i++){
                Integer y = excelList.get(i).getCustId();

                tepmPriceType = priceTypes.stream().filter(x -> x.getCustId().compareTo(y) == 0).collect(Collectors.toList());
                if(tepmPriceType.size() == 0){
                    errorMsg += br + "找不到顾客编码为" + excelList.get(i).getCustCode() + "的顾客所绑定的水表价格信息，请审查这个顾客是否已经绑定了水表及价格";

                    logger.warn("POIDATAService/saveWaterRecordData找不到顾客编码对应的priceType 移除了第{}条数据:{}",i,excelList.get(i).toString());
                    excelList.remove(i);
                    --i;
                    continue;
                }
                excelList.get(i).setSwage(tepmPriceType.get(0).getSewage());
                excelList.get(i).setNormal(tepmPriceType.get(0).getPrice());

            }
            //批量插入用水记录
            waterRecordDAO.insertBatch(excelList);

            List<PayRecord> payRecords = new ArrayList<>();
            //批量生成缴费记录
            for(int i = 0 ; i< excelList.size() ; i++){
                PayRecord payRecord = new PayRecord();
                //用水量
                BigDecimal usedWaterRecord = excelList.get(i).getCurrNumber().subtract(excelList.get(i).getLastNumber());
                //正常水费
                BigDecimal normalWaterFee = usedWaterRecord.multiply(excelList.get(i).getNormal());
                //污水费
                BigDecimal sewageWaterFee = usedWaterRecord.multiply(excelList.get(i).getSwage());
                //汇总水费
                BigDecimal totalFee = normalWaterFee.add(sewageWaterFee);
                payRecord.setWaterRecordId(excelList.get(i).getId());
                payRecord.setCustomerId(excelList.get(i).getCustId());
                payRecord.setFactoryId(excelList.get(i).getFactoryId());
                payRecord.setWaterBeginDate(excelList.get(i).getWaterRecordBeginDate());
                payRecord.setWaterEndDate(excelList.get(i).getWaterRecordEndDate());
                payRecord.setWaterFee(normalWaterFee);
                payRecord.setSewageFee(sewageWaterFee);
                payRecord.setLateFee(BigDecimal.ZERO);
                payRecord.setTotalFee(normalWaterFee.add(sewageWaterFee).add(payRecord.getLateFee()));
                payRecords.add(payRecord);
            }

            //批量插入缴费记录
            payRecordDAO.insertBatch(payRecords);





        /*    //插入customer表
            customerDAO.insertBatch(excelList);
            //插入t_customer_account
            customerAccountDAO.insertBatch(excelList);
            //t_customer_meter
            customerMeterDAO.insertBatch(excelList);

        */
           /*
            *//* for(Customer customer : excelList)
                    System.out.println(customer.getPhone()+":"+customer.getName());*/
        } catch (Exception ex) {
            logger.error("批量导入用水记录资源异常：" + ex.getMessage(), ex);
        }
        return errorMsg;
    }
}
