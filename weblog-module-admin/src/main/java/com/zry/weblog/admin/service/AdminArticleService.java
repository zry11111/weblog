package com.zry.weblog.admin.service;

import com.zry.weblog.admin.model.vo.article.*;
import com.zry.weblog.common.utils.Response;

public interface AdminArticleService {
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);
    Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO);
    Response findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO);
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);
    Response updateArticle(UpdateArticleReqVO updateArticleReqVO);
}
