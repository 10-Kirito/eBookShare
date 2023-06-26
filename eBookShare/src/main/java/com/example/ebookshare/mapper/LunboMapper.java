package com.example.ebookshare.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Lunbo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 前台轮播图 Mapper 接口
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
@Mapper
public interface LunboMapper extends BaseMapper<Lunbo> {
    Page<Lunbo> findPage(Page<Lunbo> page, String name);
}
