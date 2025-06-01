package com.zry.weblog.web.convert;


import com.zry.weblog.common.domain.dos.ArticleDO;
import com.zry.weblog.web.model.vo.article.FindIndexArticlePageListRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleConvert {
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    /**
     * 将 DO 转化为 VO
     */
    FindIndexArticlePageListRspVO convertDO2VO(ArticleDO bean);

}
