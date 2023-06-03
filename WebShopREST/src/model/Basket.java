package model;

import java.util.ArrayList;

public class Basket {
	private int id;
	private ArrayList<Vehicle> vehicles;
	private User user;
	private int price;
	
	
	public Basket() {
	}
	
	public Basket(int id, ArrayList<Vehicle> v, User u, int p) {
		this.id = id;
		this.vehicles = v;
		this.user = u;
		this.price = p;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}
