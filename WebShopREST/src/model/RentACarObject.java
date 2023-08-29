package model;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RentACarObject {
	private int id;
	private String name;
	private String from;
	private String to;
	private boolean open;
	private Location location;
	private String logo;
	private double grade;
	private ArrayList<Vehicle> vehicles;
	
	public RentACarObject() {
		super();
		vehicles =  new ArrayList<Vehicle>();
	}
	
	public RentACarObject(int id, String name, String from, String to, boolean open, Location location, String logo,
			double grade, ArrayList<Vehicle> vehicles) {
		super();
		this.id = id;
		this.name = name;
		this.from = from;
		this.to = to;
		this.open = open;
		this.location = location;
		this.logo = logo;
		this.grade = grade;
		
	}
	public RentACarObject(int id, String name, String from, String to, boolean open, Location location, String logo,
			double grade) {
		super();
		this.id = id;
		this.name = name;
		this.from = from;
		this.to = to;
		this.open = open;
		this.location = location;
		this.logo = logo;
		this.grade = grade;
		this.vehicles = new ArrayList<Vehicle>();
		
	}

	public RentACarObject(int rentACarObjectId) {
		this.id = rentACarObjectId;
	}

	public String toStringForFile() {
		return id + "|" + name + "|" + from + "|" + to + "|" + open + "|"
				+ ((location == null) ? -1 : location.getId()) + "|" + logo + "|" + grade ;
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
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public ArrayList<Vehicle> getVehicles(){
		return vehicles;
	}
	
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}
