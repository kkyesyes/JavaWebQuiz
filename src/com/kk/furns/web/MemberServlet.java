package src.com.kk.furns.web;

import com.sun.net.httpserver.HttpServer;
import src.com.kk.furns.entity.Member;
import src.com.kk.furns.service.MemberService;
import src.com.kk.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author KK
 * @version 1.0
 */
public class MemberServlet extends HttpServlet {
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            login(req, resp);
        } else if ("register".equals(action)) {
            register(req, resp);
        } else {
            System.out.println("Action 参数不正确");
        }
    }

    /**
     * 用户登录服务
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        if (memberService.userLogin(member)) {
            req.getRequestDispatcher("/views/member/login_ok.html").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
        }
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
