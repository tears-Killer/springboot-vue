package com.chinasoft.service.impl;

import com.chinasoft.mapper.ShopMapper;
import com.chinasoft.pojo.Shop;
import com.chinasoft.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;


    @Override
    public List<Shop> getAllShops(Integer pageSize, Integer pageIndex) {
        pageIndex = (pageIndex-1)*pageSize;
        return shopMapper.getAllShops(pageSize,pageIndex);
    }

    @Override
    public int deleteShop(Integer shopId) {
        return shopMapper.deleteShop(shopId);
    }

    @Override
    public int addShop(Shop shop) {
        return shopMapper.addShop(shop);
    }

    @Override
    public Shop selectShopById(Integer shopId) {
        return shopMapper.selectById(shopId);
    }

    @Override
    public int updateShop(Shop shop) {
        return shopMapper.updateShop(shop);
    }

    @Override
    public List<Shop> searchShop(String shopName, Integer pageSize, Integer pageIndex) {
        pageIndex = (pageIndex-1)*pageSize;
        return shopMapper.selectShopByNameLike(shopName,pageSize,pageIndex);
    }


    @Override
    public Integer selectShopCount() {
        return shopMapper.selectShopCount();
    }

    @Override
    public Integer likeShopCount(String shopName) {
        return shopMapper.likeShopCount(shopName);
    }
}
