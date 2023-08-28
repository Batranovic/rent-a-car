package dto;

import enums.Gender;
import enums.Role;
import model.User;

public class ManagerCreationForObjectDTO {
	private int id;
	private String name;
	private String surname;
	private Gender gender;
	private String birthday;
	private String username;
	private String password;

	public ManagerCreationForObjectDTO() {
		super();
	}

	public ManagerCreationForObjectDTO(int id, String name, String surname, Gender gender, String birthday,
			String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthday = birthday;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User ConvertToUser() {
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setGender(gender);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(Role.manager);
		return user;
	}

	public static ManagerCreationForObjectDTO ConvertToDTO(User user) {
		ManagerCreationForObjectDTO dto = new ManagerCreationForObjectDTO();
		dto.setId(user.getId());
		dto.name = user.getName();
		dto.surname = user.getSurname();
		dto.gender = user.getGender();
		dto.birthday = user.getBirthday();
		dto.username = user.getUsername();
		dto.password = user.getPassword();
		return dto;
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
