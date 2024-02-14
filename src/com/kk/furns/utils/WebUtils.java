package src.com.kk.furns.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

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

    /**
     * 返回 /2024/11/11 字符串
     * @return 日期目录字符串
     */
    public static String getYearMonthDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        return "/" + year + "/" + month + "/" + day;
    }
}
