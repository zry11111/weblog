package com.zry.weblog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zry.weblog.common.domain.mapper")
public class MybatisPlusConfig {
}
