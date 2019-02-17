package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    // 通过模块
    SUCCESS(0, "成功"),
    SERVER_ERROR(500, "服务器异常"),
    // 登录模块
    SESSION_ERROR(50001, "session不存在或者已经失效"),
    PASSWORD_EMPTY(50002, "密码不能为空"),
    MOBILE_EMPTY(50003, "手机号不能为空"),
    ;

    private Integer code;

    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
