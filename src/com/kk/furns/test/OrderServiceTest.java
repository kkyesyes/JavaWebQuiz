package src.com.kk.furns.test;

import org.junit.Test;
import src.com.kk.furns.dao.OrderItemDAO;
import src.com.kk.furns.dao.impl.OrderItemDAOImpl;
import src.com.kk.furns.entity.Cart;
import src.com.kk.furns.entity.CartItem;
import src.com.kk.furns.service.OrderService;
import src.com.kk.furns.service.impl.OrderServiceImpl;

import java.math.BigDecimal;

/**
 * @author KK
 * @version 1.0
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void saveOrder() {
        Cart cart = new Cart();
        cart.add(new CartItem(35, "正品丝绸", new BigDecimal("234.10"),
                1, new BigDecimal("234.10"),
                "https://cdn.pixabay.com/photo/2017/08/02/11/53/weaving-loom-2571179_640.jpg"));
        cart.add(new CartItem(37, "套娃不倒翁", new BigDecimal("69.02"),
                1, new BigDecimal("69.02"),
                "https://imgservice.suning.cn/uimg1/b2c/image/3iXL_PJQTyD_dPNytmaF8Q.jpg"));
        System.out.println(orderService.saveOrder(1, cart));
    }
}
