package com.water.repository;

import com.water.WaterCmsApplicationTests;
import com.water.domain.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/3/26.
 */
public class AreaTest extends WaterCmsApplicationTests {

    @Autowired
    private AreaRepository areaRepository;


    @Test
    public void testCreate() {
        Area area = new Area();
        area.setName("B区");
        area.setCode("B");
        area.setManager("amos");
        area.setCreateUser(1);
        int result = areaRepository.createArea(area);
        System.out.println("==========result :" + result);
    }
}
