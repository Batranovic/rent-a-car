package dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import dto.CommentCreationDTO;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import enums.CommentStatus;
import enums.OrderStatus;
import enums.Role;
import model.Comment;
import model.Order;
import model.RentACarObject;
import model.User;

public class CommentDAO {

	private static CommentDAO instance = null;
	private ArrayList<Comment> comments;
	private final ObjectMapper objectMapper;
	private final File file;
	
	private CommentDAO() {
		objectMapper = new ObjectMapper();
		comments = new ArrayList<Comment>();
		String filePath = ProjectDAO.ctxPath + "comments.txt";
		file = new File(filePath);
		
		readFromFileJSON();
	}
	
	public static CommentDAO getInstance() {
		if(instance == null)
		{
			instance = new CommentDAO();
		}
		return instance;
	}
	
	private Integer nextId() {
		int id = 0;
		for(Comment comment: comments)
		{
			if(comment.getId() > id)
			{
				id = comment.getId();
			}
		}
		return id + 1;
	}
	public Comment createComment(CommentCreationDTO dto) {
		Comment comment = dto.ConvertToComment();
		comment.setId(nextId());
		User user = UserDAO.getInstance().getById(dto.getUserId());
		if(user == null) {
			return null;
		}
		RentACarObject rentACarObject = RentACarObjectDAO.getInstance().getById(dto.getRentACArObjectId());
		if(rentACarObject == null) {
			return null;
		}
		comment.setUser(user);
		comment.setRentACarObject(rentACarObject);
		
		comments.add(comment);
		writeToFileJSON();
		return comment;
	} 
	
	public Comment create(Comment comment) {
		comment.setId(nextId());
		comments.add(comment);
		writeToFileJSON();
		return comment;
	}
	
	public Comment getById(int id) {
		for(Comment c: comments) {
			if(c.getId() == id)
				return c;
		}
		return null;
	}
	public ArrayList<Comment> getAllCommentsForRentObject(int objectId){
		ArrayList<Comment> commentsForObject = new ArrayList<Comment>();
		for(Comment comment : comments) {
			if(comment.getRentACarObject().getId() == objectId) {
				commentsForObject.add(comment);
			}
		}
		return commentsForObject;
	}
	
	private void createFile() throws IOException {
		if (!file.exists())
			file.createNewFile();
	}
	
	private void writeToFileJSON() {
		 try {
		      FileWriter fileWriter = new FileWriter(file);
		      BufferedWriter output = new BufferedWriter(fileWriter);

		      for(Comment comment : comments) {
		    	  output.write(comment.toStringForFile());
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
				int rentACarObjectId = Integer.parseInt(parts[2]);
				String text = parts[3];
				int grade = Integer.parseInt(parts[4]);
				CommentStatus status = CommentStatus.valueOf(parts[5]);
				RentACarObject rentACarObject = new RentACarObject(rentACarObjectId);
				User user = new User(userId);
				
				Comment comment = new Comment(id, user, rentACarObject, text, grade, status);
				comments.add(comment);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
