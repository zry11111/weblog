package com.zry.weblog.jwt.handler;

import com.zry.weblog.common.enums.ResponseCodeEnum;
import com.zry.weblog.common.utils.Response;
import com.zry.weblog.jwt.exception.UsernameOrPasswordNullException;
import com.zry.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("Authentication failed: {}", exception.getMessage());
        if(exception instanceof UsernameOrPasswordNullException){
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
        } else if (exception instanceof BadCredentialsException) {
            ResultUtil.fail(response,Response.fail(ResponseCodeEnum.USERNAME_OR_PWD_ERROR));
        }
        ResultUtil.fail(response, Response.fail(ResponseCodeEnum.LOGIN_FAIL));
    }
}
