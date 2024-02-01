package src.com.kk.furns.test;

import org.junit.Test;
import src.com.kk.furns.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author KK
 * @version 1.0
 */
public class JDBCUtilsByDruidTest {
    @Test
    public void getConnection() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println("connection: " + connection);
        JDBCUtilsByDruid.close(null, null, connection);
    }
}
