package src.com.kk.furns.test;

import org.junit.Test;
import src.com.kk.furns.dao.OrderItemDAO;
import src.com.kk.furns.dao.impl.OrderItemDAOImpl;
import src.com.kk.furns.entity.OrderItem;

import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class OrderItemDAOTest {
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Test
    public void queryOrderItemsByOrderId() {
        List<OrderItem> orderItems = orderItemDAO.queryOrderItemsByOrderId("1707443032431");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}
