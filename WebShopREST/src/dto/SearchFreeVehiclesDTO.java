package dto;

import java.time.LocalDate;

public class SearchFreeVehiclesDTO {

	private String start;
	private String end;

	public SearchFreeVehiclesDTO() {
		super();
	}

	public SearchFreeVehiclesDTO(String start, String end) {
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
