package com.worlk.service.impl;

import com.worlk.entity.Company;
import com.worlk.entity.Employee;
import com.worlk.scan.service.CompanyService;
import com.worlk.scan.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: XSF
 * Date: 13-11-9
 * Time: 下午3:01
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback =true)
public class CompanyServImplTest {

    //private static final Logger logger = Logger.getLogger(CompanyServImplTest.class);

    @Autowired
    private CompanyService companyService;
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void test1()
    {
        Company company = companyService.selectByPrimaryKey(1);

        company.setCompanyname(null);

        company = companyService.selectByPrimaryKey(1);

        System.out.println(company.getCompanyname());
    }

    @Test
    public void test2()
    {
        Company company = companyService.selectByPrimaryKey(1);
        Employee employee = employeeService.selectByPrimaryKey(1);
        company.setCity("湘乡");

        employee.setEmployeename("张三");
        employee.setDepartmentid(1);

        companyService.updateByPrimaryKey(company, employee);

        company = companyService.selectByPrimaryKey(1);
        assertEquals("湘乡", company.getCity());
    }


}
