package com.example.myblog.mapper;

import com.example.myblog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName: UserMapperTest
 * @Description:
 * @Date 2024/2/3 13:15
 */
@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void reg() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("12345");
        user.setEmail("123445");
        user.setPhone("12345");
        userMapper.reg(user);
    }
}