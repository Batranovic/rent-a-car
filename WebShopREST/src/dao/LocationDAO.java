package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Customer;
import model.Location;
import model.User;

public class LocationDAO {
private static LocationDAO instance = null;
	
	private ArrayList<Location> locations;
	private final ObjectMapper objectMapper;
	private final File file;
	
	private LocationDAO() {
		objectMapper = new ObjectMapper();
		locations = new ArrayList<Location>();
		String filePath = ProjectDAO.ctxPath + "location.JSON";
		file = new File(filePath);
		
		readFromFileJSON();
	}
	
	public static LocationDAO getInstance(){
		if(instance == null) {
			instance = new LocationDAO();
		}
		return instance;
	}
	
	private Integer nextId() {
		int id = 0;
		for(Location location: locations) {
			if(location.getId() > id)
			{
				id = location.getId();
			}
		}
		return id + 1;
	}
	
	public Location create(Location location) {
		location.setId(nextId());
		locations.add(location);
		writeToFileJSON();
		return location;
	}
	
	public Location getById(int id) {
		for (Location l : locations) {
			if (l.getId() == id) {
				return l;
			}
		}
		return null;
	}
	
	public Location update(int id, Location location) {
		Location findLocation = getById(id);
		
		if(findLocation == null) {
			return null;
		}
		
		findLocation.setAddress(location.getAddress());
		findLocation.setLongitude(location.getLongitude());
		findLocation.setLatitude(location.getLatitude());
		
		return findLocation;
	}
	
	public ArrayList<Location> getAll() {
		readFromFileJSON();
		return new ArrayList<>(locations);
	}
	
	private void createFile() throws IOException {
		if (!file.exists())
			file.createNewFile();
	}
	
	private void writeToFileJSON() {

		try {
			createFile();
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(file), locations);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void readFromFileJSON() {
		try {
			JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Location.class);
			locations = objectMapper.readValue(file, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
