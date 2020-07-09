package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 获取数据库连接工具类
 * @author whh
 *
 */
public class DbUtil {
	private static Connection conn = null;
	
	static {
		try {
			Class.forName(GetProperties.driverName);
			conn = DriverManager.getConnection(GetProperties.url, GetProperties.user, GetProperties.password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		return conn;
	}
	
	
	/**
	 * 测试连接
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(conn);
	}
}
