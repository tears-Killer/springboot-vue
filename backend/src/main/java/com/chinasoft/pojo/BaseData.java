package com.chinasoft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseData {

    private Integer dayOrderCount;
    private Double dayOrderProfit;
    private Integer weekOrderCount;
    private Double weekOrderProfit;
}
