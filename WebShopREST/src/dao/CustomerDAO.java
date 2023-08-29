package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import enums.CustomerType;
import enums.OrderStatus;
import model.Customer;
import model.Order;
import model.RentACarObject;
import model.User;

public class CustomerDAO {
	private static CustomerDAO instance = null;
	
	private ArrayList<Customer> customers;
	private final File file;
	
	private CustomerDAO() {
		customers = new ArrayList<Customer>();
		String filePath = ProjectDAO.ctxPath + "customer.txt";
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
	
	public Customer getCustomerByCustomerType(CustomerType customerType) {
		for(Customer customer : customers) {
			if(customer.getType() == customerType) {
				return customer;
			}
		}
		return null;
	}
	
	
	
	private void writeToFileJSON() {
		 try {
		      FileWriter fileWriter = new FileWriter(file);
		      BufferedWriter output = new BufferedWriter(fileWriter);

		      for(Customer customer : customers) {
		    	  output.write(customer.toStringForFile()  + "\n");
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
				CustomerType type = CustomerType.valueOf(parts[1]);
				int discount = Integer.parseInt(parts[2]);
				int neededPoints = Integer.parseInt(parts[3]);
				
				Customer customer = new Customer(id, type, discount, neededPoints);
				customers.add(customer);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
