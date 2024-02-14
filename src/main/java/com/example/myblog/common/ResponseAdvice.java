package com.example.myblog.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName: ResponseAdvice
 * @Description: 保底统一返回数据策略
 * @Date 2024/2/4 2:00
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果返回数据类型为指定类型 则直接返回
        if (body instanceof AjaxResult) {
            return body;
        }

        // String类型需要单独处理
        if (body instanceof String) {
            return objectMapper.writeValueAsString(AjaxResult.success(body));
        }

        // 其他类型将其封装为指定类型返回
        return AjaxResult.success(body);
    }
}
