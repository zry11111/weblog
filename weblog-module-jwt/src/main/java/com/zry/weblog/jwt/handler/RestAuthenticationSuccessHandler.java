package com.zry.weblog.jwt.handler;

import com.zry.weblog.common.utils.Response;
import com.zry.weblog.jwt.domain.vo.LoginRspVo;
import com.zry.weblog.jwt.utils.JwtTokenHelper;
import com.zry.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Slf4j

public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    //这个是接口中写了default的方法，使用默认的处理方式
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String username = userDetails.getUsername();
        //通过用户名生成token
        String token = jwtTokenHelper.generateToken(username);
        LoginRspVo loginRspVo = LoginRspVo.builder().token(token).build();
        //返回token
        ResultUtil.ok(response,Response.success(loginRspVo));
    }
}
