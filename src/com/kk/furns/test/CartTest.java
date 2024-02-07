package src.com.kk.furns.test;

import org.junit.Test;
import src.com.kk.furns.entity.Cart;
import src.com.kk.furns.entity.CartItem;

import java.math.BigDecimal;

/**
 * @author KK
 * @version 1.0
 */
public class CartTest {
    Cart cart = new Cart();

    @Test
    public void cartAddTest() {
        CartItem cartItem = new CartItem(34, "木桌", new BigDecimal("35.5"), 1, new BigDecimal("35.5"), "");

        cart.add(cartItem);
        cart.add(cartItem);
        System.out.println(cart);
    }

}
