package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.VehicleCreationDTO;
import enums.Role;
import enums.VehicleKind;
import enums.VehicleType;
import model.User;
import model.Vehicle;

public class VehicleDAO {
	private static VehicleDAO instance = null;

	private ArrayList<Vehicle> vehicles;
	private final ObjectMapper objectMapper;
	private final File file;

	private VehicleDAO() {

		objectMapper = new ObjectMapper();
		vehicles = new ArrayList<Vehicle>();
		String filePath = ProjectDAO.ctxPath + "vehicle.JSON";
		file = new File(filePath);

		readFromFileJSON();
	}
	
	
	public static VehicleDAO getInstance() {
		if (instance == null) {
			instance = new VehicleDAO();
		}
		return instance;
	}

	private Integer nextId() {
		int id = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getId() > id) {
				id = vehicle.getId();
			}
		}
		return id + 1;
	}

	public ArrayList<Vehicle> getAll() {
		readFromFileJSON();
		return new ArrayList<>(vehicles);
	}
	
	public Vehicle getById(int id) {
		for (Vehicle v : vehicles) {
			if (v.getId() == id) {
				return v;
			}
		}
		return null;
	}

	public Vehicle create(VehicleCreationDTO dto, User loggedManager) {
		Vehicle vehicle = dto.ConvertToVehicle();
		vehicle.setId(nextId());
		
		if(loggedManager.getRole() != Role.Manager) {
			return null;
		}
		if(loggedManager.getRentACarObject() == null) {
			return null;
		}
		
		vehicle.setObject(loggedManager.getRentACarObject());
		
		vehicles.add(vehicle);
		writeToFileJSON();
		return vehicle;
	}
	
	public Vehicle update(int id, Vehicle vehicle) {
		Vehicle foundVehicle = getById(id);

		if (foundVehicle == null) {
			return null;
		}

		foundVehicle.setBrand(vehicle.getBrand());
		foundVehicle.setConsumption(vehicle.getConsumption());
		foundVehicle.setDescription(vehicle.getDescription());
		foundVehicle.setFuel(vehicle.getFuel());
		foundVehicle.setImage(vehicle.getImage());
		foundVehicle.setModel(vehicle.getModel());
		foundVehicle.setNumberOfDoors(vehicle.getNumberOfDoors());
		foundVehicle.setNumberOfPeople(vehicle.getNumberOfPeople());
		foundVehicle.setVehicleKind(vehicle.getVehicleKind());
		foundVehicle.setVehicleType(vehicle.getVehicleType());
		foundVehicle.setStatus(vehicle.getStatus());

		return foundVehicle;
	}
	
	private void createFile() throws IOException {
		if (!file.exists())
			file.createNewFile();
	}
	
	private void writeToFileJSON() {

		try {
			createFile();
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(file), vehicles);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void readFromFileJSON() {
		try {
			JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Vehicle.class);
			vehicles = objectMapper.readValue(file, type);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
