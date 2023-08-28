package model;

import java.util.ArrayList;
import enums.OrderStatus;

public class Order {
	private int id; // 10 karaktera
	private ArrayList<Vehicle> vehicles;
	private RentACarObject rentACarObject;
	private String rentalDateAndTime;
	private int leaseDuration;
	private int price;
	private User user;
	private OrderStatus orderStatus;

	public Order() {
		super();
	}

	public Order(int id, ArrayList<Vehicle> vehicles, RentACarObject rentACarObject, String dateAndTime,
			int leaseDuration, int price, User user, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.vehicles = vehicles;
		this.rentACarObject = rentACarObject;
		this.rentalDateAndTime = dateAndTime;
		this.leaseDuration = leaseDuration;
		this.price = price;
		this.user = user;
		this.orderStatus = orderStatus;
	}

	public Order(int id, RentACarObject rentACarObject, String dateAndTime, int leaseDuration, int price, User user,
			OrderStatus orderStatus) {
		super();
		this.id = id;
		this.vehicles = new ArrayList<Vehicle>();
		this.rentACarObject = rentACarObject;
		this.rentalDateAndTime = dateAndTime;
		this.leaseDuration = leaseDuration;
		this.price = price;
		this.user = user;
		this.orderStatus = orderStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public RentACarObject getRentACarObject() {
		return rentACarObject;
	}

	public void setRentACarObject(RentACarObject rentACarObject) {
		this.rentACarObject = rentACarObject;
	}

	public String getDateAndTime() {
		return rentalDateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.rentalDateAndTime = dateAndTime;
	}

	public int getLeaseDuration() {
		return leaseDuration;
	}

	public void setLeaseDuration(int leaseDuration) {
		this.leaseDuration = leaseDuration;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String toStringForFile() {
		return id + "|" + rentACarObject.getId() + "|" + rentalDateAndTime + "|" + leaseDuration + "|" + price + "|"
				+ user.getId() + "|" + orderStatus;
	}
}
