package src.com.kk.furns.dao;

import src.com.kk.furns.entity.Member;
import src.com.kk.furns.entity.Order;
import src.com.kk.furns.entity.OrderItem;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public interface OrderDAO {
    // 保存订单
    int saveOrder(Order order);

    // 根据 memberId 查询订单
    List<Order> queryOrderByMemberId(int id);

    // 查询订单 orderId 查询订单
    Order queryOrderByOrderId(String id);
}
