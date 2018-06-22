/*
package com.water.service;

import com.water.constant.GlobalSetting;
import com.water.domain.Customer;
import com.water.domain.CustomerMeter;
import com.water.domain.WaterRecord;
import com.water.exception.BizException;
import com.water.repository.CustomerRepository;
import com.water.repository.WaterRecordRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

*/
/**
 * Created by Administrator on 2017/4/3.
 *//*

@Service
public class WaterRecordService {

    @Autowired
    private WaterRecordRepository waterRecordRepository;
    @Autowired
    private CustomerService customerService;

    */
/**
     * 抄表准备
     *
     * @param period 月份
     *//*

    public void createWaterReocrd(String period) {

        List<Customer> customers = customerService.findAllEnableCustomer();
        List<CustomerMeter> meters = customerService.findAllEnableMeter();
//        customers.stream().parallel().flatMap(customer -> customer.)
//
//        waterRecordRepository.save()

    }

    */
/**
     * 抄表
     * 1.首先更新本月生成的用水记录
     * 2.生成下个月的起始码数
     *//*

    public void doCopy(String period, String custCode, int currNum, int operator) {
        List<WaterRecord> records = waterRecordRepository.findByPeriodAndCustCode(period, custCode);
        if (records == null) {
            throw new BizException("未查询到该用户的用水记录");
        }
        records.forEach(record -> {
            waterRecordRepository.updateCurrentNumber(currNum, operator, new Date(), record.getId());
            WaterRecord nextMonth = new WaterRecord();
            BeanUtils.copyProperties(record, nextMonth);
            nextMonth.setCreateTime(new Date());
            nextMonth.setCreateUser(GlobalSetting.SYSTEM_OP);
            nextMonth.setLastNumber(currNum);
            nextMonth.setCurrNumber(0);
            // TODO: 2017/4/3 计算费用

        });
    }


}
*/
