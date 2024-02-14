package com.example.myblog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: User
 * @Description: 用户表的实体类
 * @Date 2024/2/3 0:15
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String photo;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private int state;
    private String github;
}
