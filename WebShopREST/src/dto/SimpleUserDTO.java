package dto;

import model.User;

public class SimpleUserDTO {
	private int id;
	private String name;
	private String surname;
	
	public SimpleUserDTO() {
		super();
	}

	public SimpleUserDTO(int id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
	
	public User convertToUser() {
		User user = new User();
		user.setId(0);
		user.setName(name);
		user.setSurname(surname);
		return user;
	}
	
	public static SimpleUserDTO ConvertSimpleUserDTO(User user) {
		SimpleUserDTO dto = new SimpleUserDTO();
		dto.id = user.getId();
		dto.name = user.getName();
		dto.surname = user.getSurname();
		return dto;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
