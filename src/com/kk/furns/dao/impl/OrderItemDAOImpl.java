package src.com.kk.furns.dao.impl;

import src.com.kk.furns.dao.BasicDAO;
import src.com.kk.furns.dao.OrderItemDAO;
import src.com.kk.furns.entity.OrderItem;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into `order_item`(`id`, `name`, `count`, `price`, `total_price`, `order_id`)" +
                "values(null, ?, ?, ?, ?, ?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `id`, `name`, `count`, `price`, `total_price` as totalPrice, `order_id` as orderId " +
                "from `order_item` where `order_id`=?";
        return queryMulti(sql, OrderItem.class, orderId);
    }
}
