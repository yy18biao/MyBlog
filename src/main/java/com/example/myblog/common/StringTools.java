package com.example.myblog.common;

import org.springframework.util.StringUtils;

/**
 * @ClassName: StringTools
 * @Description: 字符串操作工具类
 * @Date 2024/2/4 1:46
 */
public class StringTools {
    /**
     * 截取字符串为指定的最大长度
     * @param val 指定字符串
     * @param maxLength 指定最大长度
     */
    public static String subLength(String val, int maxLength) {
        if (!StringUtils.hasLength(val) || maxLength <= 0) {
            return val;
        }

        if (val.length() <= maxLength) {
            return val;
        }

        return val.substring(0, maxLength);
    }
}
