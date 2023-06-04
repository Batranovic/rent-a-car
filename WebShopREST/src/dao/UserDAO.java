package dao;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
public class UserDAO {
	private ArrayList<User> users;
	private final ObjectMapper objectMapper;
	private final File file;
	
    public UserDAO(String CtxPath){
    	
    	objectMapper = new ObjectMapper();
        users = new ArrayList<User>();
        String filePath = CtxPath + "..\\..\\src\\resources\\users.JSON";
        file = new File(filePath);
        
        readFromFileJSON();
    }
    
    
    private Integer nextId() {
		int id = 0;
		for(User book : users) {
			if(book.getId() > id) {
				id = book.getId();
			}
		}
		return id + 1;
	}

    
	public ArrayList<User> getAllUsers() {
	       readFromFileJSON();
	       return new ArrayList<>(users);
	    }

	

	public User searchById(int id) {
			 for(User u:users) {
				 if(u.getId() == id) {
					 return u;
				 }
			 }
			 return null;
		 }
	public User findUser(User user) {
        return users.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null);
    }

	public User create(User user) {
		user.setId(nextId());
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
		foundUser.setBirthday(user.getBirthday());

		return foundUser;
	}
	
	private boolean isUsernameUnique(User user) {
        return users.stream()
                .noneMatch(u -> u.getUsername().equals(user.getUsername()));
    }
	
	
	 public User save(User user) {
	        if (!isUsernameUnique(user))
	            return null;

	        user.setId(nextId());
	        users.add(user);
	        writeToFileJSON();
	        return user;
	    }

    private void writeToFileJSON()
    {

        try {
            createFile();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(file), users);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void readFromFileJSON()
    {
        try{
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class,User.class);
            users = objectMapper.readValue(file, type);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void createFile() throws IOException{
        if(!file.exists()) file.createNewFile();
    }
	
}
