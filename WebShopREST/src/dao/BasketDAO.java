package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import dto.CreateOrderFromBasketDTO;
import dto.RemoveFromBasketDTO;
import enums.CustomerType;
import enums.OrderStatus;
import model.Basket;
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
		for (Basket b : baskets) {
			if (b.getUser().getId() == userId)
				return b;
		}
		return null;
	}

	private Basket createBasketForUser(int userId) {
		Basket basket = new Basket();
		User user = UserDAO.getInstance().getById(userId);
		if (user == null) {
			return null;
		}
		basket.setUser(user);
		basket.setId(nextId());
		basket.setPrice(0);
		baskets.add(basket);
		writeToFileJSON();
		return basket;
	}

	public Basket removeVehicleFromBasket(RemoveFromBasketDTO removeFromBasketDTO) {
		Basket basket = getById(removeFromBasketDTO.getBasketId());
		if (basket == null) {
			return null;
		}

		Vehicle vehicle = VehicleDAO.getInstance().getById(removeFromBasketDTO.getVehicleId());
		if (vehicle == null) {
			return null;
		}

		basket.getVehicles().remove(vehicle);
		calculatePrice(basket);
		writeToFileJSON();
		return basket;
	}
	public Order buyBasket(int basketId, CreateOrderFromBasketDTO createOrderFromBasket) {
		Basket basket = getById(basketId);
		if(basket == null) {
			return null;
		}
		Order order = new Order();
		order.setOrderStatus(OrderStatus.processing);
		//order.setPrice(basket.getPrice());
		order.setUser(basket.getUser());
		order.setVehicles(basket.getVehicles());
		if(basket.getVehicles().size() == 0) {
			return null;
		}
		
		LocalDate start = LocalDate.parse(createOrderFromBasket.getStart());
		LocalDate end = LocalDate.parse(createOrderFromBasket.getEnd());
		int duration =  (int) ChronoUnit.DAYS.between(start, end);
		order.setLeaseDuration(duration);
		order.setRentalDateAndTime(createOrderFromBasket.getStart());
		
		User user = basket.getUser();
		user.setPoints(user.getPoints() + (basket.getPrice()/1000)*133);
		UserDAO.getInstance().updateUserType(user);
		
		if(user.getCustomerType().getType().equals(CustomerType.silver)) {
		    int roundedPrice = (int) Math.round(basket.getPrice() * 0.03);
		    order.setPrice(basket.getPrice() - roundedPrice);
		}else if(user.getCustomerType().getType().equals(CustomerType.golden)) {
			int roundedPrice = (int) Math.round(basket.getPrice() * 0.05);
		    order.setPrice(basket.getPrice() - roundedPrice);
		}else {
			order.setPrice(basket.getPrice());
		}
		
		Vehicle vehicle = basket.getVehicles().get(0);
		RentACarObject rentACarObject = vehicle.getObject();
		order.setRentACarObject(rentACarObject);
		OrderDAO.getInstance().create(order);
		
		basket.setVehicles(new ArrayList<Vehicle>());
		basket.setPrice(0);
		
		
		
		writeToFileJSON();
		return order;
	}
		
	public void calculatePrice(Basket basket) {

		int fullPrice = 0;
		ArrayList<Vehicle> vehicles = basket.getVehicles();
		for (Vehicle v : vehicles) {
			fullPrice += v.getPrice();
		}
		basket.setPrice(fullPrice);
	}

	public Basket addToBasket(int userId, int vehicleId) {
		Basket basket = getBasketForUser(userId);
		if (basket == null) {
			basket = createBasketForUser(userId);
			if (basket == null) {
				return null;
			}
		}
		Vehicle vehicle = VehicleDAO.getInstance().getById(vehicleId);
		basket.getVehicles().add(vehicle);
		calculatePrice(basket);
		writeToFileJSON();
		return basket;
	}

	public ArrayList<Vehicle> getAllVehicesForBasket(int userId) {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		for (Basket b : baskets) {
			if (b.getUser().getId() == userId) {
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
