package com.chinasoft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    private Integer shopId;
    private String shopName;
    private Double shopPrice;
    private String shopInfo;
    private Integer shopState;

    public Shop(String shopName, Double shopPrice, String shopInfo, Integer shopState) {
        this.shopName = shopName;
        this.shopPrice = shopPrice;
        this.shopInfo = shopInfo;
        this.shopState = shopState;
    }
}
