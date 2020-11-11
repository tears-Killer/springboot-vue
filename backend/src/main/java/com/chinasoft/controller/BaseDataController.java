package com.chinasoft.controller;

import com.chinasoft.pojo.BaseData;
import com.chinasoft.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/basedata")
public class BaseDataController {

    @Autowired
    private BaseDataService baseDataService;

    @GetMapping("/getdata")
    public BaseData getAllData(){
        return baseDataService.getBaseData();
    }

    @GetMapping("/foodorder")
    public List<Integer> getDayOrderCount(){
        return  baseDataService.getFoodCount();
    }

    @GetMapping("/linedata")
    public Map<String,Object> getLineData(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Integer> orderList = baseDataService.selectDayOrder();
        List<Double> profitList = baseDataService.selectDayProfit();
        map.put("orderList",orderList);
        map.put("profitList",profitList);
        return map;
    }
}
