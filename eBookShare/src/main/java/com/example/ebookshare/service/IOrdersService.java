package com.example.ebookshare.service;

import com.example.ebookshare.common.Result;
import com.example.ebookshare.entity.Cart;
import com.example.ebookshare.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
public interface IOrdersService extends IService<Orders> {
    void addOrder(List<Cart> carts, Map<String, Object> form);

    Result updateOrder(Orders orders);
}
