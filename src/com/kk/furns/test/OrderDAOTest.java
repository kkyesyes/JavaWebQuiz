package src.com.kk.furns.test;

import org.junit.Test;
import src.com.kk.furns.dao.OrderDAO;
import src.com.kk.furns.dao.impl.OrderDAOImpl;
import src.com.kk.furns.entity.Order;
import src.com.kk.furns.entity.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class OrderDAOTest {
    private OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void saveOrderTest() {
        Order order = new Order(null, 1, new BigDecimal(5), "发货中");
        orderDAO.saveOrder(order);
    }

    @Test
    public void saveOrderItemTest() {
        OrderItem orderItem = new OrderItem(null, "1665488", 4, new BigDecimal(55), new BigDecimal(544), "1");
//        orderDAO.saveOrderItem(orderItem);
    }

    @Test
    public void queryOrderByMemberIdTest() {
        List<Order> orders = orderDAO.queryOrderByMemberId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
