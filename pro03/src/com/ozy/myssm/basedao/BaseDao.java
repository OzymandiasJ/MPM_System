package com.ozy.myssm.basedao;

import com.ozy.myssm.utils.jdbcutils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<P> {
    /**
     * @param sql 带占位符的sql语句
     * @param params 占位符列表
     * @return 影响的行数
     */
    //封装非DQL操作
    public int executeUpdate(String sql,Object...params) throws SQLException {
        //获取连接
        Connection connection= jdbcutils.getConnection();
        //占位符赋值
        PreparedStatement statement=connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i+1,params[i]);
        }
        //发送sql语句
        int rows=statement.executeUpdate();
        statement.close();
        //对于是否关闭连接，需要考虑当前业务是否是事务型的
        if(connection.getAutoCommit()==true){//非事务型的，需要关闭
            jdbcutils.freeConnection();
        }
        else {//开启事务了事务，不需要关闭
        }
        return rows;
    }
//    封装DQL操作
// 声明一个泛型，不确定类型
// 1.确定泛型 User.class T = User
// 2.要使用反射技术属性赋值
//    将结果封装到实体类集合

    /**
     *
     * @param clazz 实体类
     * @param sql 语句，要求列名必须等于实体类属性名，而且记得实体类这些属性设置了getter和setter方法
     * @param params 占位符值
     * @return 返回的实体类集合
     * @param <T> 生命的结果的反省
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */

    public <T> List<T> executeQuery(Class<T> clazz , String sql, Object...params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //获取连接
        Connection connection= jdbcutils.getConnection();
        //占位符赋值
        PreparedStatement statement=connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i+1,params[i]);
        }
        //发送sql语句
        ResultSet resultSet = statement.executeQuery();
        List<T> list=new ArrayList<>();
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        int columnCount=resultSetMetaData.getColumnCount();
        while(resultSet.next()){
            T t=clazz.newInstance();
            for (int i = 0; i <columnCount ; i++) {
                Object value=resultSet.getObject(i+1);
                //获取指定列下表的名称
                String propertyName=resultSetMetaData.getColumnLabel(i+1);
                //反射，给对象属性值赋值
                Field field=clazz.getDeclaredField(propertyName);
                field.setAccessible(true);//属性可以设置，打破原有private修饰

                /**
                 * 参数1，要赋值的对象。如果是静态对象即T，那么对象可以为空
                 * 参数2，具体的属性值
                 */
                field.set(t,value);
            }
            list.add(t);
        }

        //结束，关闭
        resultSet.close();
        statement.close();
        //对于是否关闭连接，需要考虑当前业务是否是事务型的
        if(connection.getAutoCommit()==true){//非事务型的，需要关闭
            jdbcutils.freeConnection();
        }
        else {//开启事务了事务，不需要关闭
        }
        return list;
    }
}
