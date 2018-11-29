package com.aotain.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * Hadoop工具类
 * @author yinzf
 * @createtime 2015.09.22
 */
public class HadoopUtils {
	
	/**
	 * 获取Connection实例
	 * @return Connection对象
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			DataSource dataSource = (DataSource) SpringUtil.getApplicationContext().getBean("impalaDataSource");
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.dbLog.error(e);
		}
		return conn;
	}
	
	/**
	 * 关闭资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param st
	 * @param rs
	 */
	public static void close(Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param st
	 */
	public static void close(Statement st) {
		try {
			if (st != null) st.close();
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param conn
	 * @param rs
	 */
	public static void close(Connection conn, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}
	}
	
	/**
	 * 刷新表
	 * @param conn Connection对象
	 * @param tableName 表名
	 */
	public static void refresh(Connection conn, String tableName){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("refresh " + tableName);
			ps.execute();
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}finally{
			HadoopUtils.close(ps);
		}
	}
	
	/**
	 * 获取结果集
	 * @param conn Connection对象
	 * @param sql 查询语句
	 * @return
	 */
	public static ResultSet executeQuery(Connection conn, String sql){
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}
		return rs;
	}
	
	/**
	 * 获取总记录数
	 * @param fromSql sql语句
	 * @param conn Connection对象
	 * @return
	 */
	public static int count(String fromSql, Connection conn){
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = conn.prepareStatement("select count(1) " + fromSql);
			System.out.println("select count(1) " + fromSql);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			CommonLog.dbLog.error(e);
		}finally{
			HadoopUtils.close(ps, rs);
		}
		return count;
	}

	public static int groupCount(String fromSql, Connection conn){
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = conn.prepareStatement("SELECT COUNT(1) FROM (SELECT  COUNT(1) " + fromSql + ")t");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			CommonLog.dbLog.error(e);
		}finally{
			HadoopUtils.close(ps, rs);
		}
		return count;
	}
}
