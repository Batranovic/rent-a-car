package dto;
import model.User;
public class UserDTO {
	private int id;
	private String name;
	private String surname;
	private String username;
	private int points;
	private String role;
	private String customerType;
	private String gender;
	private String birthday;
	private String password;
	private int rentACarObjectId;
	
	public UserDTO() {
		super();
	}
	
	
	public UserDTO(int id, String name, String surname, String username, int points, String role, String customerType,
			String gender, String birthday, String password, int rent) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.points = points;
		this.role = role;
		this.customerType = customerType;
		this.gender = gender;
		this.birthday = birthday;
		this.password = password;
		this.rentACarObjectId = rent;
	}


	public static UserDTO toObject(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setSurname(user.getSurname());
		dto.setUsername(user.getUsername());
		dto.setPoints(user.getPoints());
		dto.setRole(user.getRole().toString());
		dto.setCustomerType(user.getCustomerType().getType().toString());
		dto.setBirthday(user.getBirthday());
		dto.setGender(user.getGender().toString());
		dto.setPassword(user.getPassword());
		if(user.getRentACarObject() != null) {
			dto.setRentACarObjectId(user.getRentACarObject().getId());			
		}
		return dto;
	}
	
	
	public int getRentACarObjectId() {
		return rentACarObjectId;
	}


	public void setRentACarObjectId(int rentACarObjectId) {
		this.rentACarObjectId = rentACarObjectId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
