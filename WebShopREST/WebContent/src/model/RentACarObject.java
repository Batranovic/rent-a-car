package model;

public class RentACarObject {
	private String id;
	private String name;
	//vozila koja su u ponudi
	private String from;
	private String to;
	private boolean isOpen;
	private String location;
	private String image;
	private String grade;
	
	public RentACarObject() {
		super();
	}
	
	public RentACarObject(String id, String name, String from, String to, boolean isOpen, String location, String image,
			String grade) {
		super();
		this.id = id;
		this.name = name;
		this.from = from;
		this.to = to;
		this.isOpen = isOpen;
		this.location = location;
		this.image = image;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
