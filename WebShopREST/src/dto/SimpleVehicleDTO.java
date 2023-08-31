package dto;

import model.Vehicle;

public class SimpleVehicleDTO {
	private int id;
	private String image;
	private String brand;
	private String model;
	private double price;
	
	
	public SimpleVehicleDTO() {
		super();
	}
	public SimpleVehicleDTO(int id, String image, String brand, String model, double price) {
		super();
		this.id = id;
		this.image = image;
		this.brand = brand;
		this.model = model;
		this.price = price;
	}
	
	public static SimpleVehicleDTO convertToDTO(Vehicle vehicle) {
		SimpleVehicleDTO dto = new SimpleVehicleDTO();
		dto.setId(vehicle.getId());
		dto.setImage(vehicle.getImage());
		dto.setBrand(vehicle.getBrand());
		dto.setModel(vehicle.getModel());
		dto.setPrice(vehicle.getPrice());
		return dto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	
	
}
