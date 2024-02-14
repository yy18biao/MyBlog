package com.example.myblog.controller;

import com.example.myblog.common.AjaxResult;
import com.example.myblog.common.ApplicationVariable;
import com.example.myblog.common.PasswordTools;
import com.example.myblog.common.UserSessionTools;
import com.example.myblog.entity.User;
import com.example.myblog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName: UserController
 * @Description: 用户管理控制器
 * @Date 2024/2/3 0:26
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 注册功能
     * @param user
     */
    @RequestMapping("reg")
    public AjaxResult reg(User user){
        // 进行非空判断
        if(user == null || !StringUtils.hasLength(user.getUsername()) || !StringUtils.hasLength(user.getPassword())
                || !StringUtils.hasLength(user.getPhone()) || !StringUtils.hasLength(user.getEmail())){
            return AjaxResult.fail(-1, "参数有误");
        }
        // 对密码进行加盐加密
        user.setPassword(PasswordTools.encrypt(user.getPassword()));
        // 调用注册函数，返回注册结果
        int res = userService.reg(user);
        return AjaxResult.success(res);
    }

    /**
     * 登录功能
     * @param username
     * @param password
     * @param request
     */
    @RequestMapping("login")
    public AjaxResult login(String username, String password, HttpServletRequest request){
        // 进行非空判断
        if (!StringUtils.hasLength(username) ||
                !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "参数有误");
        }

        // 获取查询结果
        User user = userService.login(username);

        // 判断查询结果是否合法
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-2, "用户名或密码错误！");
        }
        // 判断密码是否相等(加盐加密)
        if (!PasswordTools.decrypt(password, user.getPassword())) {
            return AjaxResult.fail(-2, "用户名或密码错误！");
        }

        // 将当前成功登录的用户信息存储到 session
        HttpSession session = request.getSession();
        session.setAttribute(ApplicationVariable.SESSION_KEY_USER, user);

        // 返回正确结果
        return AjaxResult.success(1);
    }

    /**
     * 注销用户
     * @param request
     */
    @RequestMapping("logout")
    public AjaxResult logout(HttpServletRequest request) {
        // 获取session并移除
        HttpSession session = request.getSession();
        session.removeAttribute(ApplicationVariable.SESSION_KEY_USER);
        return AjaxResult.success(1);
    }

    /**
     * 判断用户是否已经登录
     * @param request
     * @return
     */
    @RequestMapping("islogin")
    public AjaxResult isLogin(HttpServletRequest request){
        if(UserSessionTools.getLoginUser(request) == null){
            return AjaxResult.success(0);
        }
        return AjaxResult.success(1);
    }

    /**
     * 获取用户所有数据细腻些
     * @param request
     * @return
     */
    @RequestMapping("getuser")
    public AjaxResult getUser(HttpServletRequest request){
        return AjaxResult.success(UserSessionTools.getLoginUser(request));
    }

    /**
     * 获取当前用户的所有文章数量
     * @param request
     * @return
     */
    @RequestMapping("getcount")
    public AjaxResult getCount(HttpServletRequest request){
        int uid = UserSessionTools.getLoginUser(request).getId();
        return AjaxResult.success(userService.getCount(uid));
    }

    /**
     * 修改用户信息
     * @param phone
     * @param email
     * @param password
     * @param pwd1
     * @param request
     * @return
     */
    @RequestMapping("update")
    public AjaxResult update(String phone, String email, String password, String pwd1, HttpServletRequest request){
        phone = phone.equals("") ? null : phone;
        email = email.equals("") ? null : email;
        password = password.equals("") ? null : password;
        User user = UserSessionTools.getLoginUser(request);

        // 比较用户的密码和输入的密码是否一致
        if(!pwd1.equals("")) {
            String dbPassword = user.getPassword();
            if (!PasswordTools.decrypt(pwd1, dbPassword)) {
                return AjaxResult.fail(-1, "密码错误");
            }
        }

        // 获取用户id
        int id = UserSessionTools.getLoginUser(request).getId();

        // 将用户的新密码进行加盐加密
        if(password != null) {
            password = PasswordTools.encrypt(password);
        }

        // 修改操作
        int res = userService.update(id, phone, email, password);

        // 更新会话
        if(res >= 1){
            HttpSession session = request.getSession();
            if(phone != null) {
                user.setPhone(phone);
            }
            if(email != null) {
                user.setEmail(email);
            }
            if(password != null) {
                user.setPassword(password);
            }
            session.setAttribute(ApplicationVariable.SESSION_KEY_USER, user);
        }
        return AjaxResult.success(res);
    }
}
