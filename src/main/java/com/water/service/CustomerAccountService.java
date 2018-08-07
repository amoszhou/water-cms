package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.config.HttpServletRequestUtil;
import com.water.constant.ChargeType;
import com.water.constant.EmployeeType;
import com.water.constant.PayType;
import com.water.dao.CustomerAccountDAO;
import com.water.dao.CustomerDAO;
import com.water.domain.ChargeRecord;
import com.water.domain.CustomerAccount;
import com.water.domain.IdAndNameDTO;
import com.water.exception.BizException;
import com.water.util.PageUtil;
import com.water.util.Query;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 9:25 2018/7/17
 * @Modified By:
 */
@Service
public class CustomerAccountService {

    Logger logger = LoggerFactory.getLogger(CustomerAccountService.class);
    @Autowired
    private CustomerAccountDAO customerAccountDAO;
    @Autowired
    private ChargeRecordService chargeRecordService;
    @Autowired
    private CustomerDAO customerDAO;

    private static final String FACTORYIDS = "factoryIds";

    private static final String USERTYPE = "userType";

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 10:13 2018/7/3
     */
    @FactoryIds
    public R queryList(Map<String, Object> params) {
        logger.info("CustomerAccountService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = customerAccountDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<CustomerAccount> customerAccounts = null;
        if (total_count > 0) {
            customerAccounts = customerAccountDAO.queryList(query);
            logger.info("customerAccounts = {}", customerAccounts.toString());
        }
        PageUtil pageUtil = new PageUtil(customerAccounts, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public void save(CustomerAccount customerAccount) {
        if (customerAccount != null) {

            //检查这个customerId 是否已经存在customerAccount表中
            if (customerAccountDAO.selectByCustId(customerAccount.getCustId()) > 0) {
                throw new BizException("此顾客已经有账号了！");
            }

            //todo 获取user
            customerAccount.setUpdateUser(1);
            customerAccountDAO.insertSelective(customerAccount);
        }
    }

    public CustomerAccount queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id", id);
        return (CustomerAccount) customerAccountDAO.queryList(map).get(0);
    }

    //充值成功之后要生成消费记录
    @Transactional
    public int update(CustomerAccount customerAccount,int flag) {
        if (customerAccount != null) {
            customerAccount.setUpdateTime(LocalDateTime.parse(customerAccount.getUpdateTimeForHTML()));
            //todo 获取user
            customerAccount.setUpdateUser(1);
            //使用updateTime作为乐观锁
            int result = customerAccountDAO.updateByPrimaryKeySelective(customerAccount);
            if (result == 0)
                throw new BizException("缴费失败，有多人同时操作该用户的账户余额!");

            if(flag == ChargeType.JIAOFEI.getChargeTpe())
                return  result;

            //生成消费记录
            ChargeRecord chargeRecord = new ChargeRecord();
            chargeRecord.setCustId(customerAccount.getCustId());
            chargeRecord.setCustCode(customerDAO.selectByPrimaryKey(customerAccount.getCustId()).getCode());
            chargeRecord.setAmount(customerAccount.getRaiseMoney());
            //后期做成枚举  消费类型（1--充值，2--缴费）
            chargeRecord.setChargeType(ChargeType.YUCUN.getChargeTpe());
            chargeRecord.setFactoryId(customerAccount.getFactoryId());
            //充值的支付类型全部为5
            chargeRecord.setPayType(PayType.CHONGZHI.getPayType());
            chargeRecordService.save(chargeRecord);
            return result;
        }
        return 0;
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            customerAccountDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }
    }

    public List<IdAndNameDTO> selectCustomerMessage() {
        Map map = new HashMap();
        int userType = (Integer) HttpServletRequestUtil.getRequst().getSession().getAttribute(USERTYPE);
        if (userType == EmployeeType.NORMAL_MANAGER.getTypeId())
            map.put(FACTORYIDS, HttpServletRequestUtil.getRequst().getSession().getAttribute(FACTORYIDS));
        return customerAccountDAO.selectCustomerMessage(map);
    }

}
