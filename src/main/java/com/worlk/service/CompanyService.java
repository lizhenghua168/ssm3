package com.worlk.service;

import com.worlk.entity.Company;
import com.worlk.entity.Employee;

/**
 * Created with IntelliJ IDEA.
 * User: XSF
 * Date: 13-11-9
 * Time: 下午2:50
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyService {

    Company selectByPrimaryKey(Integer companyid);

    void updateByPrimaryKey(Company company, Employee employee);
}
