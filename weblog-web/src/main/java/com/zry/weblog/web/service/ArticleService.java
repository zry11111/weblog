package com.zry.weblog.web.service;

import com.zry.weblog.common.utils.Response;
import com.zry.weblog.web.model.vo.article.FindArticleDetailReqVO;
import com.zry.weblog.web.model.vo.article.FindIndexArticlePageListReqVO;

public interface ArticleService {
    /**
     * 获取首页文章分页数据
     */
    Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);

    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);
}
