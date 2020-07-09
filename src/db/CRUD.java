package db;
/**
 * 数据库操作
 * @author whh
 *
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Employee;
import util.DbUtil;
import util.GetProperties;

public class  CRUD{
	//初始化连接
	private static Connection conn = DbUtil.getConn();
	
	
	/**
	 * 添加员工
	 * @param e
	 * @throws SQLException 
	 */
	public void addEmp(Employee e) throws SQLException{
		
		String sql = "INSERT INTO emp(name,age,sex,salary)values(?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, e.getName());
		
		pstmt.setInt(2, e.getAge());
		
		pstmt.setInt(3, e.getSex());
		
		pstmt.setDouble(4, e.getSalary());
		
		pstmt.execute();
		
	}
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Employee getEmp(int id) throws SQLException{
		Employee emp = null;
		String sql = "select * from emp where id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			 emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getInt("sex"), rs.getDouble("salary"));
		}
		
		return emp;
	}
	
	/**
	 * 修改员工
	 * @param e
	 * @throws SQLException 
	 */
	public void updateEmp(int id,Employee emp) throws SQLException{
		//根据id查询该员工是否存在，不存在返回
		Employee employee = getEmp(id);
		if(employee==null){
			System.out.println("该员工不存在");
			return;
		}
		String sql = "update emp set name=?,age=?,sex=?,salary=? where id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, emp.getName());
		pstmt.setInt(2, emp.getAge());
		pstmt.setInt(3, emp.getSex());
		pstmt.setDouble(4, emp.getSalary());
		
		pstmt.execute();
		
		
	}
	
	/**
	 * 查询所有
	 * @return
	 * @throws SQLException 
	 */
	public List<Employee> query() throws SQLException{
		
		String sql ="select * from emp";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		while(rs.next()){
			list.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getInt("sex"), rs.getDouble("salary")));
		}
		return list;
		
	}
	
	/**
	 * 删除员工
	 * @param id
	 * @throws SQLException 
	 */
	public void delEmp(int id) throws SQLException{
		//根据id查询该员工是否存在，不存在返回
		Employee employee = getEmp(id);
			if(employee==null){
				System.out.println("该员工不存在");
			return;
		}
			
		String sql = "delete from emp where id = ?";	
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		
		pstmt.execute();
	}
	
	/**
	 * 测试
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		CRUD crud = new CRUD();
		//测试查询
//		System.out.println(crud.getEmp(1));
		
		//测试添加
//		crud.addEmp(new Employee("ls", 22, 1, 22243));
		
//		测试按id查询
		System.out.println(crud.getEmp(2));
	}
	
}
