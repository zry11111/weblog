package com.zry.weblog.web;

import com.zry.weblog.common.domain.dos.UserDO;
import com.zry.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void insert(){
        UserDO userDO = UserDO.builder()
                .username("稚生2")
                .password("123456")
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();
        userMapper.insert(userDO);
    }

    @Test
    void contextLoads() {
    }

}
