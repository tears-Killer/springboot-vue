package com.chinasoft.service.impl;

import com.chinasoft.mapper.BaseDataMapper;
import com.chinasoft.pojo.BaseData;
import com.chinasoft.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDataServiceImpl implements BaseDataService {

    @Autowired
    private BaseDataMapper baseDataMapper;

    @Override
    public BaseData getBaseData() {
        return baseDataMapper.selectBaseData();
    }

    @Override
    public List<Integer> getFoodCount() {
        return baseDataMapper.selectFoodCount();
    }

    @Override
    public List<Integer> selectDayOrder() {
        return baseDataMapper.selectDayOrder();
    }

    @Override
    public List<Double> selectDayProfit() {
        return baseDataMapper.selectDayProfit();
    }
}
