package com.chinasoft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer employeeId;
    private String employeeName;
    private Integer employeeSex;
    private Integer employeeAge;
    private Integer employeePhone;
    private Integer employeeRole;
    private Integer employeeState;
}
