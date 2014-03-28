package com.worlk.mapper;

import com.worlk.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository(value = "employeeMapper")
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer employeeid);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer employeeid);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    //List<Employee> queryEmpByPage(Page page);

    //List<Employee> queryEmployeeByPage(Employee employee, );
}