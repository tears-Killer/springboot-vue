package com.chinasoft.mapper;

import com.chinasoft.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopMapper {

    List<Shop> getAllShops(@Param("pageSize")Integer pageSize,@Param("pageIndex")Integer pageIndex);

    int deleteShop(@Param("shopId")Integer shopId);

    int addShop(Shop shop);

    Shop selectById(@Param("shopId")Integer shopId);

    int updateShop(Shop shop);

    List<Shop> selectShopByNameLike(@Param("shopName")String shopName,@Param("pageSize")Integer pageSize,
                                    @Param("pageIndex")Integer pageIndex);

    Integer selectShopCount();

    Integer likeShopCount(@Param("shopName") String shopName);
}
