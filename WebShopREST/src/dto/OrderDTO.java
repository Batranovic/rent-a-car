package dto;

import model.Order;

public class OrderDTO {
	private String rentACarObject;
	private int rentACarObjectId;
	private String rentalDateAndTime;
	private int price;
	private String status;
	
	public OrderDTO() {
		super();
	}
	public OrderDTO(String rentACarObject,int rentId, String rentalDateAndTime, int price, String status) {
		super();
		this.rentACarObject = rentACarObject;
		this.rentACarObjectId = rentId;
		this.rentalDateAndTime = rentalDateAndTime;
		this.price = price;
		this.status = status;
	}
	
	public static OrderDTO toObject(Order order) {
		OrderDTO dto = new OrderDTO();
		dto.setRentACarObject(order.getRentACarObject().getName());
		dto.setRentACarObjectId(order.getRentACarObject().getId());
		dto.setRentalDateAndTime(order.getRentalDateAndTime());
		dto.setPrice(order.getPrice());		
		dto.setStatus(order.getOrderStatus().toString());
		return dto;
	}
	
	public int getRentACarObjectId() {
		return rentACarObjectId;
	}
	public void setRentACarObjectId(int rentACarObjectId) {
		this.rentACarObjectId = rentACarObjectId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRentACarObject() {
		return rentACarObject;
	}
	public void setRentACarObject(String rentACarObject) {
		this.rentACarObject = rentACarObject;
	}
	public String getRentalDateAndTime() {
		return rentalDateAndTime;
	}
	public void setRentalDateAndTime(String rentalDateAndTime) {
		this.rentalDateAndTime = rentalDateAndTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
