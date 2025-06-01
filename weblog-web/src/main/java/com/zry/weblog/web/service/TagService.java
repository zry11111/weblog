package com.zry.weblog.web.service;

import com.zry.weblog.common.utils.Response;
import com.zry.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;

public interface TagService {
    /**
     * 获取标签列表
     */
    Response findTagList();
    Response findTagPageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO);
}
