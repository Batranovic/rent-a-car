package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.RentACarObject;

public class RentACarObjectDAO {
    private ArrayList<RentACarObject> rentACarObjects;
    private final ObjectMapper objectMapper;
    private final File file;

    public RentACarObjectDAO() {
        objectMapper = new ObjectMapper();
        rentACarObjects = new ArrayList<RentACarObject>();
        String filePath = "..\\..\\src\\resources\\rentACarObjects.JSON";
        file = new File(filePath);

        readFromFileJSON();
    }

    public RentACarObject searchRentACarObject(String name, String location, String type, String grade) {
        for (RentACarObject r : rentACarObjects) {
            if (r.getName().equals(name) && r.getLocation().equals(location) && r.getGrade().equals(grade))
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
