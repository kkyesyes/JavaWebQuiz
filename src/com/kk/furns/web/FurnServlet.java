package src.com.kk.furns.web;

import src.com.kk.furns.entity.Furn;
import src.com.kk.furns.service.FurnService;
import src.com.kk.furns.service.impl.FurnServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author KK
 * @version 1.0
 */
public class FurnServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();

    /**
     * 列出所有家居服务
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Furn> furns = furnService.list();
        req.setAttribute("furns", furns);
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 添加
        String name = req.getParameter("name");
        String maker = req.getParameter("maker");
        try {
            int price = Integer.parseInt(req.getParameter("price"));
            int sales = Integer.parseInt(req.getParameter("sales"));
            int inventory = Integer.parseInt(req.getParameter("inventory"));
        } catch (Exception e) {
            req.setAttribute("msg", "数据格式异常！请重新检查");
            req.setAttribute("name", name);
            req.setAttribute("maker", maker);
            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
            return;
        }
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        Integer sales = new Integer(req.getParameter("sales"));
        Integer inventory = new Integer(req.getParameter("inventory"));
        if ("".equals(name) || "".equals(maker)) {
            req.setAttribute("msg", "家居名或厂商不可为空！");
            req.setAttribute("name", name);
            req.setAttribute("maker", maker);
            req.setAttribute("price", price);
            req.setAttribute("sales", sales);
            req.setAttribute("inventory", inventory);
            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
            return;
        }
        Furn furn = new Furn(null, name, maker, price, sales, inventory, "https://img95.699pic.com/photo/60005/6018.jpg_wh860.jpg");
        System.out.println(furn);
        furnService.add(furn);

        // 转发列表
        resp.sendRedirect(req.getContextPath() + "/manage/furnServlet?action=list");
    }
}
