package com.chinasoft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer orderId;
    private Integer orderNo;
    private Date orderTime;
    private String shopName;
    private String customerName;
    private Integer customerPhone;
    private String customerAddress;
    private Integer orderState;

}
