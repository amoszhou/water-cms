package com.water.service;

import com.water.dao.FactoryDAO;
import com.water.domain.Factory;
import com.water.util.PageUtil;
import com.water.util.Query;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 11:47 2018/6/26
 * @Modified By:
 */
@Service
public class FactoryService {

    Logger logger = LoggerFactory.getLogger(FactoryService.class);

    @Autowired
    private FactoryDAO factoryDAO;


    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 20:28 2018/6/26
     */
    public R queryList(Map<String, Object> params) {
        logger.info("FactoryService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = factoryDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<Factory> factories = null;
        if (total_count > 0) {
            factories = factoryDAO.queryList(query);
            logger.info("serviceList = {}", factories.toString());
        }
        PageUtil pageUtil = new PageUtil(factories, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public void save(Factory factory) {
        if (factory != null) {
            factoryDAO.insertSelective(factory);
        }
    }

    public Factory queryObject(Integer id) {
        return factoryDAO.selectByPrimaryKey(id);
    }

    public void update(Factory factory) {
        if (factory != null) {

            factoryDAO.updateByPrimaryKeySelective(factory);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Factory表里的数据
            factoryDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }

}
