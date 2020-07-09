package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * @author whh
 *
 */
public class GetProperties {
	public static String user = "";
	public static String password ="";
	public static String driverName = "";
	public static String url = "";
	static{
		Properties properties = new Properties();
		
		InputStream in = GetProperties.class.getClassLoader().getResourceAsStream("db.properties");
		
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		user = properties.getProperty("user");
		password=properties.getProperty("password");
		driverName=properties.getProperty("driverName");
		url=properties.getProperty("url");
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(GetProperties.password);
	}
}
