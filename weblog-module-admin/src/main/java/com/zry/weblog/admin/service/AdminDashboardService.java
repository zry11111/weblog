package com.zry.weblog.admin.service;

import com.zry.weblog.common.utils.Response;

public interface AdminDashboardService {

    /**
     * 获取仪表盘基础统计信息
     */
    Response findDashboardStatistics();
    Response findDashboardPublishArticleStatistics();
}