package com.zry.weblog.admin.model.vo.category;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除分类请求 VO")
public class DeleteCategoryReqVO {
    @NotNull(message = "分类ID不能为空")
    private Long id; // 分类ID
}
