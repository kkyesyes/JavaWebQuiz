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
            System.out.println("用户注册服务执行成功");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
