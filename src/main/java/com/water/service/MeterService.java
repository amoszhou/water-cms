package com.water.service;

import com.water.dao.MeterDAO;
import com.water.domain.Hall;
import com.water.domain.IdAndNameDTO;
import com.water.domain.Meter;
import com.water.util.PageUtil;
import com.water.util.Query;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 11:50 2018/7/11
 * @Modified By:
 */
@Service
public class MeterService {

    Logger logger = LoggerFactory.getLogger(MeterService.class);

    @Autowired
    private MeterDAO meterDAO;

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 10:13 2018/7/3
     */
    public R queryList(Map<String, Object> params) {
        logger.info("MeterService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = meterDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<Meter> meters = null;
        if (total_count > 0) {
            meters = meterDAO.queryList(query);
            logger.info("hallList = {}", meters.toString());
        }
        PageUtil pageUtil = new PageUtil(meters, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public void save(Meter meter) {
        if (meter != null) {
            meterDAO.insertSelective(meter);
        }
    }

    public Meter queryObject(Integer id) {
        return meterDAO.selectByPrimaryKey(id);
    }

    public void update(Meter meter) {
        if (meter != null) {

            meterDAO.updateByPrimaryKeySelective(meter);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Employee表里的数据
            meterDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }

}
