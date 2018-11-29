package com.aotain.common.util;

import java.util.ResourceBundle;


public class DBConfig {
	
	private String dbDriver;
	private String dbURL;
	private String dbUser;
	private String dbPassword;
	private String mdbDriver;
	private String mdbURL;
	private String mdbUser;
	private String mdbPassword;
	private String HasPool = "no";
	
	private static DBConfig instance;
	
	private DBConfig(){
		ResourceBundle config = ResourceBundle.getBundle("db");
		dbDriver = config.getString("one2n.driverClassName");
		dbURL = config.getString("one2n.url");
		dbUser = config.getString("one2n.username");
		dbPassword = config.getString("one2n.password");
		/*
		mdbDriver = config.getString("monitor.driverClassName");
		mdbURL = config.getString("monitor.url");
		mdbUser = config.getString("monitor.username");
		mdbPassword = config.getString("monitor.password");\
		*/
	}
	
	public synchronized static DBConfig getInstance() {

		if (instance == null) {
			instance = new DBConfig();
		}
		return instance;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public String getDbURL() {
		return dbURL;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}
	
	public String getHasPool() {
		return HasPool;
	}

	public String getMdbDriver() {
		return mdbDriver;
	}

	public String getMdbURL() {
		return mdbURL;
	}

	public String getMdbUser() {
		return mdbUser;
	}

	public String getMdbPassword() {
		return mdbPassword;
	}
	
}
