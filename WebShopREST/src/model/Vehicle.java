package model;
import java.util.EnumSet;

import enums.VehicleType;
import enums.FuelType;
import enums.VehicleKind;
import enums.VehicleStatus;
public class Vehicle {
	private int id;
	private String brand;
	private String model;
	private VehicleType vehicleType;
	private VehicleKind vehicleKind;
	private FuelType fuel;
	private double consumption;
	private int numberOfDoors;
	private int numberOfPeople;
	private String description;
	private String image;
	private VehicleStatus status;
	private String shop;
	
	
	public Vehicle() {
		
	}
	
	public Vehicle(int id, String brand, String model, VehicleType vT, VehicleKind vK,
					FuelType fuel, double c, int d, int p, String des, String image, VehicleStatus vS, String shop) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.vehicleType = vT;
		this.vehicleKind = vK;
		this.fuel = fuel;
		this.consumption = c;
		this.numberOfDoors = d;
		this.numberOfPeople = p;
		this.description = des;
		this.image = image;
		this.status = vS;
		this.shop = shop;
	}
	
	public void setid(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setVehicleType(VehicleType type) {
		this.vehicleType = type;
	}
	
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	public void setVehicleKind(VehicleKind kind) {
		this.vehicleKind = kind;
	}
	
	public VehicleKind getVehicleKind() {
		return vehicleKind;
	}
	
	public void setFuelType(FuelType type) {
		this.fuel = type;
	}
	
	public FuelType getFuelType() {
		return fuel;
	}
	
	public void setConsumption(double c) {
		this.consumption = c;
	}
	
	public double getConsumption() {
		return consumption;
	}
	
	public void setNumberOfDoors(int d) {
		this.numberOfDoors = d;
	}
	
	public int getNumberOfDoors() {
		return numberOfDoors;
	}
	
	public void setNumberOfPeople(int p) {
		this.numberOfPeople = p;
	}
	
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	
	public void setDescription(String d) {
		this.description = d;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setImage(String i) {
		this.image = i;
	}
	
	public String getImage() {
		return image;
	}
	
	
	public void setVehicleStatus(VehicleStatus status) {
		this.status = status;
	}
	
	public VehicleStatus getVehicleStatus() {
		return status;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	
	public String getShop() {
		return shop;
	}
	
	
	
	
	
}
