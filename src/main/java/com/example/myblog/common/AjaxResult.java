package com.example.myblog.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AjaxResult
 * @Description: 返回前端的统一数据格式
 * @Date 2024/2/3 0:05
 */
@Data
public class AjaxResult implements Serializable {
    private int code;
    private String msg;
    private Object data;

    /**
     * 业务处理成功后，将返回数据包装为统一格式返回
     *
     * @param data
     */
    public static AjaxResult success(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(200);
        ajaxResult.setMsg("");
        ajaxResult.setData(data);
        return ajaxResult;
    }

    public static AjaxResult success(Object data, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(200);
        ajaxResult.setMsg(msg);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /**
     * 业务处理失败后，将返回数据包装为统一格式返回
     *
     * @param code
     * @param msg
     */
    public static AjaxResult fail(Integer code, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        ajaxResult.setData("");
        return ajaxResult;
    }

    public static AjaxResult fail(Integer code, String msg, Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(code);
        ajaxResult.setMsg(msg);
        ajaxResult.setData(data);
        return ajaxResult;
    }
}
