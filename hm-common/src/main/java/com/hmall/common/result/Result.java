package com.hmall.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;      // 状态码
    private String message;    // 返回消息
    private T data;            // 返回数据

    // 成功响应，带数据
    public static <T> Result<T> ok(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 成功响应，不带数据
    public static <T> Result<T> ok() {
        return new Result<>(200, "操作成功", null);
    }

    // 失败响应
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    // 自定义响应
    public static <T> Result<T> of(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
