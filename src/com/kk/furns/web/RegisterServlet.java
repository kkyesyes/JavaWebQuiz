package src.com.kk.furns.web;

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
public class RegisterServlet extends HttpServlet {
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
