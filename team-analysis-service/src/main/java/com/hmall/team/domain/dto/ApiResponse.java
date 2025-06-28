package com.hmall.team.domain.dto;

import lombok.Data;

/**
 * API通用响应对象
 * @param <T> 响应数据类型
 */
@Data
public class ApiResponse<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 默认成功响应码
     */
    public static final int SUCCESS_CODE = 200;
    
    /**
     * 默认错误响应码
     */
    public static final int ERROR_CODE = 500;

    /**
     * 创建成功响应
     */
    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    /**
     * 创建成功响应
     * @param data 响应数据
     */
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(SUCCESS_CODE);
        response.setMessage("操作成功");
        response.setData(data);
        return response;
    }

    /**
     * 创建错误响应
     */
    public static <T> ApiResponse<T> error() {
        return error(ERROR_CODE, "操作失败");
    }

    /**
     * 创建错误响应
     * @param message 错误消息
     */
    public static <T> ApiResponse<T> error(String message) {
        return error(ERROR_CODE, message);
    }

    /**
     * 创建错误响应
     * @param code 错误码
     * @param message 错误消息
     */
    public static <T> ApiResponse<T> error(Integer code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
} 