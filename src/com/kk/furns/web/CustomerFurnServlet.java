package src.com.kk.furns.web;

import src.com.kk.furns.entity.Furn;
import src.com.kk.furns.entity.Page;
import src.com.kk.furns.service.FurnService;
import src.com.kk.furns.service.impl.FurnServiceImpl;
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
public class CustomerFurnServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();


    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.CUSTOMER_PAGE_SIZE);
        Page<Furn> page = furnService.page(pageNo, pageSize);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);
    }

}
