package com.zry.weblog.admin.service;

import com.zry.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.zry.weblog.common.utils.Response;

public interface AdminBlogSettingsService {
    /*更新博客设置信息
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);
//     获取博客设置信息
    Response findDetail();
}

