package com.ozy.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();

    private static String URL="jdbc:mysql://localhost:3306/demo1?useUnicode=true&characterEncoding=UTF-8";
    private static String USER="root";
    private static String PW="320320";
    public static Connection createConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");//运行时类
        Connection connection= DriverManager.getConnection(URL,USER,PW);
        return connection;
    }
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Connection conn=threadLocal.get();
        if(conn==null){
            conn= createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }
    public static void closeConn() throws SQLException {
        Connection conn=threadLocal.get();
        if(conn==null){
        }
        if(!conn.isClosed()){
            conn.close();
        }
//        threadLocal.set(null);
        threadLocal.remove();//皆可
    }
}
