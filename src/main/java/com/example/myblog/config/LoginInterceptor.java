package com.example.myblog.config;

import com.example.myblog.common.ApplicationVariable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ClassName: LoginInterceptor
 * @Description: 验证是否已登录拦截器
 * @Date 2024/2/3 13:59
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断用户登录
        HttpSession session = request.getSession(false);
        // 如果session不为空并且找得到关于用户登录的key值则已经登陆
        if (session != null && session.getAttribute(ApplicationVariable.SESSION_KEY_USER) != null) {
            return true;
        }

        // 走到这里说明未登录直接跳转到登录界面
        response.sendRedirect("/login.html");
        return false;
    }
}
