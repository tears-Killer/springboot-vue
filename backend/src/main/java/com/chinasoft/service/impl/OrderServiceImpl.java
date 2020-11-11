package com.chinasoft.service.impl;

import com.chinasoft.mapper.OrderMapper;
import com.chinasoft.pojo.Order;
import com.chinasoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAllOrders(Integer pageSize, Integer pageIndex) {
        pageIndex = (pageIndex-1)*pageSize;
        return orderMapper.getAllOrders(pageSize,pageIndex);
    }

    @Override
    public Integer getOrderCount() {
        return orderMapper.selectOrderCounts();
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public int deleteOrder(Integer orderId) {
        return orderMapper.deleteOrder(orderId);
    }

    @Override
    public List<Order> selectByLike(String searchWord, Integer pageSize, Integer pageIndex) {
        pageIndex = (pageIndex-1)*pageSize;
        return orderMapper.selectByLike(searchWord,pageSize,pageIndex);
    }

    @Override
    public Integer likeOrderCount(String searchWord) {
        return orderMapper.likeOrderCount(searchWord);
    }
}
