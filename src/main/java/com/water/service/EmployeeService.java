package com.water.service;

import com.water.dao.EmployeeDAO;
import com.water.domain.Employee;
import com.water.domain.Factory;
import com.water.util.PageUtil;
import com.water.util.Query;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 16:49 2018/6/28
 * @Modified By:
 */
@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDAO employeeDAO;

/**
  * @Author : 林吉达
  * @Description : 查询列表
  * @Date : 16:53 2018/6/28
  */
    public R queryList(Map<String, Object> params) {
        logger.info("EmployeeService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = employeeDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<Employee> factories = null;
        if (total_count > 0) {
            factories = employeeDAO.queryList(query);
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
    public void save(Employee employee) {
        if (employee != null) {
            employeeDAO.insertSelective(employee);
        }
    }

    public Employee queryObject(Integer id) {
        return employeeDAO.selectByPrimaryKey(id);
    }

    public void update(Employee employee) {
        if (employee != null) {

            employeeDAO.updateByPrimaryKeySelective(employee);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Employee表里的数据
            employeeDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }



}
