package com.worlk.scan.dao;

import com.worlk.entity.Employee;

import java.util.List;

/**
 * Created by XSF on 14-3-25.
 */
public interface EmployeeDAO {
    List<Employee> queryPage(String statement, Object parameter, int pageNum, int pageSize);
}
