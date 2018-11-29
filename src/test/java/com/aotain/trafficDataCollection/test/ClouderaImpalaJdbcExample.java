package com.aotain.trafficDataCollection.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClouderaImpalaJdbcExample {

	private static final String CONNECTION_URL_PROPERTY = "connection.url";
	private static final String JDBC_DRIVER_NAME_PROPERTY = "jdbc.driver.class.name";

	private static String connectionUrl;
	private static String jdbcDriverName;

	private static void loadConfiguration() throws IOException {
		InputStream input = null;
		try {

			ResourceBundle config = ResourceBundle.getBundle("config/config");

			connectionUrl = config.getString(CONNECTION_URL_PROPERTY);
			jdbcDriverName = config.getString(JDBC_DRIVER_NAME_PROPERTY);
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				// nothing to do
			}
		}
	}

	public static void main(String[] args) throws IOException {
		//String sqlStatement = "select * from cmcc.original_bill where partdate=20161105 and billtype=103 order by uiStartTime LIMIT 10 OFFSET 100";
		String sqlStatement = "show PARTITIONS cmcc.original_bill";

		loadConfiguration();

		System.out.println("\n=============================================");
		System.out.println("Cloudera Impala JDBC Example");
		System.out.println("Using Connection URL: " + connectionUrl);
		System.out.println("Running Query: " + sqlStatement);

		Connection con = null;

		try {

			Class.forName(jdbcDriverName);

			con = DriverManager.getConnection(connectionUrl);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sqlStatement);
			
			System.out.println("\n== Begin Query Results ======================");

			// print the results to the console
			while (rs.next()) {
				// the example query returns one String column
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}

			System.out.println("== End Query Results =======================\n\n");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// swallow
			}
		}
	}
}
