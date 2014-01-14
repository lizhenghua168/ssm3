package com.worlk.service.impl;

import com.alibaba.fastjson.JSON;
import com.worlk.entity.Company;
import com.worlk.entity.Employee;
import com.worlk.service.CompanyService;
import com.worlk.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    private CompanyService companyService;
    private EmployeeService employeeService;

    @Test
    public void test1()
    {
        Company company = companyService.selectByPrimaryKey(1);
        System.out.println(JSON.toJSON(company));
    }

    @Test
    public void test2()
    {
        Company company = companyService.selectByPrimaryKey(1);
        Employee employee = employeeService.selectByPrimaryKey(1);
        company.setCity("湘乡");

        employee.setEmployeename("张三1");
        employee.setDepartmentid(1);

        companyService.updateByPrimaryKey(company, employee);

        company = companyService.selectByPrimaryKey(1);
        assertEquals("株洲", company.getCity());
    }


}
