package com.chinasoft.service;

import com.chinasoft.pojo.BaseData;

import java.util.List;

public interface BaseDataService {

    BaseData getBaseData();

    List<Integer> getFoodCount();

    List<Integer> selectDayOrder();

    List<Double> selectDayProfit();
}
