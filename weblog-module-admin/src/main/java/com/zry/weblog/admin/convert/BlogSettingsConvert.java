package com.zry.weblog.admin.convert;

import com.zry.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.zry.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.zry.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSettingsConvert {
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);
    // 将VO转为DO
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);
    // 将DO转为VO
    FindBlogSettingsRspVO convertDO2VO(BlogSettingsDO bean);

}
