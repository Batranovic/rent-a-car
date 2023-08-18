package model;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
	private String birthday;
	private Role role;
	private ArrayList<Vehicle> allRentals;
	private Basket basket;
	private RentACarObject rentACarObject;
	private int points;
	private Customer customerType;
	
	public User() {
		super();
	}
	
	List <User> userList = new ArrayList<>();
	
	User admin = new User();
	admin.setUsername("admin");
	admin.setPassword("111");
	admin.setRole("administrator");
	userList.add(admin);
	
	User manager = new User();
	menager.setUsername("manager");
	menager.setPassword("222");
	menager.setRole("manager");
	userList.add(manager);
	
	User buyer = new User();
	buyer.setUsername("buyer");
	buyer.setPassword("333");
	buyer.setRole("buyer");
	userList.add(buyer);
	a

	public User(int id, String username, String password, String name, String surname, Gender gender, String birthday,
			Role role, ArrayList<Vehicle> allRentals, Basket basket, RentACarObject rentACarObject, int points,
			Customer customerType) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
		this.role = role;
		this.allRentals = allRentals;
		this.basket = basket;
		this.rentACarObject = rentACarObject;
		this.points = points;
		this.customerType = customerType;
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

	public ArrayList<Vehicle> getAllRentals() {
		return allRentals;
	}

	public void setAllRentals(ArrayList<Vehicle> allRentals) {
		this.allRentals = allRentals;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
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
