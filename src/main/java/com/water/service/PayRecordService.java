package com.water.service;

import com.water.dao.PayRecordDAO;
import com.water.domain.PayRecord;
import com.water.util.PageUtil;
import com.water.util.Query;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private PayRecordDAO payRecordDAO;


    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 10:13 2018/7/3
     */
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

   /* *//**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     *//*
    public void save(PayRecord payRecords) {
        if (payRecords != null) {
            meterDAO.insertSelective(payRecords);
        }
    }*/

    public PayRecord queryObject(Integer id) {

       Map map = new HashMap();
       map.put("id",id);
        return (PayRecord)payRecordDAO.queryList(map).get(0);
    }

    public void update(PayRecord payRecord) {
        if (payRecord != null) {
            payRecordDAO.updateByPrimaryKeySelective(payRecord);
        }
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
