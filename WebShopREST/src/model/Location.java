package model;

public class Location {
	private int id;
	private String longitude;
	private String latitude;
	private String street;
	private String number;
	private String city;
	private String zipCode;
	
	public Location() {
		super();
	}
	
	public Location(int id, String longitude, String latitude, String street, String number, String city,
			String zipCode) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.street = street;
		this.number = number;
		this.city = city;
		this.zipCode = zipCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
