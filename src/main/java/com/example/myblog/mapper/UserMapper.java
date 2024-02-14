package com.example.myblog.mapper;

import com.example.myblog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: UserMapper
 * @Description: 用户表的数据库操作接口类
 * @Date 2024/2/3 0:20
 */
@Mapper
public interface UserMapper {
    int reg(User user);
    User login(@Param("username") String username);
    int getCount(@Param("uid") Integer uid);
    int savePhoto(@Param("filename") String filename, @Param("id") Integer id);
    int update(int id, String phone, String email, String password);
}
