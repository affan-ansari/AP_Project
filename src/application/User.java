package application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	@Id
	private String user_name; 
	private String pass; 
	private String full_name; 
	private String phone_num;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
//	public User(String user_name, String pass, String full_name, String phone_num)
//	{
//		this.user_name = user_name;
//		this.pass = pass;
//		this.full_name = full_name;
//		this.phone_num = phone_num;
//	}
	
	public void setUser(String uname, String pass, String fullname, String phnum)
	{
		this.user_name = uname;
		this.pass = pass;
		this.full_name = fullname;
		this.phone_num = phnum;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public void register() {
		
	}
}
