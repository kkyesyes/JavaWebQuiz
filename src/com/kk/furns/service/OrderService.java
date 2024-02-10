package src.com.kk.furns.service;

import src.com.kk.furns.entity.Cart;
import src.com.kk.furns.entity.Order;
import src.com.kk.furns.entity.OrderItem;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public interface OrderService {
    // 保存订单
    String saveOrder(int memberId, Cart cart);

    // 查询订单
    List<Order> queryOrderByMemberId(int memberId);

    // 查询订单详情
    List<OrderItem> queryOrderItemsByOrderId(String orderId);

    Order queryOrderByOrderId(String orderId);
}
