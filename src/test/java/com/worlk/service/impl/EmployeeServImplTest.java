package com.worlk.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worlk.common.pagination.Page.Page;
import com.worlk.common.pagination.domain.PageBounds;
import com.worlk.common.pagination.jackson2.PageListJsonMapper;
import com.worlk.entity.Employee;
import com.worlk.scan.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XSF on 14-3-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback =true)
public class EmployeeServImplTest {

    @Autowired
    @Qualifier("EmployeeService")
    private EmployeeService employeeService;

    /*@Test
    public void test()
    {
        Employee employee = new Employee();
        employee.setEmployeename("’≈");

        PageParameter pageParameter = new PageParameter();
        //pageParameter.setNextPage(2);
        //pageParameter.setTotalPage(3);

        Page page = new Page();
        page.setEmployee(employee);
        page.setPage(pageParameter);

        employeeService.queryEmpByPage(page);

    }*/

    @Test
    public void test1()
    {
        /*Employee employee = new Employee();
        employee.setEmployeename("’≈");
        employeeService.queryEmployee(employee, 1, 10);*/
    }

    @Test
    public void test2() throws Exception
    {
        PageBounds pageBounds = new PageBounds(1, 4);

        Map<String, Employee> employeeMap = new HashMap<String, Employee>();
        Employee employee = new Employee();
        employee.setEmployeename("¡ı");
        employeeMap.put("employee", employee);

        Page page = employeeService.queryEmpByPage(employeeMap, pageBounds);

        ObjectMapper objectMapper = new PageListJsonMapper();
        System.out.println(objectMapper.writeValueAsString(page.getItems()));
    }
}
