package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.ProjectDAO;
import dao.RentACarObjectDAO;
import dto.DetailedRentACarDTO;
import dto.ObjectCreationDTO;
import dto.RentACarDTO;
import dto.SearchDTO;
import model.RentACarObject;

@Path("/rentACarObjects")
public class RentACarObjectService {
    @Context
    ServletContext ctx;

    public RentACarObjectService() {

    }

    @PostConstruct
    public void init() {
        if(ctx.getAttribute("rentACarObjectDAO") == null) {
        	String contextPath = ctx.getRealPath("");
        	ProjectDAO.ctxPath = contextPath;
        	ProjectDAO.startProject();
        }
    }

    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RentACarDTO> getAll(){
    	ArrayList<RentACarObject> rentACarObjects = RentACarObjectDAO.getInstance().getAll();
    	ArrayList<RentACarDTO> dtos = new ArrayList<RentACarDTO>();
    	for(RentACarObject rentACarObject : rentACarObjects) {
    		dtos.add(RentACarDTO.toObject(rentACarObject) );
    	}
    	
        return dtos;
    	
    }
    
    @GET
    @Path("/getOneDetailed/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DetailedRentACarDTO getOneDetailed(@PathParam("id") int id){
    	RentACarObject rentACarObject = RentACarObjectDAO.getInstance().getById(id);
    	DetailedRentACarDTO dto = DetailedRentACarDTO.toObject(rentACarObject);
    	
        return dto;
    	
    }

    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<RentACarDTO> searchRentACarObject(SearchDTO searchDTO) {
    	ArrayList<RentACarObject> rentACarObjects = RentACarObjectDAO.getInstance().searchRentACarObject(searchDTO);
    	ArrayList<RentACarDTO> dtos = new ArrayList<RentACarDTO>();
    	for(RentACarObject rentACarObject : rentACarObjects) {
    		dtos.add(RentACarDTO.toObject(rentACarObject) );
    	}
    	
        return dtos;
    }
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ObjectCreationDTO createUser(ObjectCreationDTO objectDTO) {
    	RentACarObject rentACarObject = RentACarObjectDAO.getInstance().create(objectDTO);
    	if(rentACarObject == null) {
    		return null;
    	}
    	
        return ObjectCreationDTO.convertToDTO(rentACarObject);
    }



}