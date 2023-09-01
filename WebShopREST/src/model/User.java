package model;

import java.util.ArrayList;

import enums.CustomerType;
import enums.Gender;
import enums.Role;

public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private String birthday;
	private Role role;
	private RentACarObject rentACarObject;
	private int points;
	private Customer customerType;
	private boolean isBlocked;
	
	public User() {
		super();
	}
	

	public User(int id, String username, String password, String name, String surname, Gender gender, String birthday,
			Role role, RentACarObject rentACarObject, int points,
			Customer customerType, boolean isBlocked) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
		this.role = role;
		this.rentACarObject = rentACarObject;
		this.points = points;
		this.customerType = customerType;
		this.isBlocked = isBlocked;
	}
	
	


	public boolean isBlocked() {
		return isBlocked;
	}


	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}


	public User(int userId) {
		this.id = userId;
	}


	public String toStringForFile() {
		return id + "|" + username + "|" + password + "|" + name + "|" + surname + "|"
				+ gender + "|" + birthday + "|" + role + "|" + ((rentACarObject == null) ? -1 : rentACarObject.getId()) + "|" + points + "|" + ((customerType == null) ? -1 : customerType.getId())+"|" + isBlocked;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}



	

	public RentACarObject getRentACarObject() {
		return rentACarObject;
	}


	public void setRentACarObject(RentACarObject rentACarObject) {
		this.rentACarObject = rentACarObject;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	public Customer getCustomerType() {
		return customerType;
	}


	public void setCustomerType(Customer customerType) {
		this.customerType = customerType;
	}

}
