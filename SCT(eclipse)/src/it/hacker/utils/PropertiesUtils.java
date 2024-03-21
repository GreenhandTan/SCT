package it.hacker.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory;
/*
 * �������ݿ������ļ�����������Դ
 */
public class PropertiesUtils {
	private static Properties properties = new Properties();
	
	static {
		InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){
		try {
			return BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
