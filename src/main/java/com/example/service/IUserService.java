package com.example.service;

import com.example.model.User;

import java.util.List;

/**
 * Created by fj on 2017/4/13.
 */
public interface IUserService {
    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<User> getAllUsers(Integer pageNo,Integer pageSize);


    int addUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
