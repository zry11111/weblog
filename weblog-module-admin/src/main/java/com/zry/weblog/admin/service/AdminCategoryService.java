package com.zry.weblog.admin.service;

import com.zry.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.zry.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.zry.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.zry.weblog.common.utils.PageResponse;
import com.zry.weblog.common.utils.Response;

public interface AdminCategoryService {
    /**
    * @Description:添加分类
    * @Date: 2025/5/27
    */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);
    PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);
    /**
    * @Description: 获取分类列表
    */
    Response findCategorySelectList();
}
