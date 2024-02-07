package src.com.kk.furns.web;

import jdk.nashorn.internal.ir.CallNode;
import src.com.kk.furns.entity.Cart;
import src.com.kk.furns.entity.CartItem;
import src.com.kk.furns.entity.Furn;
import src.com.kk.furns.service.FurnService;
import src.com.kk.furns.service.impl.FurnServiceImpl;
import src.com.kk.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author KK
 * @version 1.0
 */
public class CartServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();
    public void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        // todo: 异常处理改进
        if (null == furn) {
            return;
        }
        CartItem item = new CartItem(furn.getId(), furn.getName(), furn.getPrice(), 1, furn.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.add(item);
        System.out.println(cart);
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
}
