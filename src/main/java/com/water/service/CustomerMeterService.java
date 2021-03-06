package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.dao.CustomerDAO;
import com.water.dao.CustomerMeterDAO;
import com.water.dao.PriceTypeDAO;
import com.water.domain.Customer;
import com.water.domain.CustomerMeter;
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
 * @Description :  顾客水表关系表
 * @Date: Created in 17:10 2018/7/23
 * @Modified By:
 */
@Service
public class CustomerMeterService {

    Logger logger = LoggerFactory.getLogger(CustomerMeterService.class);
    @Autowired
    private CommonService commonService;
    @Autowired
    private CustomerMeterDAO customerMeterDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private PriceTypeDAO priceTypeDAO;


    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 17:39 2018/7/3
     */
    @FactoryIds
    public R queryList(Map<String, Object> params) {
        logger.info("CustomerMeterService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = customerMeterDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<CustomerMeter> customerMeters = null;
        if (total_count > 0) {
            customerMeters = customerMeterDAO.queryList(query);
            logger.info("customerMeters = {}", customerMeters.toString());
        }
        PageUtil pageUtil = new PageUtil(customerMeters, total_count, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * @Author : 林吉达
     * @Description :保存
     * @Date : 20:28 2018/6/26
     */
    public void save(CustomerMeter customerMeter) {
        if (customerMeter != null) {

            //根据custCode 获取custId
            Customer customer =  commonService.getCustomerByCode(customerMeter.getCustCode());
            Integer custId = customer.getId();
            if (custId == null)
                throw new BizException("输入的顾客编码有误！无法找到该顾客！");
            customerMeter.setCustId(custId);
            customerMeter.setFactoryId(customer.getFactoryId());
            if (!enableAdd(custId))
                throw new BizException("该顾客已绑定了水表！一个顾客无法绑定多个水表!");
         /*   customerMeter.setCode(UUID.randomUUID().toString());*/
            customerMeterDAO.insertSelective(customerMeter);
        }
    }

    public Boolean enableAdd(Integer custId) {
        return customerMeterDAO.getIdByCustId(custId) != null ? false : true;
    }


    public CustomerMeter queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id", id);
        return (CustomerMeter) customerMeterDAO.queryList(map).get(0);

    }

    public void update(CustomerMeter customerMeter) {
        if (customerMeter != null) {
            //根据custCode 获取custId
          Integer custId = commonService.getCustIdByCode(customerMeter.getCustCode());
            customerMeter.setCustId(custId);
            Integer id = customerMeterDAO.getIdByCustId(custId);
            if (id != null && id != customerMeter.getId())
                throw new BizException("该顾客已绑定了水表！一个用户无法绑定多个水表!");
            customerMeterDAO.updateByPrimaryKeySelective(customerMeter);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            customerMeterDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }


}
