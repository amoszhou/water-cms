package com.water.dao;

import com.water.domain.Employee;

public interface EmployeeDAO {

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);
}