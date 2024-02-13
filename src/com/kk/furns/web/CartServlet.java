package src.com.kk.furns.web;

import com.google.gson.Gson;
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
import java.util.HashMap;

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
        CartItem item = new CartItem(furn.getId(), furn.getName(), furn.getPrice(), 1, furn.getPrice(), furn.getPicture());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        if (cart.sizeOf(id) >= furn.getInventory()) {
            resp.sendRedirect(req.getHeader("Referer"));
            return;
        }
        cart.add(item);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void addItemByAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        // todo: 异常处理改进
        if (null == furn) {
            return;
        }
        CartItem item = new CartItem(furn.getId(), furn.getName(), furn.getPrice(), 1, furn.getPrice(), furn.getPicture());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        if (cart.sizeOf(id) >= furn.getInventory()) {
            resp.sendRedirect(req.getHeader("Referer"));
            return;
        }
        cart.add(item);
        Integer cartItemCount = cart.getTotalCount();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("cartItemCount", cartItemCount);
        String resultJson = new Gson().toJson(resultMap);
        resp.getWriter().write(resultJson);
    }

    public void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // todo: 异常处理改进
        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        if (null == cart) {
//            return;
//        }
        cart.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // todo: 异常处理改进
//        if (null == cart) {
//            return;
//        }
        cart.deleteItem(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void deleteOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // todo: 异常处理改进
//        if (null == cart) {
//            return;
//        }
        cart.deleteOne(id);
        resp.sendRedirect(req.getHeader("Referer"));}

    public void addOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // todo: 异常处理改进
//        if (null == cart) {
//            return;
//        }
        if (cart.sizeOf(id) >= furnService.queryFurnById(id).getInventory()) {
            return;
        }
        cart.addOne(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/views/cart/cart.jsp");
    }
}
