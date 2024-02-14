package com.example.myblog.controller;

import com.example.myblog.common.AjaxResult;
import com.example.myblog.common.ApplicationVariable;
import com.example.myblog.common.StringTools;
import com.example.myblog.common.UserSessionTools;
import com.example.myblog.entity.vo.ArticleVO;
import com.example.myblog.service.ArticleService;
import com.example.myblog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: ArticleController
 * @Description: 博客管理控制器
 * @Date 2024/2/3 0:26
 */
@RestController
@RequestMapping("art")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
     *
     * @param articleVO
     * @param request
     * @return
     */
    @RequestMapping("add")
    public AjaxResult add(ArticleVO articleVO, HttpServletRequest request) {
        // 非空验证
        if (articleVO == null || !StringUtils.hasLength(articleVO.getTitle())
                || !StringUtils.hasLength(articleVO.getContent())) {
            return AjaxResult.fail(-1, "参数有误");
        }

        // 从会话获取用户id
        articleVO.setUid(UserSessionTools.getLoginUser(request).getId());

        // 插入数据
        int res = articleService.add(articleVO);

        // 返回数据给前端
        return AjaxResult.success(res);
    }

    /**
     * 根据用户id和文章id查询文章
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("getdatabyidanduid")
    public AjaxResult getDataByIdAnUid(Integer id, HttpServletRequest request) {
        // 非空验证
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "参数有误");
        }

        // 从会话中获取用户id
        Integer uid = UserSessionTools.getLoginUser(request).getId();

        // 执行操作
        ArticleVO articleVO = articleService.getDataByIdAndUid(id, uid);

        // 返回数据
        return AjaxResult.success(articleVO);
    }

    /**
     * 根据用户id和文章id修改文章
     *
     * @param articleVO
     * @param request
     */
    @RequestMapping("update")
    public AjaxResult updateByIdAndUid(ArticleVO articleVO, HttpServletRequest request) {
        // 非空验证
        if (articleVO == null || !StringUtils.hasLength(articleVO.getTitle())
                || articleVO.getId() <= 0 || !StringUtils.hasLength(articleVO.getContent())) {
            return AjaxResult.fail(-1, "参数有误");
        }

        // 从会话中获取用户id放到对象中
        articleVO.setUid(UserSessionTools.getLoginUser(request).getId());
        articleVO.setUpdatetime(LocalDateTime.now());

        // 执行操作
        int res = articleService.updateByIdAndUid(articleVO);

        // 返回数据
        return AjaxResult.success(res);
    }

    /**
     * 根据文章id获取文章所有数据信息
     * @param id
     * @return
     */
    @RequestMapping("getdatabyid")
    public AjaxResult getDataById(Integer id) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "参数有误");
        }
        ArticleVO articleVO = articleService.getDataById(id);
        return AjaxResult.success(articleVO);
    }

    /**
     * 增加阅读量
     * @param id
     * @return
     */
    @RequestMapping("addrcount")
    public AjaxResult addRcount(Integer id) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "参数有误");
        }
        int res = articleService.addRcount(id);
        return AjaxResult.success(res);
    }

    /**
     * 根据分页获取所有文章
     * @param pageIndex
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping("list")
    public AjaxResult getList(@Param("pageIndex") Integer pageIndex,
                              @Param("pageSize") Integer pageSize,
                              HttpServletRequest request) {
        // 默认值处理
        if (pageSize == null || pageSize <= 0) {
            pageSize = 3;
        }
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }

        // 分页公式
        int offset = (pageIndex - 1) * pageSize;

        Integer uid = UserSessionTools.getLoginUser(request).getId();
        List<ArticleVO> list = articleService.getList(uid, pageSize, offset);

        // 将文章正文截取成文章摘要
        for (ArticleVO item : list) {
            String content = StringTools.subLength(item.getContent(), ApplicationVariable.CONTENT_MAX_LENGTH);
            item.setContent(content);
        }
        return AjaxResult.success(list);
    }

    /**
     * 删除文章
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("del")
    public AjaxResult delArt(Integer id, HttpServletRequest request) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "参数有误");
        }

        Integer uid = UserSessionTools.getLoginUser(request).getId();
        int res = articleService.delArt(id, uid);
        return AjaxResult.success(res);
    }

    /**
     * 根据分页获取所有文章
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping("getlistbypage")
    public AjaxResult getArtListByPage(Integer pageSize, Integer pageIndex){
        // 默认值处理
        if (pageSize == null || pageSize <= 0) {
            pageSize = 3;
        }
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }

        // 分页公式
        int offset = (pageIndex - 1) * pageSize;
        List<ArticleVO> list = articleService.getArtListByPage(pageSize, offset);

        // 多线程并发处理
        list.stream().parallel().forEach(item -> {
            item.setContent(StringTools.subLength(item.getContent(), ApplicationVariable.CONTENT_MAX_LENGTH));
            item.setUsername(articleService.getUsernameByUid(item.getUid()));
        });

        return AjaxResult.success(list);
    }

    /**
     * 获取文章说两
     * @return
     */
    @RequestMapping("getcount")
    public AjaxResult getArtNum(){
        return AjaxResult.success(articleService.getArtNum());
    }
}
