package dto;
import model.User;
public class UserDTO {
	private String name;
	private String surname;
	private String username;
	private int points;
	private String role;
	private String customerType;
	
	public UserDTO() {
		super();
	}
	public UserDTO(String name, String surname, String username, int points, String role, String type) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.points = points;
		this.role = role;
		this.customerType = type;
	}
	
	public static UserDTO toObject(User user) {
		UserDTO dto = new UserDTO();
		dto.setName(user.getName());
		dto.setSurname(user.getSurname());
		dto.setUsername(user.getUsername());
		dto.setPoints(user.getPoints());
		dto.setRole(user.getRole().toString());
		dto.setCustomerType(user.getCustomerType().toString());
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String type) {
		this.customerType = type;
	}
	
	
}
