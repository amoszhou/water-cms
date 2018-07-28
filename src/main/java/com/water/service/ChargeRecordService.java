package com.water.service;

import com.water.dao.ChargeRecordDAO;
import com.water.dao.CustomerAccountDAO;
import com.water.domain.ChargeRecord;
import com.water.domain.CustomerAccount;
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
import java.util.UUID;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 11:25 2018/7/17
 * @Modified By:
 */
@Service
public class ChargeRecordService {

    Logger logger = LoggerFactory.getLogger(ChargeRecordService.class);

    @Autowired
    private ChargeRecordDAO chargeRecordDAO;

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 10:13 2018/7/3
     */
    public R queryList(Map<String, Object> params) {
        logger.info("ChargeRecordService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = chargeRecordDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<ChargeRecord> chargeRecords = null;
        if (total_count > 0) {
            chargeRecords = chargeRecordDAO.queryList(query);
            logger.info("customerAccounts = {}", chargeRecords.toString());
        }
        PageUtil pageUtil = new PageUtil(chargeRecords, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public void save(ChargeRecord chargeRecord) {
        if (chargeRecord != null) {
            //获取user
            chargeRecord.setCustCode(UUID.randomUUID().toString());
            chargeRecord.setCreateUser(1);
            chargeRecordDAO.insertSelective(chargeRecord);
        }
    }

    public ChargeRecord queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id",id);
        return (ChargeRecord)chargeRecordDAO.queryList(map).get(0);
    }

    public int update(ChargeRecord chargeRecord) {
        if (chargeRecord != null) {
            //使用updateTime作为乐观锁
            return chargeRecordDAO.updateByPrimaryKeySelective(chargeRecord);
        }
        return 0;
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            chargeRecordDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }
    }

}
