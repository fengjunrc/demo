package com.example.controller;

import com.example.model.User;
import com.example.service.IUserService;
import com.example.util.RedisCacheUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    private  RedisTemplate redisTemplate;

    @RequestMapping(value="/getAllUsers")
    public PageInfo<User> getAllUsers(Integer pageNo, Integer pageSize){
        List<User> userList =  userService.getAllUsers(pageNo,pageSize);

        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        redisTemplate.opsForList().leftPushAll("test1",list);
        //List<String> resutList =(List<String>) redisTemplate.opsForList().leftPop("test");
        System.out.println(redisTemplate.opsForList().size("test1"));
        long size = redisTemplate.opsForList().size("test1");
        for (int i=0 ;i<size;i++){
            System.out.println(redisTemplate.opsForList().leftPop("test1"));
        }

        Set<String> set = new HashSet<String>();
        set.add("w1");
        set.add("w2");
        set.add("w3");
        redisTemplate.opsForSet().add("setTest",set);
        long ssize = redisTemplate.opsForSet().add(set);
        for (int i=0 ;i<ssize;i++){
            System.out.println(redisTemplate.opsForSet().pop("setTest"));
        }
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
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
