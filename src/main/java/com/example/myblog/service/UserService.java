package com.example.myblog.service;

import com.example.myblog.common.AjaxResult;
import com.example.myblog.entity.User;
import com.example.myblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserService
 * @Description:
 * @Date 2024/2/3 0:23
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int reg(User user){
        return userMapper.reg(user);
    }

    public User login(String username){
        return userMapper.login(username);
    }

    public int getCount(Integer uid){
        return userMapper.getCount(uid);
    }

    public int savePhoto(String filename, int id){
        return userMapper.savePhoto(filename, id);
    }

    public int update(int id, String phone, String email, String password){
        return userMapper.update(id, phone, email, password);
    }
}
