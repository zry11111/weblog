package com.zry.weblog.web.service;

import com.zry.weblog.common.utils.Response;

public interface CategoryService {
    /**
     * 获取分类列表
     */
    Response findCategoryList();
}
