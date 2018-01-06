package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBC {

    private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";// 数据库驱动
    private static String URL = "jdbc:mysql://localhost:3306/db_datawarehouse?characterEncoding=utf8&useSSL=true";// 数据库URL
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();// 用来保存数据库连接
    private static Connection conn = null;// 数据库连接
    private static String user="";
    private static String password="";
    public static void setURL(String port,String dbName)
    {
        URL = "jdbc:mysql://192.168.1.100:"+port+"/"+dbName+"?characterEncoding=utf8&useSSL=true";
    }
    public static void setUserAndPassword(String usr,String pw)
    {
        user=usr;
        password=pw;
    }
    static {

        try {
            Class.forName(DRIVERCLASS); // 加载数据库驱动

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static Connection getConnection() { // 创建数据库连接的方法

        conn = (Connection) threadLocal.get(); // 从线程中获得数据库连接

        if (conn == null) { // 没有可用的数据库连接

            try {
                conn = DriverManager.getConnection(URL,user,password);// 创建新的数据库连接

                threadLocal.set(conn); // 将数据库连接保存到线程中

            } catch (Exception e) {
                System.exit(0);// 关闭系统

                e.printStackTrace();
            }
        }
        return conn;
    }

    protected static boolean closeConnection() { // 关闭数据库连接的方法

        boolean isClosed = true; // 默认关闭成功

        conn = (Connection) threadLocal.get(); // 从线程中获得数据库连接

        threadLocal.set(null); // 清空线程中的数据库连接

        if (conn != null) { // 数据库连接可用

            try {
                conn.close(); // 关闭数据库连接

            } catch (SQLException e) {
                isClosed = false; // 关闭失败

                e.printStackTrace();
            }
        }
        return isClosed;
    }
}
