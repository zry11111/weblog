package com.zry.weblog.common.model;

import lombok.Data;

@Data
public class BasePageQuery {
    private Long current = 1L; // 当前页码，默认为1
    private Long size = 10L; // 每页条数，默认为10
}
