package com.example.controller;

import com.example.model.User;
import com.example.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fj on 2017/4/13.
 */
@RestController
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping(value="/getAllUsers")
    public PageInfo<User> getAllUsers(Integer pageNo,Integer pageSize){
        List<User> userList =  userService.getAllUsers(pageNo,pageSize);
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
}
