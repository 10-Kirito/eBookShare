package com.example.ebookshare.service.impl;

import com.example.ebookshare.entity.Cart;
import com.example.ebookshare.mapper.CartMapper;
import com.example.ebookshare.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

}
