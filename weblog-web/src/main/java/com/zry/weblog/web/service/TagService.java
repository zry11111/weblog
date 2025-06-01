package com.zry.weblog.web.service;

import com.zry.weblog.common.utils.Response;

public interface TagService {
    /**
     * 获取标签列表
     */
    Response findTagList();
}
