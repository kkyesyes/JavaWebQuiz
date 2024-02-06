package src.com.kk.furns.web;

import com.sun.net.httpserver.HttpServer;
import src.com.kk.furns.entity.Member;
import src.com.kk.furns.service.MemberService;
import src.com.kk.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author KK
 * @version 1.0
 */
public class MemberServlet extends BasicServlet {
    private MemberService memberService = new MemberServiceImpl();

    /**
     * 用户登录服务
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        if (memberService.userLogin(member)) {
            HttpSession session = req.getSession();
            session.setAttribute("member", member);
            req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
        }
    }

    /**
     * 用户注销服务
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }



    /**
     * 用户注册服务
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Member member = new Member(null, username, password, email);
        if (memberService.userRegister(member)) {
            // 注册成功
            req.getRequestDispatcher("/views/member/register_ok.html")
                    .forward(req, resp);
        } else {
            // 注册失败
            req.getRequestDispatcher("/views/member/register_fail.html")
                    .forward(req, resp);
        }
    }
}
