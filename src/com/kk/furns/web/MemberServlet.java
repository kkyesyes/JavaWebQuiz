package src.com.kk.furns.web;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import src.com.kk.furns.entity.Member;
import src.com.kk.furns.service.MemberService;
import src.com.kk.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author KK
 * @version 1.0
 */
public class MemberServlet extends BasicServlet {
    private MemberService memberService = new MemberServiceImpl();

    /**
     * 查询是否存在会员
     */
    public void isExistsMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean isExists = memberService.isExistsUsername(username);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("isExists", isExists);
        String resultJson = new Gson().toJson(resultMap);
        resp.getWriter().write(resultJson);
    }

    /**
     * 用户登录服务
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        member = memberService.userLogin(member);
        if (null != member) {
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
        String checkCode = req.getParameter("checkCode");
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        req.setAttribute("active", "register");
        req.setAttribute("username", username);
        req.setAttribute("email", email);
        if (null == checkCode || "".equals(checkCode)) {
            // 注册失败
            req.setAttribute("msg", "验证码不可为空！");
            req.getRequestDispatcher("/views/member/login.jsp")
                    .forward(req, resp);
            return;
        }
        if (!token.equalsIgnoreCase(checkCode)) {
            // 注册失败
            req.setAttribute("msg", "非法验证码提交！");
            req.getRequestDispatcher("/views/member/login.jsp")
                    .forward(req, resp);
            return;
        }
        Member member = new Member(null, username, password, email);
        if (memberService.userRegister(member)) {
            // 注册成功
            req.getRequestDispatcher("/views/member/register_ok.jsp")
                    .forward(req, resp);
        } else {
            // 注册失败
            req.getRequestDispatcher("/views/member/register_fail.jsp")
                    .forward(req, resp);
        }
    }
}
