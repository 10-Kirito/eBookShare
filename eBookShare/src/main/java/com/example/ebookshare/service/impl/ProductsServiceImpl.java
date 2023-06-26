package com.example.ebookshare.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ebookshare.entity.Products;
import com.example.ebookshare.mapper.ProductsMapper;
import com.example.ebookshare.service.IProductsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements IProductsService {
    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public Page<Products> findPage(Page<Products> page, String name) {
        return productsMapper.findPage(page,name);
    }
}
