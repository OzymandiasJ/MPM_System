package com.ozy.myssm.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * todo
 * 利用线程本地变量,存储连接信息! 确保一个线程的多个方法可以获取同一个connection!
 * 优势: 事务操作的时候 service 和 dao 属于同一个线程,不同再传递参数了!
 * 大家都可以调用getConnection 自动获取的是相同的连接池!
 */
public class jdbcutils {
    private static DataSource dataSource=null;
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();
    static {
        //初始化连接池对象
//        1,读取外部配置那文件 Properties
        Properties properties=new Properties();
        //src 下的文件，可以使用类加载器提供的方法,注意是当前类的同级目录，如果是需要子目录加上路径名即可
        InputStream ips= jdbcutils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        2,使用连接池的工其类的工程模式，创建连接池
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection connection=threadLocal.get();
        int numActive = ((DruidDataSource) dataSource).getActiveCount();
        System.out.println("当前连接池中已分配数量：" + numActive);
        if(connection==null){
            //if null ,create connection
            Connection connection1= dataSource.getConnection();
            return connection1;
        }
        return connection;
    }
    public static void freeConnection() throws SQLException {
        Connection connection=threadLocal.get();
        if(connection!=null){//如果没有使用事务
            threadLocal.remove();//clean
            connection.setAutoCommit(true);//事务状态回归
            connection.close();
        }
    }
}
