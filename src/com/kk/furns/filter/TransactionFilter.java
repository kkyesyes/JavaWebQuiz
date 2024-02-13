package src.com.kk.furns.filter;

import src.com.kk.furns.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import java.io.IOException;

/**
 * 管理数据库事务的过滤器
 * @author KK
 * @version 1.0
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtilsByDruid.commit();
        } catch (Exception e) {
            JDBCUtilsByDruid.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
