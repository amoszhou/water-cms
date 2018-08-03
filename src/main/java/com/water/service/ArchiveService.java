package com.water.service;

import com.water.annotation.FactoryIds;
import com.water.config.Globals;
import com.water.config.HttpServletRequestUtil;
import com.water.constant.EmployeeType;
import com.water.dao.ArchiveDAO;
import com.water.domain.Archive;
import com.water.domain.Area;
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

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 14:11 2018/7/4
 * @Modified By:
 */
@Service
public class ArchiveService {

    Logger logger = LoggerFactory.getLogger(ArchiveService.class);

    @Autowired
    private ArchiveDAO archiveDAO;

    /**
     * @Author : 林吉达
     * @Description : 查询列表
     * @Date : 17:39 2018/7/3
     */
    @FactoryIds
    public R queryList(Map<String, Object> params) {
        logger.info("ArchiveService/queryList begin | params = {}", params.toString());

        Query query = new Query(params);
        int total_count = archiveDAO.queryTotal(query);
        logger.info("total:{}", total_count);

        List<Archive> archives = null;
        if (total_count > 0) {
            archives = archiveDAO.queryList(query);
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
    public void save(Archive archive){
        if (archive != null) {
            //todo 获取用户名，填充recordUser           获取片区和营业厅列表
            archive.setRecordUser(1);
            archiveDAO.insertSelective(archive);
        }
    }

    public Archive queryObject(Integer id) {
        Map map = new HashMap();
        map.put("id",id);
        return (Archive)archiveDAO.queryList(map).get(0);
    }

    public void update(Archive archive) {
        if (archive != null) {
            archiveDAO.updateByPrimaryKeySelective(archive);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (id != null) {
            //删除Employee表里的数据
            archiveDAO.updateDeleteState(id);
            //todo 记得级联删除其他表的数据
        }

    }

    public  List<IdAndNameDTO> selectAreaMessage(){
        Map map = new HashMap();
        int userType = (Integer) HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERTYPE);
        if (userType == EmployeeType.NORMAL_MANAGER.getTypeId())
            map.put(Globals.FACTORYIDS,HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.FACTORYIDS));
        return  archiveDAO.selectAreaMessage(map);
    }

    public  List<IdAndNameDTO> selectHallMessage(){
        Map map = new HashMap();
        int userType = (Integer) HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERTYPE);
        if (userType == EmployeeType.NORMAL_MANAGER.getTypeId())
            map.put(Globals.FACTORYIDS,HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.FACTORYIDS));
        return  archiveDAO.selectHallMessage(map);
    }
    public  List<IdAndNameDTO> getArchiveMessage(){
        Map map = new HashMap();
        int userType = (Integer) HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.USERTYPE);
        if (userType == EmployeeType.NORMAL_MANAGER.getTypeId())
            map.put(Globals.FACTORYIDS,HttpServletRequestUtil.getRequst().getSession().getAttribute(Globals.FACTORYIDS));
        return  archiveDAO.getArchiveMessage(map);
    }



}
