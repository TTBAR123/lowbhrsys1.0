package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import db.CRUD;
import pojo.Employee;

/**
 * 主程序调用
 * 
 * @author whh
 *
 */
public class MainApp {

	private static Scanner scan = null;

	private static CRUD crud = null;

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		help();
		while (getScan().hasNextLine()) {
			String s = scan.nextLine();

			if (s.equals("1")) {
				addEmp();
			}

			if (s.equals("2")) {
				findEmpById();
			}

			if (s.equals("3")) {
				queryAllEmp();
			}

			if (s.equals("4")) {
				updateEmp();
			}
			
			if (s.equals("5")) {
				delEmp();
			}

			if (s.equals("help")) {
				help();
			}

			if (s.equals("exit")) {
				System.out.println("退出系统!");
				System.exit(0);
			}

		}

	}

	
	
	/**
	 * 指令提示信息
	 */
	public static void help() {
		System.out.println("1->添加员工");
		System.out.println("2->根据id查询员工");
		System.out.println("3->查询所有员工");
		System.out.println("4->修改员工信息");
		System.out.println("5->删除员工");
		System.out.println("help->命令帮助");
		System.out.println("exit->退出系统");
	}
	
	/**
	 * 添加员工
	 */
	public static void addEmp() {
			System.out.println("输入员工姓名：");
			String name= scan.nextLine();

			System.out.println("输入员工年龄：");
			int age= Integer.parseInt(scan.nextLine());

			System.out.println("输入员工性别：");
			int sex = scan.nextLine().equals("男") ? 0 : 1;

			System.out.println("输入员工薪水：");
			double salary = Double.parseDouble(scan.nextLine());

			Employee emp = new Employee(name, age, sex, salary);

			// 调用数据库保存
			try {
				getcrud().addEmp(emp);
				System.out.println("操作成功，你可以继续其他操作，若需命令帮助请输入help");
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
	}

	/**
	 * 根据id查询
	 */
	public static void findEmpById() {

		System.out.println("输入员工id：");
		int id = Integer.parseInt(scan.nextLine());

		// 调用CRUD的按id查询方法
		try {
			Employee emp = getcrud().getEmp(id);
			
			if(emp == null){
				System.out.println("无该员工");
			}else{
				System.out.println(emp);
				System.out.println("操作成功，你可以继续其他操作，若需命令帮助请输入help");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	
	/**
	 * 查询所有员工
	 */
	public static void queryAllEmp(){
		try {
			List<Employee> empList = getcrud().query();
			
			for (Employee employee : empList) {
				System.out.println(employee);
			}
			System.out.println("操作成功，你可以继续其他操作，若需命令帮助请输入help");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * 修改员工信息
	 */
	public static void updateEmp(){
		System.out.println("输入员工id：");
		int id = Integer.parseInt(scan.nextLine());
		
		try {
			Employee emp = getcrud().getEmp(id);
			
			if(emp == null){
				System.out.println("无该员工");
			}else{
				System.out.println("*******开始录入员工的修改信息*******");
				System.out.println("输入员工姓名：");
				String name= scan.nextLine();

				System.out.println("输入员工年龄：");
				int age= Integer.parseInt(scan.nextLine());

				System.out.println("输入员工性别：");
				int sex = scan.nextLine().equals("男") ? 0 : 1;

				System.out.println("输入员工薪水：");
				double salary = Double.parseDouble(scan.nextLine());

				Employee e = new Employee(name, age, sex, salary);
				
				getcrud().updateEmp(id, e);
				System.out.println("操作成功，你可以继续其他操作，若需命令帮助请输入help");
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	
	
	/**
	 * 根据id删除员工信息
	 */
	public static void delEmp(){
		System.out.println("输入员工id：");
		int id = Integer.parseInt(scan.nextLine());

		// 调用CRUD的按id查询方法
		try {
			Employee emp = getcrud().getEmp(id);
			
			if(emp == null){
				System.out.println("无该员工");
			}else{
				getcrud().delEmp(id);
				System.out.println("操作成功，你可以继续其他操作，若需命令帮助请输入help");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	//返回一个scanner的实例
	private static Scanner getScan() {
		if (scan == null) {
			scan = new Scanner(System.in);
		}

		return scan;
	}
	//返回一个CRUD的实例
	private static CRUD getcrud() {
		if (crud == null) {
			crud = new CRUD();
		}
		return crud;
	}
}
