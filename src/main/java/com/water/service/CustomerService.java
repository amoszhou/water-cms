package com.water.service;

import com.water.constant.ChargeType;
import com.water.domain.*;
import com.water.exception.BizException;
import com.water.repository.ChargeRecordRepository;
import com.water.repository.CustomerAccountRepository;
import com.water.repository.CustomerMeterRepository;
import com.water.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 基础数据管理
 * Created by 周美华 on 2017/4/1.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerMeterRepository customerMeterRepository;

    @Autowired
    private ChargeRecordRepository chargeRecordRepository;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;


    /**
     * 查询所有可用的用户
     *
     * @return
     */
      public List<Customer> findAllEnableCustomer() {
        // TODO: 2017/4/3 status需要定义
        return customerRepository.findByIsDelete(1);
    }




    /**
     * 查询所有可用的水表
     *
     * @return
     */
    public List<CustomerMeter> findAllEnableMeter() {
        // TODO: 2017/4/3 status需要定义
        return customerMeterRepository.findByIsDelete(1);
    }


    public void createCustomer(Customer customer) {
        customer = customerRepository.saveAndFlush(customer);

        CustomerAccount account = new CustomerAccount();
        account.setBalance(0);
        account.setCustId(account.getId());
        account.setVersion(0);
        account.setUpdateTime(new Date());
        account.setUpdateUser(customer.getCreateUser());
        customerAccountRepository.saveAndFlush(account);
    }


    /**
     * 用户充值（预存）
     *
     * @param user   操作员
     * @param record 充值信息
     */
    public void prePay(Employee user, ChargeType type, ChargeRecord record) {
        Customer customer = customerRepository.findOne(record.getCustId());
        if (customer == null) {
            throw new BizException("用户不存在，不能进行操作");
        }
        if (!customer.getFactoryId().equals(user.getFactoryId())) {
            throw new BizException("您没有权限对该用户进行操作");
        }
        record.setCreateTime(new Date());
        record.setCreateUser(user.getId());

        /**更新余额*/
        CustomerAccount account = customerAccountRepository.findByCustId(customer.getId());
        customerAccountRepository.updateBalance(record.getAmount(), user.getId(), customer.getId(), account.getVersion());

        chargeRecordRepository.save(record);
    }
}
