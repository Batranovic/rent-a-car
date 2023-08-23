package dto;

import model.Location;
import model.RentACarObject;

public class ObjectCreationDTO {
	private String name;
	private String address;
	private double longitude;
	private double latitude;
	private String from;
	private String to;
	private String logo;
	private int managerId;

	public ObjectCreationDTO() {
		super();
	}

	public ObjectCreationDTO(String name, double longitude, double latitude, String address, String from, String to,
			String logo, int managerId) {
		super();
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.from = from;
		this.to = to;
		this.logo = logo;
		this.managerId = managerId;
	}
	
	public RentACarObject ConvertToObject() {
		RentACarObject rentACarObject = new RentACarObject();
		rentACarObject.setName(name);
		rentACarObject.setFrom(from);
		rentACarObject.setTo(to);
		rentACarObject.setLogo(logo);
		rentACarObject.setId(managerId);
		return rentACarObject;
	}
	
	public static ObjectCreationDTO convertToDTO(RentACarObject rentACarObject) {
		ObjectCreationDTO dto = new ObjectCreationDTO();
		dto.name = rentACarObject.getName();
		dto.longitude = rentACarObject.getLocation().getLongitude();
		dto.latitude = rentACarObject.getLocation().getLatitude();
		dto.address = rentACarObject.getLocation().getAddress();
		dto.from = rentACarObject.getFrom();
		dto.to = rentACarObject.getTo();
		dto.logo = rentACarObject.getLogo();
		return dto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

}
