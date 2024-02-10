package src.com.kk.furns.web;

import src.com.kk.furns.entity.Cart;
import src.com.kk.furns.entity.Member;
import src.com.kk.furns.entity.Order;
import src.com.kk.furns.entity.OrderItem;
import src.com.kk.furns.service.OrderService;
import src.com.kk.furns.service.impl.OrderServiceImpl;
import src.com.kk.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class OrderServlet extends BasicServlet {
    private OrderService orderService = new OrderServiceImpl();

    public void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = (Member) req.getSession().getAttribute("member");
        if (null == member) {
            req.getRequestDispatcher("views/member/login.jsp").forward(req, resp);
            return;
        }
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart || cart.isEmpty()) {
            req.getRequestDispatcher("cartServlet?action=showCart").forward(req, resp);
            return;
        }
        String orderId = orderService.saveOrder(member.getId(), cart);
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/views/cart/checkout.jsp");
    }

    public void showOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = (Member) req.getSession().getAttribute("member");
        List<Order> orders = orderService.queryOrderByMemberId(member.getId());
        req.setAttribute("orders", orders);
        String referer = req.getHeader("Referer");
        req.setAttribute("referer", referer);
        req.getRequestDispatcher("views/order/order.jsp").forward(req, resp);
    }

    public void showOrderItemsByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        Order order = orderService.queryOrderByOrderId(orderId);
        req.getSession().setAttribute("order", order);
        List<OrderItem> orderItems = orderService.queryOrderItemsByOrderId(orderId);
        req.getSession().setAttribute("orderItems", orderItems);
        int orderItemsCount = 0;
        for (OrderItem orderItem : orderItems) {
            orderItemsCount += orderItem.getCount();
        }
        req.setAttribute("orderItemsCount", orderItemsCount);
        req.getRequestDispatcher("views/order/order_detail.jsp").forward(req, resp);
    }
}
