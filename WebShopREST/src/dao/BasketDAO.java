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

import enums.OrderStatus;
import model.Basket;
import model.Order;
import model.RentACarObject;
import model.User;

public class BasketDAO {
	private static BasketDAO instance = null;
	
	private ArrayList<Basket> baskets;
	private final ObjectMapper objectMapper;
	private final File file;
	
	private BasketDAO() {
		objectMapper = new ObjectMapper();
		baskets = new ArrayList<Basket>();
		String filePath = ProjectDAO.ctxPath + "basket.txt";
		file = new File(filePath);
		
		readFromFileJSON();
	}
	
	public static BasketDAO getInstance() {
		if(instance == null)
		{
			instance = new BasketDAO();
		}
		return instance;
	}
	
	private Integer nextId() {
		int id = 0;
		for(Basket basket: baskets)
		{
			if(basket.getId() > id)
			{
				id = basket.getId();
			}
		}
		return id + 1;
	}
	
	
	public Basket create(Basket basket) {
		basket.setId(nextId());
		baskets.add(basket);
		writeToFileJSON();
		return basket;
	}
	
	public Basket update(int id, Basket basket) {
		Basket foundBasket = getById(id);
		
		if(foundBasket == null)
		{
			return null;
		}
		foundBasket.setVehicles(basket.getVehicles());
		foundBasket.setUser(basket.getUser());
		foundBasket.setPrice(basket.getPrice());
		
		return foundBasket;
	}

	public Basket getById(int id) {
		for(Basket b: baskets) {
			if(b.getId() == id)
				return b;
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

		      for(Basket basket : baskets) {
		    	  output.write(basket.toStringForFile());
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
				int userId = Integer.parseInt(parts[1]);
				int price = Integer.parseInt(parts[2]);
				User user = new User(userId);
				
				Basket basket = new Basket(id, user, price);
				baskets.add(basket);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
