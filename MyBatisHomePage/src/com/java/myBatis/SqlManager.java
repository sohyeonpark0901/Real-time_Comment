package com.java.myBatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlManager {

	private static SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory getInstance() {
		
		try {
			String resource = "com/java/myBatis/sqlConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sqlSessionFactory;
	}
	
}
