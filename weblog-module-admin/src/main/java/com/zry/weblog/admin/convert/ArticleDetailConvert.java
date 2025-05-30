package com.zry.weblog.admin.convert;

import com.zry.weblog.admin.model.vo.article.FindArticleDetailRspVO;
import com.zry.weblog.common.domain.dos.ArticleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleDetailConvert {
    ArticleDetailConvert INSTANCE = Mappers.getMapper(ArticleDetailConvert.class);

    /**
     * 将 DO 转化为 VO
     */
    FindArticleDetailRspVO convertDO2VO(ArticleDO bean);

}
