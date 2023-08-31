package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import enums.CustomerType;
import enums.Gender;
import enums.OrderStatus;
import dto.SearchUserDTO;
import dto.UserDTO;
import model.Basket;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.LoginDTO;
import dto.ManagerCreationForObjectDTO;
import dto.RegisterUserDTO;
import dto.SearchDTO;
import enums.Role;
import model.RentACarObject;
import model.User;
import model.Vehicle;
import model.Customer;
import model.Order;

public class UserDAO {
	private static UserDAO instance = null;

	private ArrayList<User> users;
	private final File file;

	private UserDAO() {

		users = new ArrayList<User>();
		String filePath = ProjectDAO.ctxPath + "users.txt";
		file = new File(filePath);

		readFromFileJSON();
	}

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	private Integer nextId() {
		int id = 0;
		for (User book : users) {
			if (book.getId() > id) {
				id = book.getId();
			}
		}
		return id + 1;
	}

	public ArrayList<User> getAll() {
		return new ArrayList<>(users);
	}

	public User findUser(User user) {
		for (User u : users) {
			if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
				return u;
		}
		return null;
	}

	public User getByUsername(String username) {
		for (User u : users) {
			if (u.getUsername().equals(username))
				return u;
		}
		return null;
	}

	public User getById(int id) {
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public User create(User user) {
		user.setId(nextId());
		users.add(user);
		writeToFileJSON();
		return user;
	}

	public User update(int id, UserDTO userDTO) {
		User foundUser = getById(id);

		if (foundUser == null) {
			return null;
		}

		foundUser.setName(userDTO.getName());
		foundUser.setSurname(userDTO.getSurname());
		foundUser.setUsername(userDTO.getUsername());
		foundUser.setPassword(userDTO.getPassword());
		foundUser.setGender(Gender.valueOf(userDTO.getGender()));
		foundUser.setBirthday(userDTO.getBirthday());
		writeToFileJSON();
		return foundUser;
	}

	public User update(int id, User user) {
		User foundUser = getById(id);

		if (foundUser == null) {
			return null;
		}

		foundUser.setName(user.getName());
		foundUser.setSurname(user.getSurname());
		foundUser.setUsername(user.getUsername());
		foundUser.setPassword(user.getPassword());
		foundUser.setGender(user.getGender());
		foundUser.setBirthday(user.getBirthday());
		writeToFileJSON();
		return foundUser;
	}

	private boolean isUsernameUnique(User user) {
		return users.stream().noneMatch(u -> u.getUsername().equals(user.getUsername()));
	}

	public User save(User user) {
		if (!isUsernameUnique(user))
			return null;

		user.setId(nextId());
		users.add(user);
		writeToFileJSON();
		return user;
	}

	private void writeToFileJSON() {
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(fileWriter);

			for (User user : users) {
				output.write(user.toStringForFile() + "\n");
			}

			output.close();
		}

		catch (Exception e) {
			e.getStackTrace();
		}

	}
	public void bindRentACarObject() {
		for (User user : users) {
			int objectId = user.getRentACarObject().getId();
			RentACarObject rentACarObject = RentACarObjectDAO.getInstance().getById(objectId);
		
			user.setRentACarObject(rentACarObject);
		}
	}
	
	
	public void bindCustomer() {
		for(User user : users) {
			int userId = user.getCustomerType().getId();
			Customer customer = CustomerDAO.getInstance().getById(userId);
			user.setCustomerType(customer);
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
				String username = parts[1];
				String password = parts[2];
				String name = parts[3];
				String surname = parts[4];
				Gender gender = Gender.valueOf(parts[5]);
				String birthday = parts[6];
				Role role = Role.valueOf(parts[7]);
				int rentACarObjectId = Integer.parseInt(parts[8]);
				int points = Integer.parseInt(parts[9]);
				int customerId = Integer.parseInt(parts[10]);
				RentACarObject rentACarObject = new RentACarObject(rentACarObjectId);
				Customer customer = new Customer(customerId);

				User user = new User(id, username, password, name, surname, gender, birthday, role,
						rentACarObject, points, customer);
				users.add(user);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public boolean isUsernameUnique(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}

	public User createCustomer(RegisterUserDTO userDTO) {
		User user = userDTO.convertToUser();
		// dodatna logika
		if (!isUsernameUnique(user.getUsername())) {
			return null;
		}
		Customer customer = CustomerDAO.getInstance().getCustomerByCustomerType(CustomerType.bronze);
		user.setCustomerType(customer);
		user.setId(nextId());
		users.add(user);
		writeToFileJSON();
		return user;
	}

	public User createManager(ManagerCreationForObjectDTO userDTO) {
		User user = userDTO.ConvertToUser();
		user.setId(nextId());
		users.add(user);
		writeToFileJSON();
		return user;
	}

	public User login(LoginDTO dto) {
		for (User user : users) {
			if (user.getUsername().equals(dto.getUsername()) && user.getPassword().equals(dto.getPassword())) {
				return user;
			}
		}
		return null;
	}

	public ArrayList<User> getAllFreeManagers() {
		ArrayList<User> freeManagers = new ArrayList<User>();
		for (User user : users) {
			if (user.getRole() == Role.manager && user.getRentACarObject() == null) {
				freeManagers.add(user);
			}
		}
		return freeManagers;
	}

	

	private boolean searchCondition(User user, SearchUserDTO searchDTO) {
		if (!user.getName().contains(searchDTO.getName())) {
			return false;
		}
		if (!user.getSurname().contains(searchDTO.getSurname())) {
			return false;
		}

		if (!user.getUsername().contains(searchDTO.getUsername())) {
			return false;
		}

		return true;
	}

	public ArrayList<User> searchUser(SearchUserDTO searchDTO) {
		if (searchDTO.getName() == null) {
			searchDTO.setName("");
		}

		if (searchDTO.getSurname() == null) {
			searchDTO.setSurname("");
		}
		if (searchDTO.getUsername() == null) {
			searchDTO.setUsername("");
		}

		ArrayList<User> searchedUsers = new ArrayList<User>();
		for (User u : users) {
			if (searchCondition(u, searchDTO)) {
				searchedUsers.add(u);
			}
		}
		return searchedUsers;
	}

}
