package model;
import enums.CustomerType;
public class Customer {
	private int id;
	private CustomerType type;
	private int discount;
	private int neededPoints;
	
	public Customer() {
		
	}
	
	public Customer(int id, CustomerType type, int d, int p) {
		this.id = id;
		this.type = type;
		this.discount = d;
		this.neededPoints = p;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setCustomertype(CustomerType type) {
		this.type = type;
	}
	
	public CustomerType getCustomerType() {
		return type;
	}
	
	public void setDiscount(int d) {
		this.discount = d;
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void setNeededPoints(int p) {
		this.neededPoints = p;
	}
	
	public int getNeededPoints() {
		return neededPoints;
	}
}
