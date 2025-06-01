package com.zry.weblog.web.convert;

import com.zry.weblog.common.domain.dos.BlogSettingsDO;
import com.zry.weblog.web.model.vo.blogsetttings.FindBlogSettingsDetailRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSettingsConvert {
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 DO 转化为 VO
     */
    FindBlogSettingsDetailRspVO convertDO2VO(BlogSettingsDO bean);

}
