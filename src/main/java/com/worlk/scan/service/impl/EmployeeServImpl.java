package com.worlk.scan.service.impl;

import com.worlk.common.pagination.interceptor.PaginationInterceptor;
import com.worlk.common.pagination.Page.PageUtil;
import com.worlk.scan.dao.EmployeeDAO;
import com.worlk.entity.Employee;
import com.worlk.mapper.EmployeeMapper;
import com.worlk.scan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XSF on 14-1-6.
 */
@Service("EmployeeService")
public class EmployeeServImpl implements EmployeeService
{
    @Resource(name = "employeeDAO")
    private EmployeeDAO employeeDAO;

    private EmployeeMapper employeeMapper;


    @Override
    public Employee selectByPrimaryKey(Integer employeeid) {
        return employeeMapper.selectByPrimaryKey(employeeid);
    }

    @Override
    public void updateByPrimaryKey(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    /*@Override
    public List<Employee> queryEmpByPage(Page page) {
        return employeeMapper.queryEmpByPage(page);
    }*/

    @Override
    public com.worlk.common.pagination.Page.Page queryEmployee(Employee employee, int pageNum, int pageSize) {
        List<Employee> employeeList = employeeDAO.queryPage("com.worlk.mapper.EmployeeMapper.queryEmpListPage",employee, pageNum, pageSize);
        int totalCount = PaginationInterceptor.getPaginationTotal();
        return PageUtil.getPage(totalCount, pageNum, employeeList, pageSize);
    }

    @Autowired
    public void setCompanyService(EmployeeMapper employeeMapper)
    {
        this.employeeMapper = employeeMapper;
    }

}
