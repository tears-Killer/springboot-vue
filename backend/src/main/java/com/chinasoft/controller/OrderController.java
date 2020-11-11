package com.chinasoft.controller;

import com.chinasoft.pojo.Order;
import com.chinasoft.pojo.Shop;
import com.chinasoft.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/getAll")
    public List<Order> getAll(@RequestParam("pageSize")Integer pageSize, @RequestParam("pageIndex")Integer pageIndex){
        List<Order> orderList = orderService.getAllOrders(pageSize,pageIndex);
        return orderList;
    }

    @GetMapping("/count")
    public Integer orderCount(){
        return orderService.getOrderCount();
    }

    @PostMapping("/update")
    public Map<String,Object> updateOrder(@RequestBody Order order) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = orderService.updateOrder(order);
        if (result > 0) {
            map.put("msg", "修改成功");
            return map;
        } else {
            map.put("msg", "修改失败");
            return map;
        }
    }

    @PostMapping("/delete")
    public Map<String,Object> deleteOrder(@RequestParam("orderId")Integer orderId){
        Map<String,Object> map = new HashMap<String,Object>();
        int result = orderService.deleteOrder(orderId);
        if(result > 0){
            map.put("msg","删除成功");
            return map;
        }else {
            map.put("msg","删除失败");
            return map;
        }
    }

    @PostMapping("/selectByLike")
    public List<Order> searchOrderByLike(@RequestParam("searchWord")String searchWord,@RequestParam("pageSize")Integer pageSize,
                                         @RequestParam("pageIndex")Integer pageIndex){
        List<Order> orderList = orderService.selectByLike(searchWord, pageSize, pageIndex);
        return orderList;
    }

    @PostMapping("/likecount")
    public Integer likeOrderCount(@RequestParam("searchWord")String searchWord){
        return orderService.likeOrderCount(searchWord);
    }
}
