package com.aotain.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {

	private final static String POOL_NAME = "dams";
	
	public static Connection getConnection(){
		String driver = DBConfig.getInstance().getDbDriver();
		String url = DBConfig.getInstance().getDbURL(); 
		String user = DBConfig.getInstance().getDbUser(); 
		String password = DBConfig.getInstance().getDbPassword(); 

		Connection conn = null;
		if(DBConfig.getInstance().getHasPool().equals("yes")){
			while(conn==null){
				conn =  DBConnectionManager.getInstance().getConnection(POOL_NAME);
	        }
		}
		else{
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				conn = null;
				System.out.println("Not find Driver");
			} catch (SQLException e) {
				conn = null;
				System.out.println("Connect error");
			}
		}
		return conn;
	}
	
	public static Connection getMconnection(){
		String driver = DBConfig.getInstance().getMdbDriver();
		String url = DBConfig.getInstance().getMdbURL(); 
		String user = DBConfig.getInstance().getMdbUser(); 
		String password = DBConfig.getInstance().getMdbPassword(); 

		Connection conn = null;
		if(DBConfig.getInstance().getHasPool().equals("yes")){
			while(conn==null){
				conn =  DBConnectionManager.getInstance().getConnection(POOL_NAME);
	        }
		}
		else{
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				conn = null;
				System.out.println("Not find Driver");
			} catch (SQLException e) {
				conn = null;
				System.out.println("Connect error");
			}
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		if(DBConfig.getInstance().getHasPool().equals("yes")){
			DBConnectionManager.getInstance().freeConnection(POOL_NAME,conn);
		}
		else{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Close Connect fail");
				}
			}
		}
	}
	
	public static void closeConnection(Connection conn, java.sql.Statement st,
			ResultSet rs) {
		try {
			if(DBConfig.getInstance().getHasPool().equals("yes")){
				DBConnectionManager.getInstance().freeConnection(POOL_NAME,conn);
			}
			else{
				if (conn != null) {
					conn.close();
				}
				conn = null;
			}
			if (st != null) {
				st.close();
			}
			st = null;
			if (rs != null) {
				rs.close();
			}
			rs = null;
		} catch (Exception ex) {
		}
	}

	public static void closeConnection(Connection conn, java.sql.Statement st) {
		try {
			if(DBConfig.getInstance().getHasPool().equals("yes")){
				DBConnectionManager.getInstance().freeConnection(POOL_NAME,conn);
			}
			else{
				if (conn != null) {
					conn.close();
				}
				conn = null;
			}

			if (st != null) {
				st.close();
			}
			st = null;
		} catch (Exception ex) {
		}
	}
	
	public static void closeStatement(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
			st = null;
		} catch (Exception ex) {
		}
	}
	
	/**
     * ȡ����ݿ���ʵĴ���
     * @return��int ���ʴ���
     */
    public static int getAccessNum(){
        return DBConfig.getInstance().getHasPool().equals("yes")?DBConnectionManager.getInstance().getAccessNum() : 0;
    }

    /**
     * ȡ��Ĭ�����ӳص���Ч���Ӹ���
     * @return int ��Ч���Ӹ���
     */
    public static int getFreeConNum(){
        return DBConfig.getInstance().getHasPool().equals("yes")?DBConnectionManager.getInstance().getFreeConNum(POOL_NAME) : 0;
    }
    
    /**
     * ȡ��Ĭ�����ӳص���ʹ�����Ӹ���
     * @return int ��ʹ�����Ӹ���
     */
    public static int getUsedConNum(){
        return DBConfig.getInstance().getHasPool().equals("yes")?DBConnectionManager.getInstance().getUsedConNum(POOL_NAME) : 0;
    }

    /**
     * ע������Ӳ�������
     */
	public static void release(){
		if(DBConfig.getInstance().getHasPool().equals("yes")){
			DBConnectionManager.getInstance().release();
		}
    }
}
