package com.zry.weblog.jwt.filter;

import com.zry.weblog.jwt.utils.JwtTokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
//这里token校验器注册在webSecurityConfig中
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {//确保每一个请求只被过滤一次
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${jwt.tokenHeaderKey}")
    private String tokenHeaderKey;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(tokenHeaderKey);
        // 获取请求体中携带的参数
        String string = request.getParameter("username");
        if(StringUtils.startsWith(token,tokenPrefix)){
            token = StringUtils.substring(token, 7);
            log.info("token: {}", token);
            if(StringUtils.isNotBlank(token)){
                try {
                    // 校验 Token 是否可用, 若解析异常，针对不同异常做出不同的响应参数
                    jwtTokenHelper.validateToken(token);
                } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
                    // 抛出异常，统一让 AuthenticationEntryPoint 处理响应参数
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 不可用"));
                    return;
                } catch (ExpiredJwtException e) {//token 过期异常
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 已失效"));
                    return;
                }
                String username = jwtTokenHelper.getUsernameByToken(token);
                if(StringUtils.isNotBlank(username)&&
                        Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
                    // 从数据查询该用户
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将认证信息存储到 SecurityContext 中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        // 继续执行下一个过滤器
        filterChain.doFilter(request, response);
    }
}
