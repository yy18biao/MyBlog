package com.example.myblog.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: ExceptionAdvice
 * @Description: 统一异常处理类
 * @Date 2024/2/4 1:56
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public AjaxResult doException(Exception e){
        return AjaxResult.fail(-1, e.getMessage());
    }
}
