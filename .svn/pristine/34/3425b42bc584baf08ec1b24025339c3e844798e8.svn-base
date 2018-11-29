package com.aotain.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	/*private static SqlSessionFactory sqlSessionFactory;*/
	
	private static ApplicationContext applicationContext;

/*	public static SqlSessionFactory getSSFInstance(){
		if(sqlSessionFactory==null){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				OperLog.dbLog.error(e.getMessage());
			}
			ApplicationContext ac = getApplicationContext();
			sqlSessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
		}
		return sqlSessionFactory;
	}*/
	
	public static ApplicationContext getApplicationContext(){
		if(applicationContext==null){
			applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		}
		return applicationContext;
	}
}
