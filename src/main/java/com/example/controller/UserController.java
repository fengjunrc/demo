package com.example.controller;

import com.example.model.User;
import com.example.service.IUserService;
import com.example.util.RedisCacheUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fj on 2017/4/13.
 */
@RestController
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    protected RedisTemplate<String,Object> redisTemplate;

    @RequestMapping(value="/getAllUsers")
    public PageInfo<User> getAllUsers(Integer pageNo,Integer pageSize){
        List<User> userList =  userService.getAllUsers(pageNo,pageSize);
        redisTemplate.opsForList().leftPushAll("userList",new Date().getTime(),userList);
        //redisTemplate.opsForSet().add("userList",new Date().getTime(),userList);
        return new PageInfo<>(userList);
    }

    @RequestMapping(value = "/addUser")
    public Integer addUser(@RequestBody User user){
       return  userService.addUser(user);
    }

    @RequestMapping(value = "/updateUser")
    public Integer updateUser(@RequestBody User user){
        return  userService.updateUser(user);
    }

    @RequestMapping(value = "/deleteUser")
    public  Integer deleteUser(Integer id){
        return userService.deleteUser(id);
    }

    @RequestMapping(value="/getAllUsersFromRedis")
    public void getAllUsersFromRedis(){
        String key = "user_Test7";
        List<User> codeList = new ArrayList<>();
        User u1 = new User();
        User u2 = new User();
        u1.setId(1);
        u1.setUsername("22");
        u1.setPassword("33");

        u2.setId(1);
        u2.setUsername("22");
        u2.setPassword("33");
        codeList.add(u1);
        codeList.add(u2);
        RedisCacheUtil  RedisCacheUtil = new RedisCacheUtil();
        RedisCacheUtil.setCacheList(key, codeList);
        System.out.println("保存数据成功！");


    }
}
