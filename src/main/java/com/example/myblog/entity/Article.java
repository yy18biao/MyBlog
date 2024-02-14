package com.example.myblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: article
 * @Description: 博客表的实体类
 * @Date 2024/2/3 0:15
 */
@Data
public class Article {
    private int id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 时间格式化
    private LocalDateTime createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 时间格式化
    private LocalDateTime updatetime;
    private int uid;
    private int rcount;
    private int state;
}
