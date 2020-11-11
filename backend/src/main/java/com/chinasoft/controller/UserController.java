package com.chinasoft.controller;

import com.chinasoft.pojo.User;
import com.chinasoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String,Object> checkLogin(@RequestParam("account") String account,@RequestParam("password") String password){
        Map<String,Object> map =new HashMap<String,Object>();
        User user = userService.login(account, password);
        if(user!=null){
            map.put("msg","登录成功");
            map.put("user",user);
            map.put("url","/dashboard");
            return map;
        }else{
            map.put("msg","您输入的账号或密码错误");
            map.put("url","/login");
            return map;
        }
    }

    @PostMapping("/update")
    public Integer updateUser(@RequestBody User user){
        System.out.println(user);
        int result = userService.updateUser(user);
        if(result>0){
            return 1;
        }else {
            return 0;
        }
    }
}
