package com.zry.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zry.weblog.common.domain.dos.CategoryDO;

public interface CategoryMapper extends BaseMapper<CategoryDO> {
    default CategoryDO selectByName(String categoryName) {
        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);
        // 执行查询
        return selectOne(wrapper);
    }
}
