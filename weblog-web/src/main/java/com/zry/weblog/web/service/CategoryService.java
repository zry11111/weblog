package com.zry.weblog.web.service;

import com.zry.weblog.common.utils.Response;
import com.zry.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;

public interface CategoryService {
    /**
     * 获取分类列表
     */
    Response findCategoryList();
    Response findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO);
}
