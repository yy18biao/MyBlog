package com.example.myblog.common;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @ClassName: PasswordTools
 * @Description: 密码工具类
 * @Date 2024/2/4 3:31
 */
public class PasswordTools {
    /**
     * 密码加盐加密
     * @param password 明文密码
     * @return 加盐加密后的密码
     */
    public static String encrypt(String password){
        // 产生盐值
        String salt = UUID.randomUUID().toString().replace("-", "");
        // 将盐值+密码进行加密
        String fPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes());
        // 返回盐值+加密后的密码
        return salt + "*" + fPassword;
    }

    /**
     * 密码加盐加密
     * @param password 明文密码
     * @param salt 盐值
     * @return 加盐加密后的密码
     */
    public static String encrypt(String password, String salt) {
        // 将盐值+密码进行加密
        String fPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes());
        // 返回盐值+加密后的密码
        return salt + "*" + fPassword;
    }

    /**
     * 验证明文密码加密后与加盐加密的密码是否一致
     * @param password 明文密码
     * @param dbPassword 数据库中存储的加盐加密的密码
     */
    public static boolean decrypt(String password, String dbPassword){
        // 验证参数是否正确
        if(StringUtils.hasLength(password) && StringUtils.hasLength(dbPassword)
           && dbPassword.length() == 65 && dbPassword.contains("*")){
            // 得到盐值
            String[] pwdArr = dbPassword.split("\\*");
            String salt = pwdArr[0];
            // 生成验证密码的加盐加密密码
            String check = encrypt(password, salt);
            // 判断是否相同
            if(check.equals(dbPassword)){
                return true;
            }
        }
        return false;
    }
}
