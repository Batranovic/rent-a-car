package model;
import enums.CustomerType;
public class Customer {
	private int id;
	private CustomerType type;
	private int discount;
	private int neededPoints;
	
	public Customer() {
		super();
	}
	
	public Customer(int id, CustomerType type, int d, int p) {
		super();
		this.id = id;
		this.type = type;
		this.discount = d;
		this.neededPoints = p;
	}

	public String toStringForFile() {
		return id + "|" + type + "|" + discount + "|" + neededPoints;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getNeededPoints() {
		return neededPoints;
	}

	public void setNeededPoints(int neededPoints) {
		this.neededPoints = neededPoints;
	}
	
}
