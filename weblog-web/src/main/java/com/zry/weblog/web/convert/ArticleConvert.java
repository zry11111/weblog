package com.zry.weblog.web.convert;


import com.zry.weblog.common.domain.dos.ArticleDO;
import com.zry.weblog.web.model.vo.archive.FindArchiveArticleRspVO;
import com.zry.weblog.web.model.vo.article.FindIndexArticlePageListRspVO;
import com.zry.weblog.web.model.vo.category.FindCategoryArticlePageListRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleConvert {
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    /**
     * 将 DO 转化为 VO
     */
    FindIndexArticlePageListRspVO convertDO2VO(ArticleDO bean);
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    @Mapping(target = "createMonth", expression = "java(java.time.YearMonth.from(bean.getCreateTime()))")
    FindArchiveArticleRspVO convertDO2ArchiveArticleVO(ArticleDO bean);
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(bean.getCreateTime()))")
    FindCategoryArticlePageListRspVO convertDO2CategoryArticleVO(ArticleDO bean);
}
