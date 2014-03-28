package com.worlk.scan.service;

import com.worlk.entity.Employee;

/**
 * Created by XSF on 14-1-6.
 */
public interface EmployeeService {

    Employee selectByPrimaryKey(Integer employeeid);

    void updateByPrimaryKey(Employee employee);

    //List<Employee> queryEmpByPage(Page page);

    com.worlk.common.pagination.Page.Page queryEmployee(Employee employee, int pageNum, int pageSize);
}