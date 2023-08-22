package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.LoginDTO;
import dto.ManagerCreationForObjectDTO;
import dto.RegisterUserDTO;
import enums.Role;
import model.User;

public class UserDAO {
	private static UserDAO instance = null;

	private ArrayList<User> users;
	private final ObjectMapper objectMapper;
	private final File file;

	private UserDAO() {

		objectMapper = new ObjectMapper();
		users = new ArrayList<User>();
		String filePath = ProjectDAO.ctxPath + "users.JSON";
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
		readFromFileJSON();
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
			createFile();
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(file), users);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void readFromFileJSON() {
		try {
			JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class);
			users = objectMapper.readValue(file, type);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void createFile() throws IOException {
		if (!file.exists())
			file.createNewFile();
	}
	
	public boolean isUsernameUnique(String username) {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}

	public User createCustomer(RegisterUserDTO userDTO) {
		User user = userDTO.convertToUser();
		//dodatna logika
		if(!isUsernameUnique(user.getUsername())) {
			return null;
		}
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
		for(User user : users) {
			if(user.getUsername().equals(dto.getUsername()) && user.getPassword().equals(dto.getPassword())) {
				return user;
			}
		}
		return null;
	}

	public ArrayList<User> getAllFreeManagers(){
		ArrayList<User> freeManagers = new ArrayList<User>();
		for(User user: users) {
			if(user.getRole() == Role.Manager && user.getRentACarObject() == null) {
				freeManagers.add(user);
			}
		}
		return freeManagers;
	}
}
