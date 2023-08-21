package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.ObjectCreationDTO;
import model.Location;
import model.RentACarObject;
import model.User;


public class RentACarObjectDAO {
	private static RentACarObjectDAO instance = null;
	
    private ArrayList<RentACarObject> rentACarObjects;
    private final ObjectMapper objectMapper;
    private final File file;

    private RentACarObjectDAO() {
        objectMapper = new ObjectMapper();
        rentACarObjects = new ArrayList<RentACarObject>();
        String filePath = ProjectDAO.ctxPath + "rentACarObject.JSON";
        file = new File(filePath);

        readFromFileJSON();
    }
    
    public static RentACarObjectDAO getInstance()
    {
    	if(instance == null)
    	{
    		instance = new RentACarObjectDAO();
    	}
    	return instance;
    }
    
	private Integer nextId() {
		int id = 0;
		for(RentACarObject rentACar: rentACarObjects) {
			if(rentACar.getId() > id)
			{
				id = rentACar.getId();
			}
		}
		return id + 1;
	}
    
    public RentACarObject create(ObjectCreationDTO dto) {
    	RentACarObject rentACar = dto.ConvertToObject();
    	rentACar.setId(nextId());
    	
    	Location location = new Location(0, dto.getLongitude(), dto.getLatitude(), dto.getAddress());
    	LocationDAO.getInstance().create(location);
    	rentACar.setLocation(location);
    	
    	User manager = UserDAO.getInstance().getById(dto.getManagerId());
    	if(manager == null) {
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

    public RentACarObject searchRentACarObject(String name, String location, String type, double grade) {
        for (RentACarObject r : rentACarObjects) {
            if (r.getName().equals(name) && r.getLocation().equals(location) && r.getGrade() == grade)
                return r;
        }
        return null;
    }

    private void writeToFileJSON() {
        try {
            createFile();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rentACarObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFileJSON() {
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, RentACarObject.class);
            rentACarObjects = objectMapper.readValue(file, type);
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
