package com.chinasoft.mapper;

import com.chinasoft.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User selectByAccountAndPassword(@Param("account")String account,@Param("password")String password);

    int updateUser(User user);
}
