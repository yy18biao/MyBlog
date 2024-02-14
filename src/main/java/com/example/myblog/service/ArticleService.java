package com.example.myblog.service;

import com.example.myblog.entity.vo.ArticleVO;
import com.example.myblog.mapper.ArticleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ArticleService
 * @Description:
 * @Date 2024/2/3 0:23
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public int add(ArticleVO articleVO) {
        return articleMapper.add(articleVO);
    }

    public ArticleVO getDataByIdAndUid(Integer id, Integer uid) {
        return articleMapper.getDataByIdAndUid(id, uid);
    }

    public int updateByIdAndUid(ArticleVO articleVO) {
        return articleMapper.updateByIdAndUid(articleVO);
    }

    public ArticleVO getDataById(Integer id) {
        return articleMapper.getDataById(id);
    }

    public int addRcount(Integer id) {
        return articleMapper.addRcount(id);
    }

    public List<ArticleVO> getList(Integer uid, Integer pageSize, Integer offset){
        return articleMapper.getList(uid, pageSize, offset);
    }

    public int delArt(Integer id, Integer uid){
        return articleMapper.delArt(id, uid);
    }

    public List<ArticleVO> getArtListByPage(Integer pageSize, Integer offset){
        return articleMapper.getArtListByPage(pageSize, offset);
    }

    public int getArtNum(){
        return articleMapper.getArtNum();
    }

    public String getUsernameByUid(Integer uid){
        return articleMapper.getUsernameByUid(uid);
    }
}
