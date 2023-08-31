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

import dto.ObjectCreationDTO;
import dto.SearchDTO;
import enums.OrderStatus;
import enums.VehicleType;
import model.Location;
import model.Order;
import model.RentACarObject;
import model.User;
import model.Vehicle;

public class RentACarObjectDAO {
	private static RentACarObjectDAO instance = null;

	private ArrayList<RentACarObject> rentACarObjects;
	private final File file;

	private RentACarObjectDAO() {
		rentACarObjects = new ArrayList<RentACarObject>();
		String filePath = ProjectDAO.ctxPath + "rentACarObject.txt";
		file = new File(filePath);

		readFromFileJSON();
	}

	public static RentACarObjectDAO getInstance() {
		if (instance == null) {
			instance = new RentACarObjectDAO();
		}
		return instance;
	}

	private Integer nextId() {
		int id = 0;
		for (RentACarObject rentACar : rentACarObjects) {
			if (rentACar.getId() > id) {
				id = rentACar.getId();
			}
		}
		return id + 1;
	}

	public ArrayList<RentACarObject> getAll() {
		return new ArrayList<>(rentACarObjects);
	}

	public RentACarObject create(ObjectCreationDTO dto) {
		RentACarObject rentACar = dto.ConvertToObject();
		rentACar.setId(nextId());

		Location location = new Location(0, dto.getLongitude(), dto.getLatitude(), dto.getAddress());
		LocationDAO.getInstance().create(location);
		rentACar.setLocation(location);

		User manager = UserDAO.getInstance().getById(dto.getManagerId());
		if (manager == null) {
			return null;
		}
		manager.setRentACarObject(rentACar);
		UserDAO.getInstance().update(manager.getId(), manager);

		rentACarObjects.add(rentACar);
		writeToFileJSON();
		return rentACar;
	}

	public RentACarObject getById(int id) {
		for (RentACarObject rac : rentACarObjects) {
			if (rac.getId() == id) {
				return rac;
			}
		}
		return null;
	}

	private boolean checkIfVehicleTypeExistsInObject(RentACarObject rentACarObject, String type) {
		for (Vehicle vehicle : rentACarObject.getVehicles()) {
			if (vehicle.getVehicleType().toString().contains(type))
				return true;
		}
		return false;
	}

	private boolean searchCondition(RentACarObject aCarObject, SearchDTO searchDTO) {
		if (!aCarObject.getName().contains(searchDTO.getName())) {
			return false;
		}
		if (!aCarObject.getLocation().getAddress().contains(searchDTO.getLocation())) {
			return false;
		}

		if(searchDTO.getGrade() != -1) {
			if (aCarObject.getGrade() != searchDTO.getGrade()) {
				return false;
			}			
		}
		if(searchDTO.getVehicleType() != "") {
			if (!checkIfVehicleTypeExistsInObject(aCarObject, searchDTO.getVehicleType())) {
				return false;
			}			
		}
		return true;
	}

	public ArrayList<RentACarObject> searchRentACarObject(SearchDTO searchDTO) {
		if (searchDTO.getName() == null) {
			searchDTO.setName("");
		}

		if (searchDTO.getLocation() == null) {
			searchDTO.setLocation("");
		}
		if(searchDTO.getVehicleType() == null) {
			searchDTO.setVehicleType("");
		}


		ArrayList<RentACarObject> searchedObjects = new ArrayList<RentACarObject>();
		for (RentACarObject r : rentACarObjects) {
			if (searchCondition(r, searchDTO)) {
				searchedObjects.add(r);				
			}
		}
		return searchedObjects;
	}
	
	public void bindLocation() {
		for(RentACarObject rentACar : rentACarObjects) {
			
			int locationId = rentACar.getLocation().getId();
			Location location = LocationDAO.getInstance().getById(locationId);
			if(location == null) {
				System.out.println("RentACarObject/Location bind error");
				continue;
			}
			rentACar.setLocation(location);
		}
	}

	private void writeToFileJSON() {
		 try {
		      FileWriter fileWriter = new FileWriter(file);
		      BufferedWriter output = new BufferedWriter(fileWriter);

		      for(RentACarObject object : rentACarObjects) {
		    	  output.write(object.toStringForFile()  + "\n");
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
				if(strCurrentLine.isEmpty() || strCurrentLine.startsWith("#")) {
					continue;
				}
		
				String parts[] = strCurrentLine.split("\\|");
				int id = Integer.parseInt(parts[0]);
				String name = parts[1];
				String from = parts[2];
				String to = parts[3];
				boolean open = Boolean.parseBoolean(parts[4]);
				int locationId = Integer.parseInt(parts[5]);
				String logo = parts[6];
				double grade = Double.parseDouble(parts[7]);
				Location location = new Location(locationId);
				
				RentACarObject rent = new RentACarObject(id, name, from, to, open, location, logo, grade);
				rentACarObjects.add(rent);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	private void createFile() throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
	}
}
