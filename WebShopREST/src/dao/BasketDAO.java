package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Basket;
import model.User;

public class BasketDAO {
	private static BasketDAO instance = null;
	
	private ArrayList<Basket> baskets;
	private final ObjectMapper objectMapper;
	private final File file;
	
	private BasketDAO() {
		objectMapper = new ObjectMapper();
		baskets = new ArrayList<Basket>();
		String filePath = ProjectDAO.ctxPath + "basket.JSON";
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
			createFile();
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(file), baskets);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void readFromFileJSON() {
		try {
			JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Basket.class);
			baskets = objectMapper.readValue(file, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
