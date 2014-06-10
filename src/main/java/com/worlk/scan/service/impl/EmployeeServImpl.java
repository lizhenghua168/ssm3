package com.worlk.scan.service.impl;

import com.worlk.common.pagination.Page.Page;
import com.worlk.common.pagination.Page.PageUtil;
import com.worlk.common.pagination.domain.PageBounds;
import com.worlk.common.pagination.domain.PageList;
import com.worlk.common.pagination.interceptor.PaginationInterceptor2;
import com.worlk.entity.Employee;
import com.worlk.mapper.EmployeeMapper;
import com.worlk.scan.dao.EmployeeDAO;
import com.worlk.scan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by XSF on 14-1-6.
 */
@Service("EmployeeService")
public class EmployeeServImpl implements EmployeeService
{

    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public Employee selectByPrimaryKey(Integer employeeid) {
        return employeeMapper.selectByPrimaryKey(employeeid);
    }

    @Override
    public void updateByPrimaryKey(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public Page queryEmpByPage(Map<String, Employee> employeeMap, PageBounds pageBounds) {
        PageList<Employee> employeeList = employeeMapper.query(employeeMap, pageBounds);

        return PageUtil.getPage(employeeList.getPaginator().getTotalCount(), pageBounds.getPage(), employeeList, pageBounds.getLimit());
    }

    /*@Override
    public List<Employee> queryEmpByPage(Page page) {
        return employeeMapper.queryEmpByPage(page);
    }*/

    @Override
    public com.worlk.common.pagination.Page.Page queryEmployee(Employee employee, int pageNum, int pageSize) {
        List<Employee> employeeList = employeeDAO.queryPage("com.worlk.mapper.EmployeeMapper.queryEmpListPage",employee, pageNum, pageSize);
        int totalCount = PaginationInterceptor2.getPaginationTotal();
        return PageUtil.getPage(totalCount, pageNum, employeeList, pageSize);
    }

}
