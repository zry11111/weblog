package com.zry.weblog.admin.model.vo.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArticleDetailRspVO {
    private Long id;
    private String title;
    private String cover;
    private String content;
    private Long categoryId;
    private List<Long> tagIds;

    /**
     * 摘要
     */
    private String summary;

}
