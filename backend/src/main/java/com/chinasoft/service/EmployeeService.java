package com.chinasoft.service;

import com.chinasoft.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee(Integer pageIndex,Integer pageSize);

    Integer selectEmployeeCount();

    int addEmployee(Employee employee);

    int deleteEmployee(Integer employeeId);

    int updateEmployee(Employee employee);

    List<Employee> selectByNameLike(String employeeName,Integer pageIndex,Integer pageSize);

    Integer likeOrderCount(String employeeName);
}
