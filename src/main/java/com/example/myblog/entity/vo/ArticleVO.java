package com.example.myblog.entity.vo;

import com.example.myblog.entity.Article;
import lombok.Data;

/**
 * @ClassName: ArticleVO
 * @Description:
 * @Date 2024/2/3 0:22
 */
@Data
public class ArticleVO extends Article {
    private String username;
}
