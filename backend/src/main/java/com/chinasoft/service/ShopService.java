package com.chinasoft.service;

import com.chinasoft.pojo.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShops(Integer pageSize,Integer pageIndex);

    Integer selectShopCount();

    int deleteShop(Integer shopId);

    int addShop(Shop shop);

    Shop selectShopById(Integer shopId);

    int updateShop(Shop shop);

    List<Shop> searchShop(String shopName,Integer pageSize,Integer pageIndex);

    Integer likeShopCount(String shopName);
}
