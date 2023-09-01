package dto;

import java.util.ArrayList;

import model.Basket;
import model.Vehicle;

public class BasketDTO {
	private int id;
	private ArrayList<SimpleVehicleDTO> vehicles;
	private int price;
	
	public BasketDTO() {
		super();
	}
	
	public BasketDTO(int id, ArrayList<SimpleVehicleDTO> vehicles, int price) {
		super();
		this.id = id;
		this.vehicles = vehicles;
		this.price = price;
	}

	public static BasketDTO convertToDTO(Basket basket) {
		BasketDTO dto = new BasketDTO();
		dto.setId(basket.getId());
		dto.setPrice(basket.getPrice());
		dto.vehicles = new ArrayList<SimpleVehicleDTO>();
		for(Vehicle vehicle : basket.getVehicles()) {
			dto.vehicles.add(SimpleVehicleDTO.convertToDTO(vehicle));
		}
		
		return dto;
		
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<SimpleVehicleDTO> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<SimpleVehicleDTO> vehicles) {
		this.vehicles = vehicles;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
