package com.chinasoft.controller;

import com.chinasoft.pojo.Employee;
import com.chinasoft.pojo.Shop;
import com.chinasoft.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/getAll")
    public List<Employee> getAllEmployee(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        List<Employee> employeeList = employeeService.getAllEmployee(pageIndex, pageSize);
        return employeeList;
    }

    @GetMapping("/count")
    public Integer employeeCount() {
        return employeeService.selectEmployeeCount();
    }

    @PostMapping("/selectByLike")
    public List<Employee> selectByLike(@RequestParam("employeeName") String employeeName, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        List<Employee> employeeList = employeeService.selectByNameLike(employeeName, pageIndex, pageSize);
        return employeeList;
    }

    @PostMapping("/likecount")
    public Integer likeEmployeeCount(@RequestParam("employeeName") String employeeName) {
        return employeeService.likeOrderCount(employeeName);
    }

    @PostMapping("/add")
    public Map<String, Object> addEmployee(@RequestBody Employee employee) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = employeeService.addEmployee(employee);
        if (result > 0) {
            map.put("msg", "添加成功");
            return map;
        } else {
            map.put("msg", "添加失败");
            return map;
        }
    }

    @PostMapping("/delete")
    public Map<String, Object> deleteEmploee(@RequestParam("employeeId") Integer employeeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = employeeService.deleteEmployee(employeeId);
        if (result > 0) {
            map.put("msg", "删除成功");
            return map;
        } else {
            map.put("msg", "删除失败");
            return map;
        }
    }

    @PostMapping("/update")
    public Map<String,Object> updateEmployee(@RequestBody Employee employee){
        Map<String, Object> map = new HashMap<String, Object>();
        int result = employeeService.updateEmployee(employee);
        if(result > 0){
            map.put("msg","修改成功");
            return map;
        }else {
            map.put("msg","修改失败");
            return map;
        }
    }
}
