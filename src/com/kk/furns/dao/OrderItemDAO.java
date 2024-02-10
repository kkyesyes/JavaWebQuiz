package src.com.kk.furns.dao;

import src.com.kk.furns.entity.OrderItem;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public interface OrderItemDAO {
    // 保存订单项
    int saveOrderItem(OrderItem orderItem);

    // 查询订单详情
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
