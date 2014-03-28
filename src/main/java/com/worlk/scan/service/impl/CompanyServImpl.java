package com.worlk.scan.service.impl;

import com.worlk.entity.Company;
import com.worlk.entity.Employee;
import com.worlk.mapper.CompanyMapper;
import com.worlk.mapper.EmployeeMapper;
import com.worlk.scan.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: XSF
 * Date: 13-11-9
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CompanyServImpl implements CompanyService {

    @Resource(name = "companyMapper")
    private CompanyMapper companyMapper;
    @Resource(name = "employeeMapper")
    private EmployeeMapper employeeMapper;

    @Override
    public Company selectByPrimaryKey(Integer companyid)
    {
        return companyMapper.selectByPrimaryKey(companyid);
    }

    @Override
    public void updateByPrimaryKey(Company company, Employee employee) {
        companyMapper.updateByPrimaryKey(company);
        employeeMapper.updateByPrimaryKey(employee);
    }

}
