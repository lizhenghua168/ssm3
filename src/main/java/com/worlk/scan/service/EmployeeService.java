package com.worlk.scan.service;

import com.worlk.common.pagination.domain.PageBounds;
import com.worlk.entity.Employee;

import java.util.Map;

/**
 * Created by XSF on 14-1-6.
 */
public interface EmployeeService {

    Employee selectByPrimaryKey(Integer employeeid);

    void updateByPrimaryKey(Employee employee);

    com.worlk.common.pagination.Page.Page queryEmpByPage(Map<String, Employee> employeeMap, PageBounds pageBounds);

    com.worlk.common.pagination.Page.Page queryEmployee(Employee employee, int pageNum, int pageSize);
}