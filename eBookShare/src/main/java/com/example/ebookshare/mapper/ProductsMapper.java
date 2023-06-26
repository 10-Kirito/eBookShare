package com.example.ebookshare.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Products;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
@Mapper
public interface ProductsMapper extends BaseMapper<Products> {
    Page<Products> findPage(Page<Products> page, @Param("name") String name);
}
