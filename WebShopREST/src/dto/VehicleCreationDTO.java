package dto;

import enums.FuelType;
import enums.VehicleKind;
import enums.VehicleType;
import model.Vehicle;

public class VehicleCreationDTO {
	private int id;
	private String brand;
	private String model;
	private double price;
	private VehicleType type;
	private VehicleKind category;
	private FuelType fuelType;
	private double consumption;
	private int doorNumber;
	private int peopleNumber;
	private String picture;
	private String description;
	
	public VehicleCreationDTO() {
		super();
	}

	public VehicleCreationDTO(int id,String brand, String model, double price, VehicleType type, VehicleKind category,
			FuelType fuelType, double consumption, int doorNumber, int peopleNumber, String picture,
			String description) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.type = type;
		this.category = category;
		this.fuelType = fuelType;
		this.consumption = consumption;
		this.doorNumber = doorNumber;
		this.peopleNumber = peopleNumber;
		this.picture = picture;
		this.description = description;
	}
	
	public Vehicle ConvertToVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(brand);
		vehicle.setModel(model);
		vehicle.setPrice(price);
		vehicle.setVehicleType(type);
		vehicle.setVehicleKind(category);
		vehicle.setFuel(fuelType);
		vehicle.setConsumption(consumption);
		vehicle.setNumberOfDoors(doorNumber);
		vehicle.setNumberOfPeople(peopleNumber);
		vehicle.setImage(picture);
		vehicle.setDescription(description);
		return vehicle;
	}
	
	public static VehicleCreationDTO convertToDTO(Vehicle vehicle) {
		VehicleCreationDTO dto = new VehicleCreationDTO();
		dto.id = vehicle.getId();
		dto.brand = vehicle.getBrand();
		dto.model = vehicle.getModel();
		dto.price = vehicle.getPrice();
		dto.type = vehicle.getVehicleType();
		dto.category = vehicle.getVehicleKind();
		dto.fuelType = vehicle.getFuel();
		dto.consumption = vehicle.getConsumption();
		dto.doorNumber = vehicle.getNumberOfDoors();
		dto.peopleNumber = vehicle.getNumberOfPeople();
		dto.picture = vehicle.getImage();
		dto.description = vehicle.getDescription();
		return dto;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public VehicleKind getCategory() {
		return category;
	}

	public void setCategory(VehicleKind category) {
		this.category = category;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public int getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(int doorNumber) {
		this.doorNumber = doorNumber;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
