package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.dao.MeterDAO;
import com.water.dao.PriceTypeDAO;
import com.water.domain.Meter;
import com.water.domain.PriceType;
import com.water.exception.BizException;
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
 * @Date: Created in 17:43 2018/7/12
 * @Modified By:
 */
@Service
public class PriceTypeService {


    Logger logger = LoggerFactory.getLogger(PriceTypeService.class);

    @Autowired
    private PriceTypeDAO priceTypeDAO;

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 10:13 2018/7/3
     */
    @FactoryIds
    public R queryList(Map<String, Object> params) {
        logger.info("PriceTypeService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = priceTypeDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<PriceType> priceTypes = null;
        if (total_count > 0) {
            priceTypes = priceTypeDAO.queryList(query);
            logger.info("hallList = {}", priceTypes.toString());
        }
        PageUtil pageUtil = new PageUtil(priceTypes, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public void save(PriceType priceType) {
        if (priceType != null) {
            //priceType表  由factory 和 type 组成联合唯一索引。 所以在这里检查是否已经存在
            if (priceTypeDAO.checkTypeIsExist(priceType) > 0) {
                throw new BizException("该水厂已经有了该类型的水价！");
            }

            priceTypeDAO.insertSelective(priceType);
        }
    }

    public PriceType queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id",id);
        return (PriceType)priceTypeDAO.queryList(map).get(0);
    }

    public void update(PriceType priceType) {
        if (priceType != null) {

            priceTypeDAO.updateByPrimaryKeySelective(priceType);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Employee表里的数据
            priceTypeDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }

}
