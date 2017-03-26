package com.water;

import com.water.domain.Area;
import com.water.repository.AreaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaterCmsApplicationTests {

    @Autowired
    private AreaRepository areaRepository;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testFindAllArea() {
        List<Area> areas = areaRepository.allArea();
        Assert.assertEquals(1, areas.size());
        Assert.assertEquals(1,areas.get(0).getCreateUser().intValue());
    }
}
