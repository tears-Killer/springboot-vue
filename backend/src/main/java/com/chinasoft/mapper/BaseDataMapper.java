package com.chinasoft.mapper;

import com.chinasoft.pojo.BaseData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BaseDataMapper {

    BaseData selectBaseData();

    List<Integer> selectFoodCount();

    List<Integer> selectDayOrder();

    List<Double> selectDayProfit();
}
