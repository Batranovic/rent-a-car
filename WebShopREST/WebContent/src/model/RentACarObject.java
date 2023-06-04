package model;

public class RentACarObject {
	private int id;
	private String name;
	//vozila koja su u ponudi
	private String from;
	private String to;
	private boolean isOpen;
	private Location location;
	private String image;
	private int grade;
	
	public RentACarObject() {
		super();
	}
	
	public RentACarObject(int id, String name, String from, String to, boolean isOpen, Location location, String image,
			int grade) {
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
