package com.water.service;

import com.water.dao.ArchiveDAO;
import com.water.dao.CustomerDAO;
import com.water.domain.Archive;
import com.water.domain.Customer;
import com.water.domain.IdAndNameDTO;
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
import java.util.UUID;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 10:57 2018/7/10
 * @Modified By:
 */
@Service
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerDAO customerDAO;


    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 17:39 2018/7/3
     */
    public R queryList(Map<String, Object> params) {
        logger.info("CustomerService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = customerDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<Customer> archives = null;
        if (total_count > 0) {
            archives = customerDAO.queryList(query);
            logger.info("serviceList = {}", archives.toString());
        }
        PageUtil pageUtil = new PageUtil(archives, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public void save(Customer customer){
        if (customer != null) {
            //todo 获取用户名，填充recordUser           获取片区和营业厅列表
            customer.setCreateUser(1);
          customer.setUpdateUser(1);
          customer.setCode(UUID.randomUUID().toString());
            customerDAO.insertSelective(customer);
        }
    }

    public Customer queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id",id);
        return (Customer) customerDAO.queryList(map).get(0);

    }

    public void update(Customer customer) {
        if (customer != null) {
            customerDAO.updateByPrimaryKeySelective(customer);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {

            customerDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }

    public  List<IdAndNameDTO> selectArchiveMessage(){
        Map map = new HashMap();
        return  customerDAO.selectArchiveMessage(map);
    }




}
