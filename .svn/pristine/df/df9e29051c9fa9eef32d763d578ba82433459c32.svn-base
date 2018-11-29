package com.aotain.trafficDataCollection.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.ibatis.session.SqlSessionFactory;

import com.aotain.common.util.SpringUtil;
import com.aotain.tdc.dto.common.BaseDTO;
import com.aotain.tdc.service.quality.CallBillService;

public class MybatisTest {
	public static void main(String[] args) throws SQLException, IOException{
		SubnetUtils utils = new SubnetUtils("115.239.210.36/24");
		System.out.println(utils.getInfo().getLowAddress());
		System.out.println(utils.getInfo().getHighAddress());
		//System.out.println(Double.parseDouble("20")/23);
		//insertMetaData();
		//insertChinese("中文");
		//insertFlowDerection();
		//testImpala();
	}
	
	private static void testImpala() throws IOException {
		CallBillService callBillService = (CallBillService) SpringUtil.getApplicationContext().getBean("callBillService");
		callBillService.getTableColumns(new BaseDTO());
	}

	private static void insertFlowDerection() {
		long inittime = new Date().getTime();
		TestService testService = (TestService) SpringUtil.getApplicationContext().getBean("testService");
		
		for(int i=0;i<4;i++ ){
			for(int j=1; j<6; j++){
				testService.insertFlowDerection((inittime-i*3600*1000)/1000, 1, j);
				testService.insertFlowDerection((inittime-i*3600*1000)/1000, 2, j);
				testService.insertFlowDerection((inittime-i*3600*1000)/1000, 3, j);
				testService.insertFlowDerection((inittime-i*3600*1000)/1000, 4, j);
			}
		}
	}
	private static void insertSERVERBUILD_FLOW_H() {
		long inittime = new Date().getTime();
		TestService testService = (TestService) SpringUtil.getApplicationContext().getBean("testService");
		
		for(int i=0;i<4;i++ ){
			for(int j=1; j<4; j++){
				testService.insertSERVERBUILD_FLOW_H((inittime-i*3600*1000)/1000, 1, j);
				testService.insertSERVERBUILD_FLOW_H((inittime-i*3600*1000)/1000, 2, j);
				testService.insertSERVERBUILD_FLOW_H((inittime-i*3600*1000)/1000, 3, j);
			}
		}
	}
	public static void insertChinese(String c) throws SQLException{
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetDecoder decoder = charset.newDecoder();
		CharsetEncoder encoder = charset.newEncoder();

		try {
		    // Convert a string to ISO-LATIN-1 bytes in a ByteBuffer
		    // The new ByteBuffer is ready to be read.
		    ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(c));

		    // Convert ISO-LATIN-1 bytes in a ByteBuffer to a character ByteBuffer and then to a string.
		    // The new ByteBuffer is ready to be read.
		    CharBuffer cbuf = decoder.decode(bbuf);
		    String s = cbuf.toString();
		} catch (CharacterCodingException e) {
		}
		
		TestService testService = (TestService) SpringUtil.getApplicationContext().getBean("testService");
		testService.insertChinese(c);
	}
	public static void insertMetaData() throws SQLException{
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringUtil.getApplicationContext().getBean("sqlSessionFactory");
		TestService testService = (TestService) SpringUtil.getApplicationContext().getBean("testService");
		
		DatabaseMetaData meta = (DatabaseMetaData) sqlSessionFactory.openSession().getConnection().getMetaData();  
		/**Catalog: 目录
		 * information_schema
			4glogsearch
			jason
			jason2
			mysql
			performance_schema
			report_201611
			south_network
			south_network2
		 */
		/**
		 * Schemas : 概要/用户
		 * 
		 */
		int i = 1000;
		meta.getDatabaseProductName();  
		String[] types = { "TABLE" };
		ResultSet rs = meta.getTables("south_network", "root", "WEB_MODULE_QUOTA", types);
		while (rs.next() == true){
			String tableName = rs.getString(3);
			ResultSet columns = meta.getColumns("south_network", "root", rs.getString(3), null);
			int j = 0;
			while (columns.next() == true){
				testService.insertColumn(tableName, columns.getString(4),j);
				j++;
				i++;
			}
		}
	}
}
