package model;

public class Comment {
	private int id;
	private User user;
	private RentACarObject rentACarObject;
	private String text;
	private int grade; //ocena od 1 do 5
	
	public Comment() {
		super();
	}

	public Comment(int id,User user, RentACarObject rentACarObject, String text, int grade) {
		super();
		this.id = id;
		this.user = user;
		this.rentACarObject = rentACarObject;
		this.text = text;
		this.grade = grade;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RentACarObject getRentACarObject() {
		return rentACarObject;
	}

	public void setRentACarObject(RentACarObject rentACarObject) {
		this.rentACarObject = rentACarObject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	
	
}
