package src.com.kk.furns.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 韩顺平
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;
    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();

    // 在静态代码块完成 ds 的初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtilsByDruid.class.getClassLoader()
                    .getResourceAsStream("src\\druid.properties"));
//            properties.load(new FileInputStream("src\\druid.properties")); // 工作目录不含 src
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 编写 getConnection 方法（使用 ThreadLocal）
    public static Connection getConnection() {
        Connection connection = threadLocalConn.get();
        if (null == connection) {
            try {
                connection = ds.getConnection();
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocalConn.set(connection);
        }
        return connection;
    }

    // 提交事务
    public static void commit() {
        Connection connection = threadLocalConn.get();
        if (null == connection) {
            return;
        }

        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        threadLocalConn.remove();
    }

    // 回滚事务
    public static void rollback() {
        Connection connection = threadLocalConn.get();
        if (null == connection) {
            return;
        }

        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        threadLocalConn.remove();
    }

    // 关闭连接（在数据库连接池技术中，close 不是真的断掉连接而是把使用的Connection对象放回连接池）
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
