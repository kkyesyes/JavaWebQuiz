package src.com.kk.furns.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author KK
 * @version 1.0
 */
public class WebUtils {
    /**
     * 判断请求是否为 Ajax 请求
     * @param req
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest req) {
        return ("XMLHttpRequest".equals(req.getHeader("X-Requested-With")));
    }
}
