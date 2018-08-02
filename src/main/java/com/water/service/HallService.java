package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.config.HttpServletRequestUtil;
import com.water.constant.EmployeeType;
import com.water.dao.HallDAO;
import com.water.domain.IdAndNameDTO;
import com.water.domain.Hall;
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
 * @Date: Created in 10:12 2018/7/3
 * @Modified By:
 */
@Service
public class HallService {

    Logger logger = LoggerFactory.getLogger(HallService.class);

    @Autowired
    private HallDAO hallDAO;

    private static final String FACTORYIDS = "factoryIds";

    private static final String USERTYPE = "userType";

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 10:13 2018/7/3
     */
    @FactoryIds
    public R queryList(Map<String, Object> params) {
        logger.info("HallService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = hallDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<Hall> halls = null;
        if (total_count > 0) {
            halls = hallDAO.queryList(query);
            logger.info("hallList = {}", halls.toString());
        }
        PageUtil pageUtil = new PageUtil(halls, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public void save(Hall hall) {
        if (hall != null) {
            hallDAO.insertSelective(hall);
        }
    }

    public Hall queryObject(Integer id) {
             Map map = new HashMap();
             map.put("id",id);
        return (Hall) hallDAO.queryList(map).get(0);
    }

    public void update(Hall hall) {
        if (hall != null) {
            hallDAO.updateByPrimaryKeySelective(hall);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Employee表里的数据
            hallDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }


    public List<IdAndNameDTO> selectFactoryMessage() {
        Map map = new HashMap();
        int userType = (Integer) HttpServletRequestUtil.getRequst().getSession().getAttribute(USERTYPE);
        if (userType == EmployeeType.NORMAL_MANAGER.getTypeId())
            map.put(FACTORYIDS,HttpServletRequestUtil.getRequst().getSession().getAttribute(FACTORYIDS));
            return hallDAO.selectFactoryMessage(map);
    }

    public List<IdAndNameDTO> getMeterMessage() {
        Map map = new HashMap();
        return hallDAO.getMeterMessage(map);
    }

    public List<IdAndNameDTO> getPriceTypeMessage() {
        Map map = new HashMap();
        return hallDAO.getPriceTypeMessage(map);
    }


}
