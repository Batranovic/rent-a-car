package dto;
import java.util.ArrayList;

import enums.VehicleType;
import model.Location;
import model.RentACarObject;
 
public class SearchDTO {
	private String name;
	private String vehicleType;
	private String location;
	private double averageGrade;
	
	public SearchDTO() {
		super();
	}
	
	public SearchDTO(String name, String type, String location, double grade) {
		super();
		this.name = name;
		this.vehicleType = type;
		this.location = location;
		this.averageGrade = grade;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getGrade() {
		return averageGrade;
	}

	public void setGrade(double grade) {
		this.averageGrade = grade;
	}
	


}
