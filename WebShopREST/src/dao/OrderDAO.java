package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import model.Basket;
import model.Order;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDAO {
	private static OrderDAO instance = null;
	
	private ArrayList<Order> orders;
	private final ObjectMapper objectMapper;
	private final File file;
	
	
	private OrderDAO() {
		objectMapper = new ObjectMapper();
		orders= new ArrayList<Order>();
		String filePath = ProjectDAO.ctxPath + "order.JSON";
		file = new File(filePath);
		
		readFromFileJSON();
	}
	
	public static OrderDAO getInstance() {
		if(instance == null)
		{
			instance = new OrderDAO();
		}
		return instance;
	}
	
	private Integer nextId() {
		int id = 0;
		for(Order order: orders)
		{
			if(order.getId() > id)
			{
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
		for(Order o: orders) {
			if(o.getId() == id)
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
			createFile();
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(file), orders);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void readFromFileJSON() {
		try {
			JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Order.class);
			orders = objectMapper.readValue(file, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
