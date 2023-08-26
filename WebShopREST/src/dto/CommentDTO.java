package dto;

import java.util.ArrayList;

import model.Comment;
import model.User;
import dto.DetailedRentACarDTO;

public class CommentDTO {
	private int id;
	private SimpleUserDTO user;
	private String text;
	private int grade;
	private String status;
	
	public CommentDTO() {
		super();
	}

	public CommentDTO(int id, SimpleUserDTO user, String text, int grade, String status) {
		super();
		this.id = id;
		this.user = user;
		this.text = text;
		this.grade = grade;
		this.status = status;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static CommentDTO toObject(Comment comment) {
		CommentDTO dto = new CommentDTO();
		dto.setId(comment.getId());
		dto.setGrade(comment.getGrade());
		dto.setText(comment.getText());
		dto.setUser(SimpleUserDTO.ConvertSimpleUserDTO(comment.getUser()));
		dto.setStatus(comment.getStatus().toString());
		return dto;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SimpleUserDTO getUser() {
		return user;
	}

	public void setUser(SimpleUserDTO user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
