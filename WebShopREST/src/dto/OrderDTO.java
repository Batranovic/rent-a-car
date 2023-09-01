package dto;

import model.Order;

public class OrderDTO {
	private int id;
	private String rentACarObject;
	private int rentACarObjectId;
	private String rentalDateAndTime;
	private int price;
	private String status;
	private String userId;

	public OrderDTO() {
		super();
	}

	public OrderDTO(int id, String rentACarObject,int rentId, String rentalDateAndTime, int price, String status, String userId) {

		super();
		this.id = id;
		this.rentACarObject = rentACarObject;
		this.rentACarObjectId = rentId;
		this.rentalDateAndTime = rentalDateAndTime;
		this.price = price;
		this.status = status;
		this.userId = userId;
	}

	public static OrderDTO toObject(Order order) {
		OrderDTO dto = new OrderDTO();
		dto.setId(order.getId());
		dto.setRentACarObject(order.getRentACarObject().getName());
		dto.setRentACarObjectId(order.getRentACarObject().getId());
		dto.setRentalDateAndTime(order.getRentalDateAndTime());
		dto.setPrice(order.getPrice());		
		dto.setStatus(order.getOrderStatus().toString());
		dto.setUserId(order.getUser().getName());
		return dto;
	}
<<<<<<< HEAD

=======
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
>>>>>>> cb4d72ec8ad196598c152470a9479a4afa917dc9
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
