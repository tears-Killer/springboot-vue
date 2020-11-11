package com.chinasoft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Integer customerId;
    private String customerName;
    private Integer customerSex;
    private String customerAddress;
    private Integer customerPhone;
}
