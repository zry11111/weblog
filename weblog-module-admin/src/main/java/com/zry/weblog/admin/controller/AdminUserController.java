package com.zry.weblog.admin.controller;

import com.zry.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.zry.weblog.admin.service.AdminUserService;
import com.zry.weblog.common.aspect.ApiOperationLog;
import com.zry.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Api(tags = "Admin用户模块")
public class AdminUserController {
    @Autowired
    private AdminUserService userService;
    @ApiOperation(value = "更新管理员用户密码")
    @ApiOperationLog(description = "更新管理员用户密码")
    @PostMapping("/password/update")
    public Response updatePassword(@RequestBody @Validated UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        return userService.updatePassword(updateAdminUserPasswordReqVO);
    }
    @PostMapping("/user/info")
    @ApiOperation(value = "获取用户信息")
    @ApiOperationLog(description = "获取用户信息")
    public Response findUserInfo() {
        return userService.findUserInfo();
    }

}
