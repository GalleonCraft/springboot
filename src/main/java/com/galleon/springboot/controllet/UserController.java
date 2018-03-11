package com.galleon.springboot.controllet;

import com.galleon.springboot.entity.User;
import com.galleon.springboot.service.UserService;
import com.galleon.springboot.util.JsonWebToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  406525372@qq.com
 * on 2018/3/11
 **/
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public List<User> getAllUser(){
        List<User> data = userService.queryAll();
        return data;
    }

    @RequestMapping("/lgoin")
    public Map<String,Object> getAccessToken(String username, String password) throws Exception {
        userService.queryAll();
        User user = userService.queryByUsername(username);
        Map<String,Object> data = new HashMap<>();
        if(user.getPassword().equals(password)){
            data.put("access_token", JsonWebToken.createToken(user));
            data.put("message","登陆成功！");
            data.put("flag",Boolean.TRUE);
        }else{
            data.put("access_token","");
            data.put("message","用户名或密码错误！");
            data.put("flag",Boolean.FALSE);
        }
        return data;
    }
}
