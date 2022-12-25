package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String userName;
	private String password;
	private String name;
	private String lastName;
	private String email;
	private double money;
	
	public User() {
	
	}
	public User(String userName, String password, String name, String lastName,	String email) {
		this.userName=userName;
		this.password=password;
		this.name=name;
		this.lastName=lastName;
		this.email=email;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", name=" + name + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	

}
