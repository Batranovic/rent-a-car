package dto;

import model.Comment;

public class CommentCreationDTO {
	private int userId;
	private int rentACArObjectId;
	private String text;
	private int grade;
	
	public CommentCreationDTO() {
		super();
	}
	
	public CommentCreationDTO(int userId, int rentACArObjectId, String text, int grade) {
		super();
		this.userId = userId;
		this.rentACArObjectId = rentACArObjectId;
		this.text = text;
		this.grade = grade;
	}
	
	public Comment ConvertToComment() {
		Comment comment = new Comment();
		comment.setText(text);
		comment.setGrade(grade);
		
		return comment;
	}
	
	public static CommentCreationDTO convertToDTO(Comment comment) {
		CommentCreationDTO dto = new CommentCreationDTO();
		dto.userId = comment.getUser().getId();
		dto.rentACArObjectId = comment.getRentACarObject().getId();
		dto.text = comment.getText();
		dto.grade = comment.getGrade();
		return dto;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRentACArObjectId() {
		return rentACArObjectId;
	}
	public void setRentACArObjectId(int rentACArObjectId) {
		this.rentACArObjectId = rentACArObjectId;
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
