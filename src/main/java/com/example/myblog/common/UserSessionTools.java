package com.example.myblog.common;

import com.example.myblog.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * @ClassName: UserSessionTools
 * @Description: 用户会话操作类
 * @Date 2024/2/3 17:47
 */
public class UserSessionTools {
    /**
     * 从会话中获取用户
     * @param request
     */
    public static User getLoginUser(HttpServletRequest request){
        Object user = null;
        HttpSession session = request.getSession();
        if (session != null && (user = session.getAttribute(ApplicationVariable.SESSION_KEY_USER)) != null) {
            return (User) user;
        }
        return null;
    }
}
