package com.example.myblog.mapper;

import com.example.myblog.entity.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ArticleMapper
 * @Description: 博客表的数据库操作接口类
 * @Date 2024/2/3 0:20
 */
@Mapper
public interface ArticleMapper {
    // 添加文章
    int add(ArticleVO articleVO);

    // 根据文章id和用户id查询文章
    ArticleVO getDataByIdAndUid(@Param("id") Integer id, @Param("uid") Integer uid);

    // 根据文章id和用户id修改文章
    int updateByIdAndUid(ArticleVO articleVO);

    // 根据文章id查询文章
    ArticleVO getDataById(@Param("id") Integer id);

    // 根据文章id增加文章阅读量
    int addRcount(@Param("id") Integer id);

    // 查询当前用户所有的文章
    List<ArticleVO> getList(@Param("uid") Integer uid,
                            @Param("pageSize") Integer pageSize,
                            @Param("offset") Integer offset);

    // 删除文章
    int delArt(@Param("id") Integer id, @Param("uid") Integer uid);

    // 获取所有文章并分页
    List<ArticleVO> getArtListByPage(@Param("pageSize") Integer pageSize,
                                     @Param("offset") Integer offset);

    // 获取所有文章数量
    int getArtNum();

    String getUsernameByUid(@Param("uid") Integer uid);
}
