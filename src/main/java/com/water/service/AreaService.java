package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.config.HttpServletRequestUtil;
import com.water.dao.AreaDAO;
import com.water.domain.Area;
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
 * @Date: Created in 17:37 2018/7/3
 * @Modified By:
 */
@Service
public class AreaService {

    Logger logger = LoggerFactory.getLogger(AreaService.class);

    @Autowired
    private AreaDAO areaDAO;
    @Autowired
    private EmployeeService employeeService;

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 17:39 2018/7/3
     */
    @FactoryIds
    public R queryList(Map<String, Object> params) {
        logger.info("AreaService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = areaDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<Area> factories = null;
        if (total_count > 0) {
            factories = areaDAO.queryList(query);
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
    public void save(Area area) {
        if (area != null) {
            //组装创建人
            area.setCreateUser(HttpServletRequestUtil.getUserId());
            areaDAO.insertSelective(area);
        }
    }

    public Area queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id", id);
        return (Area) areaDAO.queryList(map).get(0);
    }

    public void update(Area area) {
        if (area != null) {
            areaDAO.updateByPrimaryKeySelective(area);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Employee表里的数据
            areaDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }


}
