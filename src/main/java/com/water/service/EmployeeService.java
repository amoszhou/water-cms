package com.water.service;

import com.water.config.HttpServletRequestUtil;
import com.water.constant.EmployeeType;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
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

    //登录失败
    private final static int LOGINFAIL = -1;
    //登录成功
    private final static int LOGINSUCCESS = 1;

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
            logger.info(employee.toString());
            employeeDAO.insertSelective(employee);
        }
    }

    public Employee queryObject(Integer id) {
        return employeeDAO.selectByPrimaryKey(id);
    }


    public Integer employeeLogin(Employee employee) {
        HttpServletRequest request = HttpServletRequestUtil.getRequst();
        Employee employFromTable = employeeDAO.queryObject(employee);
        if (employFromTable == null) {
            return LOGINFAIL;
        }
        if (employFromTable.getPassword().equals(employee.getPassword())) {
            request.getSession(true).setAttribute("userType", employFromTable.getUserType());
            //登录成功
            if (employFromTable.getUserType() == EmployeeType.NORMAL_MANAGER.getTypeId()) {
                request.getSession(true).setAttribute("userId", employFromTable.getTelPhone());
                request.getSession(true).setAttribute("factoryIds", Arrays.asList(employFromTable.getFactoryId()));
            } else {
                //超级管理员,不需要设置factoryId
                request.getSession(true).setAttribute("userId", employFromTable.getTelPhone());
            }

            return LOGINSUCCESS;
        }
        return LOGINFAIL;
    }


    public void update(Employee employee) {
        if (employee != null) {
           /* String[] result = employee.getHallName().split(":");
            employee.setHallId(Integer.parseInt(result[0]));
            employee.setHallName(result[1]);*/

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
