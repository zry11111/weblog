package com.zry.weblog.admin.controller;

import com.zry.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.zry.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.zry.weblog.admin.service.AdminCategoryService;
import com.zry.weblog.common.aspect.ApiOperationLog;
import com.zry.weblog.common.utils.PageResponse;
import com.zry.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "后台管理-分类管理")
public class AdminCategoryController {
    @Autowired
    private AdminCategoryService categoryService;
    @PostMapping("/category/add")
    @ApiOperation(value = "添加分类")
    @ApiOperationLog(description = "添加分类")
    public Response addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return categoryService.addCategory(addCategoryReqVO);
    }
    @PostMapping("/category/list")
    @ApiOperation(value = "分类分页数据获取")
    @ApiOperationLog(description = "分类分页数据获取")
    public PageResponse findCategoryList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO) {
        return categoryService.findCategoryList(findCategoryPageListReqVO);
    }
}
