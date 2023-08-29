package dto;

import java.util.ArrayList;

import enums.Gender;
import enums.Role;
import model.Basket;
import model.Customer;
import model.RentACarObject;
import model.User;
import model.Vehicle;

public class RegisterUserDTO {
	private String name;
	private String surname;
	private Gender gender;
	private String birthday;
	private String username;
	private String password;
	private String role;

	public RegisterUserDTO() {
		super();
	}

	public RegisterUserDTO(String name, String surname, Gender gender, String birthday, String username,
			String password, String role) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User convertToUser() {
		User user = new User();
		user.setId(0);
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setSurname(surname);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setRole(Role.customer);
		user.setPoints(0);
		return user;
	}
	
	public static RegisterUserDTO convertToDTO(User user) {
		RegisterUserDTO dto = new RegisterUserDTO();
		dto.name = user.getName();
		dto.surname = user.getSurname();
		dto.gender = user.getGender();
		dto.birthday = user.getBirthday();
		dto.username = user.getUsername();
		dto.password = user.getPassword();
		dto.role = user.getRole().toString();
		return dto;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

}
