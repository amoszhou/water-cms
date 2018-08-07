package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.constant.ChargeType;
import com.water.constant.PayState;
import com.water.constant.PayType;
import com.water.dao.CustomerAccountDAO;
import com.water.dao.CustomerDAO;
import com.water.dao.PayRecordDAO;
import com.water.domain.ChargeRecord;
import com.water.domain.CustomerAccount;
import com.water.domain.PayRecord;
import com.water.exception.BizException;
import com.water.util.PageUtil;
import com.water.util.Query;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 17:08 2018/7/21
 * @Modified By:
 */
@Service
public class PayRecordService {

    Logger logger = LoggerFactory.getLogger(PayRecordService.class);
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private PayRecordDAO payRecordDAO;
    @Autowired
    private ChargeRecordService chargeRecordService;
    @Autowired
    private CustomerAccountService customerAccountService;
    @Autowired
    private CustomerAccountDAO customerAccountDAO;

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 10:13 2018/7/3
     */
    @FactoryIds
    public R queryList(Map<String, Object> params) {
        logger.info("PayRecordService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = payRecordDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<PayRecord> payRecords = null;
        if (total_count > 0) {
            payRecords = payRecordDAO.queryList(query);
            logger.info("hallList = {}", payRecords.toString());
        }
        PageUtil pageUtil = new PageUtil(payRecords, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

   /* */

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public PayRecord queryObject(Integer id) {

        Map map = new HashMap();
        map.put("id", id);
        return (PayRecord) payRecordDAO.queryList(map).get(0);
    }


    //点击缴费之后，还要生成消费记录
    //todo 如果payrecord.payType = 4，即顾客账户余额缴费，还要检查顾客账户余额是否充足
    @Transactional
    public void update(PayRecord payRecord) {
        //不缴费，不做任何修改
        if (payRecord.getPayType() == PayType.NOTPAYYET.getPayType() || payRecord.getPayState() != PayState.NOTPAY.getState()) {
            throw new BizException("此条记录已缴费!请勿重复交费!");
        }


        //todo 顾客账号余额缴费，需检查用户余额并扣除余额
        if (payRecord.getPayType() == PayType.BALANCE.getPayType()) {
            CustomerAccount customerAccount = customerAccountDAO.getByCustId(payRecord.getCustomerId());
            //如果用户没有账户,创建一个

            //余额不足缴费
            if (customerAccount.getBalance().compareTo(payRecord.getTotalFee()) < 0)
                throw new BizException("缴费失败，顾客账户余额不足!");

                customerAccount.setRaiseMoney(payRecord.getTotalFee().multiply(BigDecimal.valueOf(-1)));

            int result = customerAccountService.update(customerAccount,ChargeType.JIAOFEI.getChargeTpe());
            if (result == 0)
                throw new BizException("缴费失败，有多人同时操作顾客账户!");
        }

        if (payRecord != null) {
            //修改状态变成已支付
            payRecord.setPayState(PayState.PAY.getState());
            payRecordDAO.updateByPrimaryKeySelective(payRecord);
        }


        //生成消费记录
        ChargeRecord chargeRecord = new ChargeRecord();
        chargeRecord.setCustId(payRecord.getCustomerId());
        chargeRecord.setCustCode(customerDAO.selectByPrimaryKey(payRecord.getCustomerId()).getCode());
        chargeRecord.setAmount(payRecord.getTotalFee());
        chargeRecord.setFactoryId(payRecord.getFactoryId());
        //后期做成枚举  消费类型（1--充值，2--缴费）
        chargeRecord.setChargeType(ChargeType.JIAOFEI.getChargeTpe());
        chargeRecord.setPayType(payRecord.getPayType());
        chargeRecordService.save(chargeRecord);

    }

   /* @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Employee表里的数据
            meterDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }
*/

}
