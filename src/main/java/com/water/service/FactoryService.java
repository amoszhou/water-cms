package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.dao.EmployeeDAO;
import com.water.dao.FactoryDAO;
import com.water.domain.Employee;
import com.water.domain.Factory;
import com.water.domain.IdAndNameDTO;
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
import java.util.HashMap;
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
    @Autowired
    private EmployeeDAO employeeDAO;


    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 20:28 2018/6/26
     */
    @FactoryIds
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
    @Transactional
    public void save(Factory factory) {
        if (factory != null) {
            logger.info(factory.toString());
            int factoryId = factoryDAO.insertSelective(factory);
            //修改employee表
            Employee employee = new Employee();
            employee.setFactoryId(factoryId);
            employee.setId(factory.getManagerId());
            employee.setFactoryName(factory.getName());
            employeeDAO.updateByPrimaryKeySelective(employee);
         }
    }

    public Factory queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id",id);
        return (Factory)factoryDAO.queryList(map).get(0);
    }

    @Transactional
    public void update(Factory factory) {
        if (factory != null) {
            String[] result = factory.getManagerName().split(":");
            factory.setManagerId(Integer.parseInt(result[0]));
            factory.setManagerName(result[1]);
            logger.info(factory.toString());
            factoryDAO.updateByPrimaryKeySelective(factory);
            //修改employee表
            Employee employee = new Employee();
            employee.setFactoryId(factory.getId());
            employee.setId(Integer.parseInt(result[0]));
            employee.setFactoryName(factory.getName());
            employeeDAO.updateByPrimaryKeySelective(employee);
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

    //获得所有的雇员信息
    public List<IdAndNameDTO> getEmployee() {
        return factoryDAO.getEmployee();
    }

}
