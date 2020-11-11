package com.chinasoft.service;

import com.chinasoft.pojo.Order;
import com.chinasoft.pojo.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders(Integer pageSize,Integer pageIndex);

    Integer getOrderCount();

    int updateOrder(Order order);

    int deleteOrder(Integer orderId);

    List<Order> selectByLike(String searchWord,Integer pageSize,Integer pageIndex);

    Integer likeOrderCount(String searchWord);
}
