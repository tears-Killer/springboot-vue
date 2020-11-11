package com.chinasoft.mapper;

import com.chinasoft.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {

    List<Employee> getAllEmployee(@Param("pageIndex")Integer pageIndex,@Param("pageSize")Integer pageSize);

    Integer selectEmployeeCount();

    int addEmployee(Employee employee);

    int deleteEmployee(@Param("employeeId")Integer employeeId);

    int updateEmployee(Employee employee);

    List<Employee> selectByNameLike(@Param("employeeName")String employeeName,@Param("pageIndex")Integer pageIndex,@Param("pageSize")Integer pageSize);

    Integer likeOrderCount(@Param("employeeName")String employeeName);
}
