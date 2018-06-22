package com.water.dao;

import com.water.domain.Employee;

public interface EmployeeDAO {
    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);
}