package model;

public class Adminstrator {
	private String name;
	private String password;
	private String priority;
	public Adminstrator(){
		
	}
	public Adminstrator(String name, String password, String priority){
		this.name = name;
		this.password = password;
		this.priority = priority;
	}
	
	public Adminstrator(String name, String password){
		this.name = name;
		this.password = password;
		this.priority = "2";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "Adminstrator [name=" + name + ", password=" + password + ", priority=" + priority + "]";
	}
}