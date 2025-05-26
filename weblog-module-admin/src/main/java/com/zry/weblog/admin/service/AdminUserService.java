package com.zry.weblog.admin.service;

import com.zry.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.zry.weblog.common.utils.Response;
import org.springframework.stereotype.Service;



public interface AdminUserService {
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);
    Response findUserInfo();
}
