package dto;

import model.Basket;
import model.Order;

public class AddToBasketDTO {
	private int userId;
	private int vehicleId;
	public AddToBasketDTO() {
		super();
	}
	public AddToBasketDTO(int userId, int vehicleId) {
		super();
		this.userId = userId;
		this.vehicleId = vehicleId;
	}
	


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
}
