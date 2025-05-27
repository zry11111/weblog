package com.zry.weblog.admin.model.vo.category;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "添加分类 VO")
public class AddCategoryReqVO {

    @NotBlank(message = "分类名称不能为空")
//    @Length注解要使用Hibernate的validate依赖
    @Size(min = 1, max = 10, message = "分类名称字数限制 1 ~ 10 之间")
    private String name;

}
