package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.IUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fj on 2017/4/13.
 */
@Service
public class UserServiceImpl  implements IUserService {

    @Resource
    private UserMapper userMapper;

    public List<User> getAllUsers(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
      return  userMapper.selectAll();
    }

    /**
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }
}
