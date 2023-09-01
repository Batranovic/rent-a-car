package dto;

public class RemoveFromBasketDTO {
	private int basketId;
	private int vehicleId;
	
	public RemoveFromBasketDTO() {
		super();
	}
	public RemoveFromBasketDTO(int basketId, int vehicleId) {
		super();
		this.basketId = basketId;
		this.vehicleId = vehicleId;
	}
	public int getBasketId() {
		return basketId;
	}
	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	
}
