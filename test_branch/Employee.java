package pojo;
/**
 * 员工实体类
 * @author whh
 *
 */
public class Employee {
	private int id;
	private String name;
	private int age;
	private int sex;
	private double salary;
	
	
	public Employee(int id, String name, int age, int sex, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.salary = salary;
	}
	
	
	public Employee(String name, int age, int sex, double salary) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.salary = salary;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + (sex==0 ? "男":"女") + ", salary=" + salary + "]";
	}
	
	
	
	public static void main(String[] args) {
		Employee e = new Employee(1, "zs", 10, 0, 12.34);
		
		System.out.println(e.toString());
	}
	
}
