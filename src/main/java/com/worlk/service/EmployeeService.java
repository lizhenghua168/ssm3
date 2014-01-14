package com.worlk.service;

import com.worlk.entity.Employee;

/**
 * Created by XSF on 14-1-6.
 */
public interface EmployeeService {

    Employee selectByPrimaryKey(Integer employeeid);

    void updateByPrimaryKey(Employee employee);

}