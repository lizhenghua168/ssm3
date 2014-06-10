package com.worlk.scan.dao.impl;

import com.worlk.entity.Employee;
import com.worlk.scan.dao.EmployeeDAO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by XSF on 14-3-25.
 */
@Repository("employeeDAO")
public class EmployeeDAOImpl extends SqlSessionDaoSupport implements EmployeeDAO {

    @Override
    public List<Employee> queryPage(String statement, Object parameter, int offset, int limit) {
        /*RowBounds rowBounds = new RowBounds(offset, limit);
        List<Employee> employeeList = getSqlSession().selectList(statement, parameter, rowBounds);
        return employeeList;*/
        return null;
    }
}
