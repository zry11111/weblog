package com.zry.weblog.web.service.impl;

import com.zry.weblog.common.domain.dos.BlogSettingsDO;
import com.zry.weblog.common.domain.mapper.BlogSettingsMapper;
import com.zry.weblog.common.utils.Response;
import com.zry.weblog.web.convert.BlogSettingsConvert;
import com.zry.weblog.web.model.vo.blogsetttings.FindBlogSettingsDetailRspVO;
import com.zry.weblog.web.service.BlogSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BlogSettingsServiceImpl implements BlogSettingsService {

    @Autowired
    private BlogSettingsMapper blogSettingsMapper;

    /**
     * 获取博客设置信息
     */
    @Override
    public Response findDetail() {
        // 查询博客设置信息（约定的 ID 为 1）
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        FindBlogSettingsDetailRspVO vo = new FindBlogSettingsDetailRspVO();
        // DO 转 VO
        BeanUtils.copyProperties(blogSettingsDO, vo);
        // 后面再改，不知道为什么出问题
//        FindBlogSettingsDetailRspVO vo = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);

        return Response.success(vo);
    }
}
