package com.zry.weblog.admin.service;

import com.zry.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.zry.weblog.common.utils.Response;


public interface AdminUserService {
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);
    Response findUserInfo();
}
