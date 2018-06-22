package com.water.service;

import com.water.domain.App;
import com.water.util.PageUtil;
import com.water.util.Query;
import com.water.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : 林吉达
 * @Description :
 * @Date: Created in 8:43 2018/6/21
 * @Modified By:
 */
@Service
public class AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppService.class);

    /**
     * 分页查询应用列表
     *
     * @param params
     * @return
     */
    public R queryList(Map<String, Object> params) {
        Query query = new Query(params);
        //查询列表
        List<App> appList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            App app = new App();
            app.setAppId(""+i);
            app.setCreateTime(LocalDateTime.now().plusSeconds(i));
            app.setDeleteStatus(0);
            if(i == 7){
                app.setDeleteStatus(-1);
            }
            app.setId(i);
            app.setModifyTime(LocalDateTime.now().plusSeconds(i));
            app.setName("name:"+i);
            app.setUrl("url:"+i);
            appList.add(app);
        }
        //查询总数
        int total = 10;

        PageUtil pageUtil = new PageUtil(appList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

}
