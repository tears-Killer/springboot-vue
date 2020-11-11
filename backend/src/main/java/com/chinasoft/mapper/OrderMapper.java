package com.chinasoft.mapper;

import com.chinasoft.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    List<Order> getAllOrders(@Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);

    Integer selectOrderCounts();

    int updateOrder(Order order);

    int deleteOrder(@Param("orderId") Integer orderId);

    List<Order> selectByLike(@Param("searchWord")String searchWord,@Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);

    Integer likeOrderCount(@Param("searchWord")String searchWord);
}
