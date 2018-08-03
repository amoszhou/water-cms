package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.dao.PayRecordDAO;
import com.water.dao.PriceTypeDAO;
import com.water.dao.WaterRecordDAO;
import com.water.domain.PayRecord;
import com.water.domain.PriceType;
import com.water.domain.WaterRecord;
import com.water.util.PageUtil;
import com.water.util.Query;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 10:33 2018/7/21
 * @Modified By:
 */
@Service
public class WaterRecordService {

    Logger logger = LoggerFactory.getLogger(WaterRecordService.class);

    @Autowired
    private WaterRecordDAO waterRecordDAO;
    @Autowired
    private PriceTypeDAO priceTypeDAO;
    @Autowired
    private PayRecordDAO payRecordDAO;


    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 17:39 2018/7/3
     */
    @FactoryIds
    public R queryList(Map<String, Object> params) {
        logger.info("WaterRecordService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = waterRecordDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<WaterRecord> waterRecords = null;
        if (total_count > 0) {
            waterRecords = waterRecordDAO.queryList(query);
            logger.info("serviceList = {}", waterRecords.toString());
        }
        PageUtil pageUtil = new PageUtil(waterRecords, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    @Transactional
    public void save(WaterRecord waterRecord) {
        waterRecord = new WaterRecord();
        waterRecord.setCurrNumber(BigDecimal.valueOf(10));
        waterRecord.setLastNumber(BigDecimal.valueOf(1));
        waterRecord.setCustId(2);
        waterRecord.setMeterId(1);
        waterRecord.setFactoryId(1);
        waterRecord.setWaterRecordBeginDate(LocalDate.of(2018, 6, 10));
        waterRecord.setWaterRecordEndDate(LocalDate.of(2018, 7, 10));
        if (waterRecord != null) {
            //组装创建人
            String waterRecordCode = UUID.randomUUID().toString();
            waterRecord.setCreateUser(1);
            waterRecord.setCode(waterRecordCode);

            //计算 正常水费，污水费，汇总水费
            Map queryMap = new HashMap();
            queryMap.put("factoryId", waterRecord.getFactoryId() + "");
            PriceType priceType = (PriceType) priceTypeDAO.queryList(queryMap).get(0);
            //用水量
            BigDecimal usedWaterRecord = waterRecord.getCurrNumber().subtract(waterRecord.getLastNumber());
            //正常水费
            BigDecimal normalWaterFee = usedWaterRecord.multiply(priceType.getPrice());
            //污水费
            BigDecimal sewageWaterFee = usedWaterRecord.multiply(priceType.getSewage());
            //汇总水费
            BigDecimal totalFee = normalWaterFee.add(sewageWaterFee);
            waterRecord.setFee(totalFee);

            int waterRecordId = waterRecordDAO.insertSelective(waterRecord);
            //生成支付记录
            PayRecord payRecord = new PayRecord();
            payRecord.setWaterRecordCode(waterRecordCode);
            payRecord.setWaterRecordId(waterRecordId);
            payRecord.setCustomerId(waterRecord.getCustId());
            payRecord.setFactoryId(waterRecord.getFactoryId());
            payRecord.setWaterBeginDate(waterRecord.getWaterRecordBeginDate());
            payRecord.setWaterEndDate(waterRecord.getWaterRecordEndDate());
            payRecord.setWaterFee(normalWaterFee);
            payRecord.setSewageFee(sewageWaterFee);
            payRecord.setLateFee(BigDecimal.ZERO);
            payRecord.setTotalFee(normalWaterFee.add(sewageWaterFee).add(payRecord.getLateFee()));
            payRecordDAO.insertSelective(payRecord);
        }
    }

    public WaterRecord queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id", id);
        return (WaterRecord) waterRecordDAO.queryList(map).get(0);
    }


    public void update(WaterRecord waterRecord) {
        if (waterRecord != null) {
            //
            waterRecord.setUpdateUser(1);
            //这里没这么简单，修改的话需要修改t_pay_Record表的数据，如果t_pay_Record的记录为已支付，则不允许修改。
            waterRecordDAO.updateByPrimaryKeySelective(waterRecord);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Employee表里的数据
            waterRecordDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据    t_pay_Record 表
        }

    }

}
