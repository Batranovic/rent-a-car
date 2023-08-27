package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.RentACarObject;
import model.Vehicle;

public class RentACarDTO {
	private int id;
	private String name;
	private String address;
	private String logo;
	private double averageGrade;
	private boolean open;
	private ArrayList<String> vehicleType;
	private ArrayList<String> fuelType;

	public RentACarDTO() {
		super();
	}

	public RentACarDTO(int id, String name, String address, String logo, double averageGrade, boolean open,
			ArrayList<String> vehicleType, ArrayList<String> fuelType) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.logo = logo;
		this.averageGrade = averageGrade;
		this.open = open;
		this.vehicleType = vehicleType;
		this.fuelType = fuelType;
	}

	private static void generateVehicleAndFuelTypes(RentACarDTO dto, RentACarObject rentACarObject) {
		ArrayList<String> vehicleTypes = new ArrayList<String>();
		ArrayList<String> fuelTypes = new ArrayList<String>();

		for (Vehicle vehicle : rentACarObject.getVehicles()) {
			vehicleTypes.add(vehicle.getVehicleType().toString());
			fuelTypes.add(vehicle.getFuel().toString());
		}

		ArrayList<String> vehicleTypesDistinct = new ArrayList<String>(vehicleTypes.stream().distinct().collect(Collectors.toList()));
		ArrayList<String> fuelTypesDistinct = new ArrayList<String>(fuelTypes.stream().distinct().collect(Collectors.toList()));
		dto.setFuelType(fuelTypesDistinct);
		dto.setVehicleType(vehicleTypesDistinct);
	}

	public static RentACarDTO toObject(RentACarObject rentACarObject) {
		RentACarDTO dto = new RentACarDTO();
		dto.setId(rentACarObject.getId());
		dto.setName(rentACarObject.getName());
		dto.setAddress(rentACarObject.getLocation().getAddress());
		dto.setLogo(rentACarObject.getLogo());
		dto.setAverageGrade(rentACarObject.getGrade());
		dto.setOpen(rentACarObject.isOpen());
		generateVehicleAndFuelTypes(dto, rentACarObject);
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

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public ArrayList<String> getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(ArrayList<String> vehicleType) {
		this.vehicleType = vehicleType;
	}

	public ArrayList<String> getFuelType() {
		return fuelType;
	}

	public void setFuelType(ArrayList<String> fuelType) {
		this.fuelType = fuelType;
	}

}
