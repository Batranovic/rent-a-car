package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Customer;
import model.User;

public class CustomerDAO {
	private static CustomerDAO instance = null;
	
	private ArrayList<Customer> customers;
	private final ObjectMapper objectMapper;
	private final File file;
	
	private CustomerDAO() {
		objectMapper = new ObjectMapper();
		customers = new ArrayList<Customer>();
		String filePath = ProjectDAO.ctxPath + "customer.JSON";
		file = new File(filePath);
		
		readFromFileJSON();
	}
	
	public static CustomerDAO getInstance(){
		if(instance == null) {
			instance = new CustomerDAO();
		}
		return instance;
	}
	
	private Integer nextId() {
		int id = 0;
		for(Customer customer: customers) {
			if(customer.getId() > id)
			{
				id = customer.getId();
			}
		}
		return id + 1;
	}
	
	public ArrayList<Customer> getAll() {
		return new ArrayList<>(customers);
	}
	
	public Customer create(Customer customer) {
		customer.setId(nextId());
		customers.add(customer);
		writeToFileJSON();
		return customer;
	}
	
	public Customer getById(int id) {
		for (Customer c : customers) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public Customer update(int id, Customer customer) {
		Customer foundCustomer = getById(id);

		if (foundCustomer == null) {
			return null;
		}

		foundCustomer.setType(customer.getType());
		foundCustomer.setDiscount(customer.getDiscount());
		foundCustomer.setNeededPoints(customer.getNeededPoints());

		return foundCustomer;
	}
	
	private void createFile() throws IOException {
		if (!file.exists())
			file.createNewFile();
	}
	
	private void writeToFileJSON() {

		try {
			createFile();
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, customers);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void readFromFileJSON() {
		try {
			JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Customer.class);
			customers = objectMapper.readValue(file, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
