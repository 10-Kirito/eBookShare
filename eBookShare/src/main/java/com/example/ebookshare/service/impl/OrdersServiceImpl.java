package com.example.ebookshare.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ebookshare.common.Result;
import com.example.ebookshare.controller.enums.OrderStatusEnum;
import com.example.ebookshare.entity.*;
import com.example.ebookshare.exception.ServiceException;
import com.example.ebookshare.mapper.OrdersMapper;
import com.example.ebookshare.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhc
 * @since 2023-06-26
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    @Autowired
    private IOrderdetailsService orderdetailsService;
    @Resource
    private IProductsService productsService;
    @Autowired
    private ICartService cartService;

    @Autowired
    private IUsersService usersService;

    @Override
    public void addOrder(List<Cart> carts, Map<String, Object> form) {
        BigDecimal total=new BigDecimal(0);
        Orders orders = new Orders();
        orders.setOrderId(IdUtil.fastSimpleUUID());//设置订单Id
        orders.setMemberId(carts.get(0).getMemberId());           //更改
        orders.setStatus(OrderStatusEnum.NEED_PAY.getCode());     //设置待支付状态
        orders.setContactName((String) form.get("contactName"));
        orders.setContactPhoneno((String) form.get("contactPhoneNo"));
        orders.setContactAddress((String) form.get("contactAddress"));
        for (Cart cart:carts){
            total=total.add(cart.getPrice().multiply(BigDecimal.valueOf(cart.getNum())));

            //下单成功，扣库存
            Products products = productsService.getById(cart.getProductId());
            if(products.getNums()< cart.getNum()){
                throw new ServiceException("-1","库存不足");
            }
            products.setNums(products.getNums()- cart.getNum());
            productsService.updateById(products);

            //设置订单详情信息
            Orderdetails orderdetails = new Orderdetails();
            orderdetails.setOrderId(orders.getOrderId());
            orderdetails.setImage(cart.getImage());
            orderdetails.setAmount(cart.getNum());
            orderdetails.setProductId(cart.getProductId());
            orderdetails.setPrice(cart.getPrice());
            orderdetailsService.save(orderdetails);

            //下单成功后删除购物车商品
            cartService.removeById(cart.getId());
        }
        orders.setMoney(total);//计算总价格
        save(orders);   //保存订单


    }

    @Override
    public Result updateOrder(Orders orders) {
        if (Objects.equals(orders.getStatus(), OrderStatusEnum.CANCEL.getCode())){
            //取消订单，加库存
            String orderId = orders.getOrderId();
            List<Orderdetails> orderdetails = orderdetailsService.list(new QueryWrapper<Orderdetails>().eq("order_id", orderId));
            for (Orderdetails orderdetail:orderdetails){
                Products products = productsService.getById(orderdetail.getProductId());
                products.setNums(products.getNums()+ orderdetail.getAmount());
                productsService.updateById(products);
            }
        }
        if (Objects.equals(orders.getStatus(), OrderStatusEnum.NEED_SEND.getCode())){
            Users users=usersService.getById(orders.getMemberId());
            if (users.getPoints()-orders.getMoney().intValue()<=0){
                return Result.error("600","积分不足，兑换失败");
            }

            //扣积分
            users.setPoints(users.getPoints()-orders.getMoney().intValue());
            usersService.updateById(users);

            //加入付款时间和付款编号
            orders.setPayTime(LocalDateTime.now());
            orders.setPayno( RandomStringUtils.randomAlphanumeric(11));
        }
        updateById(orders);
        return Result.success();
    }


}
