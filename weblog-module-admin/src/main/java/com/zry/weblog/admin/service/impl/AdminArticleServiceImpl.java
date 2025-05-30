package com.zry.weblog.admin.service.impl;

import com.zry.weblog.admin.model.vo.article.PublishArticleReqVO;
import com.zry.weblog.admin.service.AdminArticleService;
import com.zry.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.zry.weblog.common.domain.dos.ArticleContentDO;
import com.zry.weblog.common.domain.dos.ArticleDO;
import com.zry.weblog.common.domain.dos.CategoryDO;
import com.zry.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.zry.weblog.common.domain.mapper.ArticleContentMapper;
import com.zry.weblog.common.domain.mapper.ArticleMapper;
import com.zry.weblog.common.domain.mapper.CategoryMapper;
import com.zry.weblog.common.enums.ResponseCodeEnum;
import com.zry.weblog.common.exception.BizException;
import com.zry.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AdminArticleServiceImpl implements AdminArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 发布文章
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response publishArticle(PublishArticleReqVO publishArticleReqVO) {
        // 1. VO 转 ArticleDO, 并保存
        ArticleDO articleDO = ArticleDO.builder()
                .title(publishArticleReqVO.getTitle())
                .cover(publishArticleReqVO.getCover())
                .summary(publishArticleReqVO.getSummary())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        articleMapper.insert(articleDO);

        // 拿到插入记录的主键 ID
        Long articleId = articleDO.getId();

        // 2. VO 转 ArticleContentDO，并保存
        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleId)
                .content(publishArticleReqVO.getContent())
                .build();
        articleContentMapper.insert(articleContentDO);

        // 3. 处理文章关联的分类
        Long categoryId = publishArticleReqVO.getCategoryId();

        // 3.1 校验提交的分类是否真实存在
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }

        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        // 4. 保存文章关联的标签集合
        List<String> publishTags = publishArticleReqVO.getTags();
        insertTags(publishTags);

        return Response.success();
    }

    /**
     * 保存标签
     * @param publishTags
     */
    private void insertTags(List<String> publishTags) {
        // TODO
    }
}
