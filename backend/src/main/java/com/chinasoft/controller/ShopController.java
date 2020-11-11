package com.chinasoft.controller;

import com.chinasoft.pojo.Shop;
import com.chinasoft.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/getAll")
    public List<Shop> getAllShops(@RequestParam("pageSize")Integer pageSize,@RequestParam("pageIndex")Integer pageIndex){
        List<Shop> shoplist = shopService.getAllShops(pageSize,pageIndex);
        return shoplist;
    }

    @PostMapping("/delete")
    public Map<String,Object> deleteShop(@RequestParam("shopId")Integer shopId){
        Map<String,Object> map = new HashMap<String,Object>();
        int result = shopService.deleteShop(shopId);
        if(result > 0){
            map.put("msg","删除成功");
            return map;
        }else {
            map.put("msg","删除失败");
            return map;
        }
    }

    @PostMapping("/add")
    public Map<String,Object> addShop(@RequestBody Shop shop) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = shopService.addShop(shop);
        if(result > 0){
            map.put("msg","添加成功");
            return map;
        }else {
            map.put("msg","添加失败");
            return map;
        }
    }

    @PostMapping("/selectById")
    public Shop selectShop(@RequestParam("shopId")Integer shopId){
        Shop shop = shopService.selectShopById(shopId);
        return shop;
    }

    @PostMapping("/update")
    public Map<String,Object> updateShop(@RequestBody Shop shop){
        Map<String, Object> map = new HashMap<String, Object>();
        int result = shopService.updateShop(shop);
        if(result > 0){
            map.put("msg","修改成功");
            return map;
        }else {
            map.put("msg","修改失败");
            return map;
        }
    }

    @PostMapping("/selectByLike")
    public List<Shop> searchShops(@RequestParam("shopName") String shopName,@RequestParam("pageSize")Integer pageSize,
                                  @RequestParam("pageIndex")Integer pageIndex){
        List<Shop> shopList = shopService.searchShop(shopName,pageSize,pageIndex);
        return shopList;
    }

    @GetMapping("/count")
    public Integer selectShopCounts(){
        return shopService.selectShopCount();
    }

    @PostMapping("/likecount")
    public Integer likeShopCount(@RequestParam("shopName")String shopName){
        return shopService.likeShopCount(shopName);
    }
}