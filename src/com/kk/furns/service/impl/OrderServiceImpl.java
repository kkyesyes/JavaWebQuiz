package src.com.kk.furns.service.impl;

import src.com.kk.furns.dao.FurnDAO;
import src.com.kk.furns.dao.OrderDAO;
import src.com.kk.furns.dao.OrderItemDAO;
import src.com.kk.furns.dao.impl.FurnDAOImpl;
import src.com.kk.furns.dao.impl.OrderDAOImpl;
import src.com.kk.furns.dao.impl.OrderItemDAOImpl;
import src.com.kk.furns.entity.*;
import src.com.kk.furns.service.OrderService;

import java.util.HashMap;
import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private FurnDAO furnDAO = new FurnDAOImpl();

    /**
     * 根据传入的购物车保存订单
     * @param cart 传入的购物车
     * @return 返回受影响的行数
     */
    @Override
    public String saveOrder(int memberId, Cart cart) {
        // todo: 事务处理
        String orderId = System.currentTimeMillis() + memberId + "";
        Order order = new Order(orderId, memberId, cart.getTotalPrice(), Order.NOT_SEND);
        orderDAO.saveOrder(order);
        HashMap<Integer, CartItem> items = cart.getItems();
        for (Integer id : items.keySet()) {
            CartItem item = items.get(id);
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getCount(), item.getPrice(),
                    item.getTotalPrice(), orderId);
            orderItemDAO.saveOrderItem(orderItem);
            Furn furn = furnDAO.queryFurnById(id);
            furn.setSales(furn.getSales() + item.getCount());
            furn.setInventory(furn.getInventory() - item.getCount());
            furnDAO.updateFurn(furn);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> queryOrderByMemberId(int memberId) {
        return orderDAO.queryOrderByMemberId(memberId);

    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        return orderItemDAO.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        return orderDAO.queryOrderByOrderId(orderId);
    }
}
