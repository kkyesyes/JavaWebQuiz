package src.com.kk.furns.web;

import com.google.gson.Gson;
import src.com.kk.furns.entity.Admin;
import src.com.kk.furns.service.AdminService;
import src.com.kk.furns.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author KK
 * @version 1.0
 */
public class AdminServlet extends BasicServlet {
    private AdminService adminService = new AdminServiceImpl();

    /**
     * 根据用户名判断管理员是否存在
     */
    public void isExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean isExists = adminService.isExistsUsername(username);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("isExists", isExists);
        String resultJson = new Gson().toJson(resultMap);
        resp.getWriter().write(resultJson);
    }

    /**
     * 管理员登录服务
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Admin admin = new Admin(null, username, password, null);
        Admin resAdmin = adminService.adminLogin(admin);
        if (null == resAdmin) {
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/views/admin/manage_login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("admin", resAdmin);
            req.getRequestDispatcher("/views/admin/manage_menu.jsp").forward(req, resp);
        }
    }
}
