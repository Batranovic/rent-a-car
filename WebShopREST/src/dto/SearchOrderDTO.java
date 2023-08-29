package dto;

public class SearchOrderDTO {
	private String rentACarObject;
	private String rentalDateAndTime;
	private int price;
	
	public SearchOrderDTO() {
		super();
	}
	
	public SearchOrderDTO(String rentACarObject, String rentalDateAndTime, int price) {
		super();
		this.rentACarObject = rentACarObject;
		this.rentalDateAndTime = rentalDateAndTime;
		this.price = price;
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
