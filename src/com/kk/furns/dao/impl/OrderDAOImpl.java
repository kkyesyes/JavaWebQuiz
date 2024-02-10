package src.com.kk.furns.dao.impl;

import src.com.kk.furns.dao.BasicDAO;
import src.com.kk.furns.dao.OrderDAO;
import src.com.kk.furns.entity.Order;
import src.com.kk.furns.entity.OrderItem;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    /**
     * 保存一个 order
     * @param order 传入的 order
     * @return 返回受影响的行数
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into `order`(`id`, `member_id`, `price`, `status`, `datetime`)" +
                "values(?, ?, ?, ?, ?)";
        return update(sql, order.getId(), order.getMemberId(), order.getPrice(), order.getStatus(), order.getDatetime());
    }

    @Override
    public List<Order> queryOrderByMemberId(int memberId) {
        String sql = "select `id`, `member_id` as memberId, " +
                "`price`, `status`, `datetime` from `order` where `member_id`=?";
        return queryMulti(sql, Order.class, memberId);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        String sql = "select `id`, `member_id` as memberId, " +
                "`price`, `status`, `datetime` from `order` where `id`=?";
        return querySingle(sql, Order.class, orderId);
    }
}
