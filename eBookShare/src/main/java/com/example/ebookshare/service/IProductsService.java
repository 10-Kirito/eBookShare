package com.example.ebookshare.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
public interface IProductsService extends IService<Products> {
    Page<Products> findPage(Page<Products> page, String name);
}
