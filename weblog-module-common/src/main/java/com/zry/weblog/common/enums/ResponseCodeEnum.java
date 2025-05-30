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
    PASSWORD_NOT_MATCH("20005", "密码和确认密码不一致，请重新输入"),
    CATEGORY_NAME_IS_EXISTED("20006", "分类名称已存在，请勿重复添加"),
    TAG_CANT_DUPLICATE("20007", "请勿添加表中已存在的标签！"),
    TAG_NOT_EXISTED("20008", "该标签不存在！"),
    FILE_UPLOAD_FAILED("20009", "文件上传失败，请稍后重试"),
    CATEGORY_NOT_EXISTED("20010", "提交的分类不存在！"),
    ;

    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

}
