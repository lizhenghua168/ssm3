package com.worlk.mapper;

import com.worlk.common.pagination.domain.PageBounds;
import com.worlk.common.pagination.domain.PageList;
import com.worlk.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository(value = "employeeMapper")
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer employeeid);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer employeeid);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    PageList<Employee> query(Map<String, Employee> employeeMap, PageBounds pageBounds);

    //List<Employee> queryEmployeeByPage(Employee employee, );
}