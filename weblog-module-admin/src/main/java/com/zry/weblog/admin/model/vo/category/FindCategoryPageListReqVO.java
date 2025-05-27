package com.zry.weblog.admin.model.vo.category;

import com.zry.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询分类分页数据入参 VO")//vo，请求参数；dto，传输数据
public class FindCategoryPageListReqVO extends BasePageQuery {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

}
