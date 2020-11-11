package com.chinasoft.service.impl;

import com.chinasoft.mapper.EmployeeMapper;
import com.chinasoft.pojo.Employee;
import com.chinasoft.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployee(Integer pageIndex, Integer pageSize) {
        pageIndex = (pageIndex-1)*pageSize;
        return employeeMapper.getAllEmployee(pageIndex,pageSize);
    }

    @Override
    public Integer selectEmployeeCount() {
        return employeeMapper.selectEmployeeCount();
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public int deleteEmployee(Integer employeeId) {
        return employeeMapper.deleteEmployee(employeeId);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public List<Employee> selectByNameLike(String employeeName, Integer pageIndex, Integer pageSize) {
        pageIndex = (pageIndex-1)*pageSize;
        return employeeMapper.selectByNameLike(employeeName,pageIndex,pageSize);
    }

    @Override
    public Integer likeOrderCount(String employeeName) {
        return employeeMapper.likeOrderCount(employeeName);
    }
}
