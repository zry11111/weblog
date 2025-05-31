package com.zry.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zry.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.zry.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.zry.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.zry.weblog.admin.model.vo.category.FindCategoryPageListRspVO;
import com.zry.weblog.admin.service.AdminCategoryService;
import com.zry.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.zry.weblog.common.domain.dos.CategoryDO;
import com.zry.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.zry.weblog.common.domain.mapper.CategoryMapper;
import com.zry.weblog.common.enums.ResponseCodeEnum;
import com.zry.weblog.common.exception.BizException;
import com.zry.weblog.common.model.vo.SelectRspVO;
import com.zry.weblog.common.utils.PageResponse;
import com.zry.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminCategoryServiceImpl implements AdminCategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Override
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String name = addCategoryReqVO.getName();
        CategoryDO categoryDO = categoryMapper.selectByName(name);
        if(Objects.nonNull(categoryDO)){
            log.warn("分类名称已存在: {}", name);
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }
        CategoryDO newCategory = CategoryDO.builder()
                .name(name.trim())
                .build();
        categoryMapper.insert(newCategory);
        return Response.success();
    }

    @Override
    public PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();
        Page<CategoryDO> page = new Page<>(current,size);
        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();

        wrapper
                .like(StringUtils.isNotBlank(name), CategoryDO::getName, name.trim()) // like 模块查询
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate) // 大于等于 startDate
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)  // 小于等于 endDate
                .orderByDesc(CategoryDO::getCreateTime); // 按创建时间倒叙

        // 执行分页查询
        Page<CategoryDO> categoryDOPage = categoryMapper.selectPage(page, wrapper);

        List<CategoryDO> categoryDOS = categoryDOPage.getRecords();
        // 将DO 转 VO
        List<FindCategoryPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream()
                    .map(categoryDO -> FindCategoryPageListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .createTime(categoryDO.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }
        return PageResponse.success(categoryDOPage,vos);
    }

    @Override
    public Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {
        Long categoryId = deleteCategoryReqVO.getId();
        // 校验该分类下是否已经有文章，若有，则提示需要先删除分类下所有文章，才能删除
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectOneByCategoryId(categoryId);

        if (Objects.nonNull(articleCategoryRelDO)) {
            log.warn("==> 此分类下包含文章，无法删除，categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_CAN_NOT_DELETE);
        }
        categoryMapper.deleteById(categoryId);
        return Response.success();
    }

    @Override
    public Response<List<SelectRspVO>> findCategorySelectList() {
        List<CategoryDO> categoryDOS = categoryMapper.selectList(null);
        // 将DO 转 VO
        List<SelectRspVO> selectRspVOS = null;
        if(!CollectionUtils.isEmpty(categoryDOS)) {
            selectRspVOS = categoryDOS.stream()
                    .map(categoryDO -> SelectRspVO.builder()
                            .value(categoryDO.getId())
                            .label(categoryDO.getName())
                            .build())
                    // 将流中的元素收集到一个新的List集合中
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspVOS);
    }
}
