package com.zry.weblog.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectRspVO {
    // 下拉列表的标签
    private String label;
    // 下拉列表的值，比如唯一id
    private Object value;
}
