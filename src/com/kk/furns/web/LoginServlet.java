package src.com.kk.furns.web;

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
public class LoginServlet extends HttpServlet {
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (memberService.userLogin(username, password)) {
            req.getRequestDispatcher("/views/member/login_ok.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/member/login.html").forward(req, resp);
        }
    }
}
