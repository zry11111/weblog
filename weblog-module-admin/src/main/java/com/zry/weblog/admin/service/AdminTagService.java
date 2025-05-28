package com.zry.weblog.admin.service;
import com.zry.weblog.admin.model.vo.tag.*;
import com.zry.weblog.common.utils.PageResponse;
import com.zry.weblog.common.utils.Response;
public interface AdminTagService {


    Response addTags(AddTagReqVO addTagReqVO);

    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    Response searchTags(SearchTagsReqVO searchTagsReqVO);
}
