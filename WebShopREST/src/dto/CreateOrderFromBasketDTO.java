package dto;

import java.util.ArrayList;

import model.Basket;
import model.Vehicle;

public class CreateOrderFromBasketDTO {
	
	private String start;
	private String end;

	public CreateOrderFromBasketDTO() {
		super();
	}
	
	public CreateOrderFromBasketDTO(String start, String end) {
		super();
		
		this.start = start;
		this.end = end;
	}
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
}
