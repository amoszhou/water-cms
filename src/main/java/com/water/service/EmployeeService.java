package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.config.Globals;
import com.water.config.HttpServletRequestUtil;
import com.water.constant.EmployeeType;
import com.water.constant.UserType;
import com.water.dao.EmployeeDAO;
import com.water.domain.Employee;
import com.water.domain.Factory;
import com.water.exception.BizException;
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
import java.util.*;

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

    //失败
    private final static int FAILFLAG = -1;
    //成功
    private final static int SUCCESSFLAG = 1;

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 16:53 2018/6/28
     */
    @FactoryIds
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
            if(employee.getUserType() == UserType.SUPER_MANAGER.getUserType()){
                if(HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERTYPE) == EmployeeType.SUPER_MANAGER)
                    employee.setFactoryId(null);
                else
                    throw new BizException("您不是超级管理员，没有此权限!");
            }
            //注册时默认密码和手机号码一致
            employee.setPassword(employee.getTelPhone());
            employeeDAO.insertSelective(employee);
        }
    }

    public Employee queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id",id);
        return (Employee)employeeDAO.queryList(map).get(0);
    }


    public Integer employeeLogin(Employee employee) {
        HttpServletRequest request = HttpServletRequestUtil.getRequst();
        Employee employFromTable = employeeDAO.queryObject(employee);
        if (employFromTable == null) {
            return FAILFLAG;
        }
        if (employFromTable.getPassword().equals(employee.getPassword())) {
            request.getSession(true).setAttribute("userType", employFromTable.getUserType());
            request.getSession(true).setAttribute("userName", employFromTable.getRealName());
            request.getSession(true).setAttribute("userKey", employFromTable.getId());
            //登录成功
            if (employFromTable.getUserType() == EmployeeType.NORMAL_MANAGER.getTypeId()) {
                request.getSession(true).setAttribute("userId", employFromTable.getTelPhone());
                request.getSession(true).setAttribute("factoryIds", Arrays.asList(employFromTable.getFactoryId()));
            } else {
                //超级管理员,不需要设置factoryId
                request.getSession(true).setAttribute("userId", employFromTable.getTelPhone());
            }

            return SUCCESSFLAG;
        }
        return FAILFLAG;
    }

    public  int modifyPassword(Map map){
       Employee oldEmployee = new Employee();
       oldEmployee.setTelPhone((String) map.get("telPhone"));
       oldEmployee = employeeDAO.queryObject(oldEmployee);
       if(!oldEmployee.getPassword().equals(map.get("oldPassword"))){
             return FAILFLAG;
       }
       oldEmployee.setPassword((String) map.get("newPassword"));
       return  employeeDAO.modifyPassword(oldEmployee);
    }

    public void update(Employee employee) {
        if (employee != null) {
           /* String[] result = employee.getHallName().split(":");
            employee.setHallId(Integer.parseInt(result[0]));
            employee.setHallName(result[1]);*/

        /*    Employee employee = new Employee();
            employee.setId(factory.getManagerId());
            //检查employee是否已经有了水厂，如果有，则不允许（因为一个管理员只允许管理一个水厂）
            if(employeeDAO.checkFactoryEmployeeIsExist(employee) > 0)
                throw  new BizException("该管理员已经有所属水厂了!");

            //修改employee表
            employee.setFactoryId(factory.getId());
            employeeDAO.updateByPrimaryKeySelective(employee);*/

           //如果修改了employee所属水厂，需要检查操作人是不是超级管理员
            if(  queryObject(employee.getId()).getFactoryId() != employee.getFactoryId()){

                if(HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERTYPE) != EmployeeType.SUPER_MANAGER.getTypeId())
                    throw new BizException("您不是超级管理员，没有此权限!");
            }


           //如果将用户设置成超级管理员，则需清除t_employee 表中的 factory_id 字段
            if(employee.getUserType() == UserType.SUPER_MANAGER.getUserType()){
                if(HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERTYPE) == EmployeeType.SUPER_MANAGER.getTypeId())
                employee.setFactoryId(null);
                else
                    throw new BizException("您不是超级管理员，没有此权限!");
            }

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
