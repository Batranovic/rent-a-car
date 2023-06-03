package dao;
import java.util.ArrayList;
import model.User;
public class UserDAO {
	private ArrayList<User> users;
	
	public UserDAO() {
		users = new ArrayList<User>();
	}
	
	private int newId() {
		int id = 0;
		for(User user : users) {
			if(user.getId() > id) {
				id = user.getId();
			}
		}
		return id + 1;
	}
	
	public ArrayList<User> getAllUsers(){
		return new ArrayList<User>(users);
	}
	
	public User create(User user) {
		user.setId(newId());
		users.add(user);
		return user;
	}
	
	
	public User updateUser(int id, User user) {
		User foundUser = new User();
		for(User u : users) {
			if(u.getId() == id) {
				foundUser = u;
			}
		}
		
		if(foundUser == null) {
			return null;
		}
		
		foundUser.setName(user.getName());
		foundUser.setSurname(user.getSurname());
		foundUser.setUsername(user.getUsername());
		foundUser.setPassword(user.getPassword());
		foundUser.setGender(user.getGender());
		foundUser.setDateTime(user.getDateTime());

		return foundUser;
	}
	
}
