package src.com.kk.furns.filter;

import com.google.gson.Gson;
import src.com.kk.furns.entity.Member;
import src.com.kk.furns.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author KK
 * @version 1.0
 */
public class MemberFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Member member = (Member) req.getSession().getAttribute("member");
        if (null == member) {
            if (WebUtils.isAjaxRequest(req)) {
                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("url", "views/member/login.jsp");
                String resultJson = new Gson().toJson(resultMap);
                servletResponse.getWriter().write(resultJson);
                return;
            }
            req.getRequestDispatcher("/views/member/login.jsp").forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
