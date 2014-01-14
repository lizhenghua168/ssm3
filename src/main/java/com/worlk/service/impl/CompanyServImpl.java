package com.worlk.service.impl;

import com.worlk.dao.CompanyMapper;
import com.worlk.dao.EmployeeMapper;
import com.worlk.entity.Company;
import com.worlk.entity.Employee;
import com.worlk.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: XSF
 * Date: 13-11-9
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CompanyServImpl implements CompanyService {

    private CompanyMapper companyMapper;
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

    public CompanyMapper getCompanyMapper() {
        return companyMapper;
    }
    @Autowired
    public void setCompanyMapper(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
}
