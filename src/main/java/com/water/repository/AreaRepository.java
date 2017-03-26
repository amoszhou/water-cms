package com.water.repository;

import com.water.domain.Area;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
@Mapper
public interface AreaRepository {

    @Select("select * from area")
    List<Area> allArea();

    @Insert("insert into area(name,code,manager,create_user,create_time) values (#{name},#{code},#{manager},#{createUser},Now())")
    int createArea(Area area);
}
