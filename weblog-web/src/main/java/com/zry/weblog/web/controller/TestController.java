package com.zry.weblog.web.controller;
import com.zry.weblog.common.aspect.ApiOperationLog;
import com.zry.weblog.common.domain.mapper.UserMapper;
import com.zry.weblog.common.utils.Response;
import com.zry.weblog.web.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@Api(tags = "首页模块")
public class TestController {

    @PostMapping("/admin/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口")
    public Response test(@RequestBody @Validated User user) {
        // 打印入参
//        log.info(JsonUtil.toJsonString(user));
//
//        // 设置三种日期字段值
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateDate(LocalDate.now());
//        user.setTime(LocalTime.now());
        return Response.success(user);
    }
    @PostMapping("/admin/update")
    @ApiOperationLog(description = "测试更新接口")
    @ApiOperation(value = "测试更新接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response testUpdate() {
        return Response.success();
    }

}
