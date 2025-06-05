package com.zry.weblog.web.service;

import com.zry.weblog.common.utils.Response;
import com.zry.weblog.web.model.vo.search.SearchArticlePageListReqVO;

public interface SearchService {
    /**
     * 关键词分页搜索
     * @param searchArticlePageListReqVO
     * @return
     */
    Response searchArticlePageList(SearchArticlePageListReqVO searchArticlePageListReqVO);
}
