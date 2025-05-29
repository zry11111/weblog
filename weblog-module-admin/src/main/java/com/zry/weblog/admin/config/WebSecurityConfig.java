package com.zry.weblog.admin.config;

import com.zry.weblog.jwt.config.JwtAuthenticationSecurityConfig;
import com.zry.weblog.jwt.filter.TokenAuthenticationFilter;
import com.zry.weblog.jwt.handler.RestAccessDeniedHandler;
import com.zry.weblog.jwt.handler.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 启用方法级别的权限控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(). // 禁用 csrf
                    formLogin().disable() // 禁用表单登录
                    .apply(jwtAuthenticationSecurityConfig) // 设置用户登录认证相关配置
                .and()
                    .authorizeHttpRequests()
                    .mvcMatchers("/admin/**").authenticated() // 认证所有以 /admin 为前缀的 URL 资源
                    .anyRequest().permitAll() // 其他都需要放行，无需认证
                .and()
                    .httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint) // 认证失败处理类
                .and()
                    .exceptionHandling().accessDeniedHandler(restAccessDeniedHandler) // 授权失败处理类
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 前后端分离，无需创建会话
                .and()
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // 添加 TokenAuthenticationFilter 过滤器在用户认证过滤器之前
                ;
    }
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
}
