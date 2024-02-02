package src.com.kk.furns.web;

import src.com.kk.furns.entity.Admin;
import src.com.kk.furns.service.AdminService;
import src.com.kk.furns.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author KK
 * @version 1.0
 */
public class AdminServlet extends BasicServlet {
    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * 管理员登录服务
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Admin admin = new Admin(null, username, password, null);
        if (adminService.adminLogin(admin)) {
            req.getRequestDispatcher("/views/admin/manage_menu.html").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/views/admin/manage_login.jsp").forward(req, resp);
        }
    }
}
