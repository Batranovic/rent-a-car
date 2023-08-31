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
import model.Comment;
import model.Order;
import model.RentACarObject;
import model.User;
import model.Vehicle;

public class BasketDAO {
	private static BasketDAO instance = null;

	private ArrayList<Basket> baskets;
	private final File file;

	private BasketDAO() {
		baskets = new ArrayList<Basket>();
		String filePath = ProjectDAO.ctxPath + "basket.txt";
		file = new File(filePath);

		readFromFileJSON();
	}

	public static BasketDAO getInstance() {
		if (instance == null) {
			instance = new BasketDAO();
		}
		return instance;
	}

	private Integer nextId() {
		int id = 0;
		for (Basket basket : baskets) {
			if (basket.getId() > id) {
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

		if (foundBasket == null) {
			return null;
		}
		foundBasket.setVehicles(basket.getVehicles());
		foundBasket.setUser(basket.getUser());
		foundBasket.setPrice(basket.getPrice());

		return foundBasket;
	}
	
	public Basket getBasketForUser(int userId) {
		for(Basket b : baskets) {
			if(b.getUser().getId() == userId)
				return b;
		}
		return null;
	}
	
	private Basket createBasketForUser(int userId) {
		Basket basket = new Basket();
		User user = UserDAO.getInstance().getById(userId);
		if(user == null) {
			return null;
		}
		basket.setUser(user);
		basket.setId(nextId());
		basket.setPrice(0);
		baskets.add(basket);
		writeToFileJSON();
		return basket;
	}

	public Basket getVehiclesForBasket(int userId, int vehicleId){
		Basket basket= getBasketForUser(userId); 
		if(basket == null) {
			basket = createBasketForUser(userId);
			if(basket == null) {
				return null;
			}
		}
		Vehicle vehicle = VehicleDAO.getInstance().getById(vehicleId);
		basket.getVehicles().add(vehicle);
		writeToFileJSON();
		return basket;
		
	}
	
	public ArrayList<Vehicle> getAllVehicesForBasket(int userId){
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Basket b : baskets) {
			if(b.getUser().getId() == userId) {
				return b.getVehicles();
			}
		}
		return null;
	}

	public Basket getById(int id) {
		for (Basket b : baskets) {
			if (b.getId() == id)
				return b;
		}
		return null;
	}

	public void bindUser() {
		for (Basket basket : baskets) {

			int userId = basket.getUser().getId();
			User user = UserDAO.getInstance().getById(userId);
			if (user == null) {
				System.out.println("Basket/User bind error");
				continue;
			}
			basket.setUser(user);

		}
	}

	private void writeToFileJSON() {
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(fileWriter);
			for (Basket basket : baskets) {
				output.write(basket.toStringForFile() + "\n");
			}
			output.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		writeToFileBasketVehicle();
	}

	private void writeToFileBasketVehicle() {
		try {
			FileWriter fileWriter = new FileWriter(ProjectDAO.ctxPath + "basketVehicle.txt");
			BufferedWriter output = new BufferedWriter(fileWriter);
			for (Basket basket : baskets) {
				for (Vehicle vehicle : basket.getVehicles()) {
					output.write(basket.getId() + "|" + vehicle.getId());
				}
			}
			output.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void readFromFileJSON() {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String strCurrentLine;

			while ((strCurrentLine = br.readLine()) != null) {
				if (strCurrentLine.isEmpty()) {
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

	public void bindVehicle() {
		try (BufferedReader br = new BufferedReader(new FileReader(ProjectDAO.ctxPath + "basketVehicle.txt"))) {

			String strCurrentLine;

			while ((strCurrentLine = br.readLine()) != null) {
				if (strCurrentLine.isEmpty()) {
					continue;
				}
				String parts[] = strCurrentLine.split("\\|");
				int basketId = Integer.parseInt(parts[0]);
				int vehicleId = Integer.parseInt(parts[1]);

				Basket basket = BasketDAO.getInstance().getById(basketId);
				if (basket == null) {
					System.out.println("Bind error (basket)");
					continue;
				}
				Vehicle vehicle = VehicleDAO.getInstance().getById(vehicleId);
				if (vehicle == null) {
					System.out.println("Bind error (basket)");
					continue;
				}
				basket.getVehicles().add(vehicle);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
