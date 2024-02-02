package src.com.kk.furns.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author KK
 * @version 1.0
 */
public abstract class BasicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Method declaredMethod = this.getClass()
                    .getDeclaredMethod(action,
                            HttpServletRequest.class,
                            HttpServletResponse.class);
            System.out.println("declaredMethod: " + declaredMethod);
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
