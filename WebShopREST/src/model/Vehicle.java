package model;
import enums.FuelType;
import enums.VehicleKind;
import enums.VehicleStatus;
import enums.VehicleType;

public class Vehicle {
	private int id;
	private String brand;
	private String model;
	private double price;
	private VehicleType vehicleType;
	private VehicleKind vehicleKind;
	private FuelType fuel;
	private double consumption;
	private int numberOfDoors;
	private int numberOfPeople;
	private String description;
	private String image;
	private VehicleStatus status;
	private RentACarObject object;
	private boolean deleted;
	
	
	public Vehicle() {
		super();
	}
	
	public Vehicle(int id, String brand, String model, double price, VehicleType vT, VehicleKind vK,
					FuelType fuel, double c, int d, int p, String des, String image, VehicleStatus vS, RentACarObject object, boolean deleted) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.vehicleType = vT;
		this.vehicleKind = vK;
		this.fuel = fuel;
		this.consumption = c;
		this.numberOfDoors = d;
		this.numberOfPeople = p;
		this.description = des;
		this.image = image;
		this.status = vS;
		this.object = object;
		this.deleted = deleted;
	}
	
	public String toStringForFile() {
		return id + "|" + brand + "|" + model + "|" + price + "|" + vehicleType.toString() + "|" + vehicleKind + "|"
				+ fuel + "|" + consumption + "|" + numberOfDoors + "|" + numberOfPeople + "|" + description + 
				"|" + image + "|" + status + "|" + object.getId() + "|" + deleted;
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

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleKind getVehicleKind() {
		return vehicleKind;
	}

	public void setVehicleKind(VehicleKind vehicleKind) {
		this.vehicleKind = vehicleKind;
	}

	public FuelType getFuel() {
		return fuel;
	}

	public void setFuel(FuelType fuel) {
		this.fuel = fuel;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	public RentACarObject getObject() {
		return object;
	}

	public void setObject(RentACarObject object) {
		this.object = object;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
