package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import enums.OrderStatus;
import model.Order;
import model.RentACarObject;
import model.User;

public class OrderDAO {
	private static OrderDAO instance = null;

	private ArrayList<Order> orders;
	private final ObjectMapper objectMapper;
	private final File file;

	private OrderDAO() {
		objectMapper = new ObjectMapper();
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

	private void createFile() throws IOException {
		if (!file.exists())
			file.createNewFile();
	}

	private void writeToFileJSON() {
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(fileWriter);

			for (Order order : orders) {
				output.write(order.toStringForFile());
			}

			output.close();
		}

		catch (Exception e) {
			e.getStackTrace();
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

}
