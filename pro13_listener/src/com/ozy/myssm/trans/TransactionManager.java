package com.ozy.myssm.trans;

import com.ozy.myssm.basedao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
//    开启事务
    public static void beginTrans() throws SQLException, ClassNotFoundException {

        ConnUtil.getConn().setAutoCommit(false);
    }
//    提交事务
    public static void commit() throws SQLException, ClassNotFoundException {
        Connection conn=ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();
    }
//    回滚事务
    public static void rollback() throws SQLException, ClassNotFoundException {
        Connection conn=ConnUtil.getConn();
        conn.rollback();
        ConnUtil.closeConn();
    }
}
