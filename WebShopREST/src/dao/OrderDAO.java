package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.SearchDTO;
import dto.SearchOrderDTO;
import enums.OrderStatus;
import model.Basket;
import model.Comment;
import model.Order;
import model.RentACarObject;
import model.User;
import model.Vehicle;

public class OrderDAO {
	private static OrderDAO instance = null;

	private ArrayList<Order> orders;
	private final File file;

	private OrderDAO() {
		orders = new ArrayList<Order>();
		String filePath = ProjectDAO.ctxPath + "order.txt";
		file = new File(filePath);

		readFromFileJSON();
	}

	public static OrderDAO getInstance() {
		if (instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	}

	private Integer nextId() {
		int id = 0;
		for (Order order : orders) {
			if (order.getId() > id) {
				id = order.getId();
			}
		}
		return id + 1;
	}

	public Order create(Order order) {
		order.setId(nextId());
		orders.add(order);
		writeToFileJSON();
		return order;
	}

	public Order getById(int id) {
		for (Order o : orders) {
			if (o.getId() == id)
				return o;
		}
		return null;
	}
	
	private boolean searchCondition(Order order, SearchOrderDTO searchDTO) {
		if (!order.getRentACarObject().getName().contains(searchDTO.getRentACarObject())) {
			return false;
		}
		if (!order.getRentalDateAndTime().contains(searchDTO.getRentalDateAndTime())) {
			return false;
		}

		if(searchDTO.getPrice() != -1) {
			if (order.getPrice() != searchDTO.getPrice()) {
				return false;
			}			
		}
		
		return true;
	}

	public ArrayList<Order> searchOrder(SearchOrderDTO searchDTO) {
		if (searchDTO.getRentACarObject() == null) {
			searchDTO.setRentACarObject("");
		}

		if (searchDTO.getRentalDateAndTime() == null) {
			searchDTO.setRentalDateAndTime("");
		}
		

		ArrayList<Order> searchedOrders = new ArrayList<Order>();
		for (Order o : orders) {
			if (searchCondition(o, searchDTO)) {
				searchedOrders.add(o);				
			}
		}
		return searchedOrders;
	}
	
	private void writeToFileOrderVehicle() {
		try {
			FileWriter fileWriter = new FileWriter(ProjectDAO.ctxPath + "orderVehicle.txt");
			BufferedWriter output = new BufferedWriter(fileWriter);
			for (Order order : orders) {
				for(Vehicle vehicle : order.getVehicles()) {
					output.write(order.getId() + "|" + vehicle.getId());					
				}
			}
			output.close();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void writeToFileJSON() {
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(fileWriter);

			for (Order order : orders) {
				output.write(order.toStringForFile() + "\n");
			}

			output.close();
		}

		catch (Exception e) {
			e.getStackTrace();
		}
		writeToFileOrderVehicle();
	}
	
	public void bindRentACarObject() {
		for (Order order : orders) {
			
			int objectId = order.getRentACarObject().getId();
			RentACarObject rentACarObject = RentACarObjectDAO.getInstance().getById(objectId);
			if (rentACarObject == null) {
				System.out.println("Order/RentACarObject bind error");
				continue;
			}
			order.setRentACarObject(rentACarObject);
			
		}
	}
	
	
	public void bindUser() {
		for (Order order : orders) {
			
			int userId = order.getUser().getId();
			User user = UserDAO.getInstance().getById(userId);
			if (user == null) {
				System.out.println("Order/User bind error");
				continue;
			}
			order.setUser(user);
			
		}
	}

	private void readFromFileJSON() {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String strCurrentLine;

			while ((strCurrentLine = br.readLine()) != null) {
				if(strCurrentLine.isEmpty()) {
					continue;
				}
				String parts[] = strCurrentLine.split("\\|");
				int id = Integer.parseInt(parts[0]);
				int rentACarObjectId = Integer.parseInt(parts[1]);
				String rentalDateAndTime = parts[2];
				int leastDuration = Integer.parseInt(parts[3]);
				int price = Integer.parseInt(parts[4]);
				int userId = Integer.parseInt(parts[5]);
				OrderStatus status = OrderStatus.valueOf(parts[6]);
				RentACarObject rentACarObject = new RentACarObject(rentACarObjectId);
				User user = new User(userId);
				
				Order order = new Order(id, rentACarObject, rentalDateAndTime, leastDuration, price, user, status);
				orders.add(order);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Order> getAllOrdersForUser(int userId){
		ArrayList<Order> userOrders = new ArrayList<Order>();
		for(Order order : orders) {
			if(order.getUser().getId() == userId) {
				userOrders.add(order);
			}
		}
		return userOrders;
	}

}
