package dto;

import java.util.ArrayList;
import model.RentACarObject;
import model.Vehicle;
public class DetailedRentACarDTO {
	private int id;
	private String name;
	private String from;
	private String to;
	private boolean open;
	private String address;
	private String logo;
	private double averageGrade;
	private ArrayList<VehicleCreationDTO> vehicles;
	
	public DetailedRentACarDTO() {
		super();
	}
	
	
	public DetailedRentACarDTO(int id, String name, String from, String to, boolean open, String address, String logo,
			double averageGrade, ArrayList<VehicleCreationDTO> vehicles) {
		super();
		this.id = id;
		this.name = name;
		this.from = from;
		this.to = to;
		this.open = open;
		this.address = address;
		this.logo = logo;
		this.averageGrade = averageGrade;
		this.vehicles = vehicles;
	}
	
	public static DetailedRentACarDTO toObject(RentACarObject rentACarObject) {
		DetailedRentACarDTO dto = new DetailedRentACarDTO();
		dto.setId(rentACarObject.getId());
		dto.setName(rentACarObject.getName());
		dto.setFrom(rentACarObject.getFrom());
		dto.setTo(rentACarObject.getTo());
		dto.setOpen(rentACarObject.isOpen());
		dto.setAddress(rentACarObject.getLocation().getAddress());
		dto.setLogo(rentACarObject.getLogo());
		dto.setAverageGrade(rentACarObject.getGrade());
		dto.vehicles = new ArrayList<VehicleCreationDTO>();
		for(Vehicle vehicle : rentACarObject.getVehicles()) {
			dto.vehicles.add(VehicleCreationDTO.convertToDTO(vehicle));
		}
		return dto;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	public ArrayList<VehicleCreationDTO> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<VehicleCreationDTO> vehicles) {
		this.vehicles = vehicles;
	}
	
	
	
	
}
