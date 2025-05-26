package com.zry.weblog.common.enums;

import com.zry.weblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台小哥正在努力修复中..."),
    PARAM_NOT_VALID("10001", "参数错误"),


    // ----------- 业务异常状态码 -----------

    // ----------- 登录异常状态码 -----------
    USERNAME_OR_PWD_ERROR("20000", "用户名或密码错误"),
    LOGIN_FAIL("20001", "登录失败"),
    UNAUTHORIZED("20002", "无访问权限，请先登录！"),
    USERNAME_NOT_FOUND("20003", "用户名不存在"),
    FORBIDDEN("20004", "演示账号仅支持查询操作！"),
    ;

    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

}
