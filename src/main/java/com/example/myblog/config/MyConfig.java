package com.example.myblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: MyConfig
 * @Description: 拦截器的拦截规则
 * @Date 2024/2/3 14:07
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/reg.html")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/reg")
                .excludePathPatterns("/blog_list.html")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/editor.md/**")
                .excludePathPatterns("/blog_content.html")
                .excludePathPatterns("/art/getdatabyid")
                .excludePathPatterns("/art/addrcount")
                .excludePathPatterns("/user/islogin")
                .excludePathPatterns("/art/getlistbypage")
                .excludePathPatterns("/art/getcount")
        ;
    }
}
