package com.zry.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zry.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.zry.weblog.admin.model.vo.user.FindUserInfoRspVO;
import com.zry.weblog.admin.service.AdminUserService;
import com.zry.weblog.common.domain.dos.UserDO;
import com.zry.weblog.common.domain.mapper.UserMapper;
import com.zry.weblog.common.enums.ResponseCodeEnum;
import com.zry.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
//        拿到用户名和密码
        String username = updateAdminUserPasswordReqVO.getUsername();
        String password = updateAdminUserPasswordReqVO.getPassword();
        
//        对密码进行就加密
        String encodePassword = passwordEncoder.encode(password);
        UserDO userDO = new UserDO();
        userDO.builder()
                .username(username)
                .password(encodePassword)
                .build();
        // 编写通过username 更新密码的wrapper
        UpdateWrapper<UserDO> wrapper = new UpdateWrapper<>();
        wrapper.eq("username", username).set("password", encodePassword);
        int count = userMapper.update(userDO, wrapper);
        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.USERNAME_NOT_FOUND);
    }
    @Override
    public Response findUserInfo() {
        // 获取存储在 ThreadLocal 中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 拿到用户名
        String username = authentication.getName();

        return Response.success(FindUserInfoRspVO.builder().username(username).build());
    }
}
