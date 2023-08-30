package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.UserDTO;
import dto.VehicleCreationDTO;
import enums.FuelType;
import enums.Gender;
import enums.OrderStatus;
import enums.Role;
import enums.VehicleKind;
import enums.VehicleStatus;
import enums.VehicleType;
import model.Order;
import model.RentACarObject;
import model.User;
import model.Vehicle;

public class VehicleDAO {
	private static VehicleDAO instance = null;

	private ArrayList<Vehicle> vehicles;
	private final File file;

	private VehicleDAO() {

		vehicles = new ArrayList<Vehicle>();
		String filePath = ProjectDAO.ctxPath + "vehicle.txt";
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

		if (loggedManager.getRole() != Role.manager) {
			return null;
		}
		if (loggedManager.getRentACarObject() == null) {
			return null;
		}

		vehicle.setObject(loggedManager.getRentACarObject());
		loggedManager.getRentACarObject().getVehicles().add(vehicle);
		vehicle.setStatus(VehicleStatus.available);
		vehicles.add(vehicle);
		writeToFileJSON();
		return vehicle;
	}
	
	public Vehicle update(int id, VehicleCreationDTO vehicleCreationDTO) {
		Vehicle foundVehicle = getById(id);

		if (foundVehicle == null) {
			return null;
		}

		foundVehicle.setBrand(vehicleCreationDTO.getBrand());
		foundVehicle.setModel(vehicleCreationDTO.getModel());
		foundVehicle.setPrice(vehicleCreationDTO.getPrice());
		foundVehicle.setVehicleType(vehicleCreationDTO.getType());
		foundVehicle.setFuel(vehicleCreationDTO.getFuelType());
		foundVehicle.setConsumption(vehicleCreationDTO.getConsumption());
		foundVehicle.setNumberOfDoors(vehicleCreationDTO.getDoorNumber());
		foundVehicle.setNumberOfPeople(vehicleCreationDTO.getPeopleNumber());
		foundVehicle.setDescription(vehicleCreationDTO.getDescription());
		foundVehicle.setImage(vehicleCreationDTO.getPicture());
		writeToFileJSON();
		return foundVehicle;

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



	private void writeToFileJSON() {
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(fileWriter);

			for (Vehicle vehicle : vehicles) {
				output.write(vehicle.toStringForFile() + "\n");
			}

			output.close();
		}

		catch (Exception e) {
			e.getStackTrace();
		}

	}

	public void bindRentACarObject() {
		for (Vehicle vehicle : vehicles) {
			int objectId = vehicle.getObject().getId();
			RentACarObject rentACarObject = RentACarObjectDAO.getInstance().getById(objectId);
			if (rentACarObject == null) {
				System.out.println("Vehicle/RentACarObject bind error");
				continue;
			}
			vehicle.setObject(rentACarObject);
			rentACarObject.getVehicles().add(vehicle);
			
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
				String brand = parts[1];
				String model = parts[2];
				double price = Double.parseDouble(parts[3]);
				VehicleType type = VehicleType.valueOf(parts[4]);
				VehicleKind kind = VehicleKind.valueOf(parts[5]);
				FuelType fuel = FuelType.valueOf(parts[6]);
				double consumption = Double.parseDouble(parts[7]);
				int doors = Integer.parseInt(parts[8]);
				int people = Integer.parseInt(parts[9]);
				String description = parts[10];
				String image = parts[11];
				VehicleStatus status = VehicleStatus.valueOf(parts[12]);
				int rentACarObjectId = Integer.parseInt(parts[13]);
				RentACarObject object = new RentACarObject(rentACarObjectId);
				
				Vehicle vehicle = new Vehicle(id, brand, model, price, type, kind, fuel, consumption, doors, people, description, image, status, object);
				vehicles.add(vehicle);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
