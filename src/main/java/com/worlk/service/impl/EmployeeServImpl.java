package com.worlk.service.impl;

import com.worlk.dao.EmployeeMapper;
import com.worlk.entity.Employee;
import com.worlk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XSF on 14-1-6.
 */
@Service
public class EmployeeServImpl implements EmployeeService {

    private EmployeeMapper employeeMapper;

    @Override
    public Employee selectByPrimaryKey(Integer employeeid) {
        return employeeMapper.selectByPrimaryKey(employeeid);
    }

    @Override
    public void updateByPrimaryKey(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Autowired
    public void setCompanyService(EmployeeMapper employeeMapper)
    {
        this.employeeMapper = employeeMapper;
    }

}
