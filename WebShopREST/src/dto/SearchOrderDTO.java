package dto;

public class SearchOrderDTO {
	private String rentACarObject;
	private String rentalDateAndTimeFrom;
	private String rentalDateAndTimeTo;
	private int priceFrom;
	private int priceTo;
	
	public SearchOrderDTO() {
		super();
	}
	

	public SearchOrderDTO(String rentACarObject, String rentalDateAndTimeFrom, String rentalDateAndTimeTo,
			int priceFrom, int priceTo) {
		super();
		this.rentACarObject = rentACarObject;
		this.rentalDateAndTimeFrom = rentalDateAndTimeFrom;
		this.rentalDateAndTimeTo = rentalDateAndTimeTo;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
	}


	public String getRentACarObject() {
		return rentACarObject;
	}
	public void setRentACarObject(String rentACarObject) {
		this.rentACarObject = rentACarObject;
	}


	public String getRentalDateAndTimeFrom() {
		return rentalDateAndTimeFrom;
	}


	public void setRentalDateAndTimeFrom(String rentalDateAndTimeFrom) {
		this.rentalDateAndTimeFrom = rentalDateAndTimeFrom;
	}


	public String getRentalDateAndTimeTo() {
		return rentalDateAndTimeTo;
	}


	public void setRentalDateAndTimeTo(String rentalDateAndTimeTo) {
		this.rentalDateAndTimeTo = rentalDateAndTimeTo;
	}


	public int getPriceFrom() {
		return priceFrom;
	}


	public void setPriceFrom(int priceFrom) {
		this.priceFrom = priceFrom;
	}


	public int getPriceTo() {
		return priceTo;
	}


	public void setPriceTo(int priceTO) {
		this.priceTo = priceTO;
	}
	
}
