package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.ProjectDAO;
import dao.RentACarObjectDAO;
import dao.UserDAO;
import dto.ObjectCreationDTO;
import dto.RegisterUserDTO;
import model.RentACarObject;
import model.User;

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
    @Path("/searchRentACarObject/{name}/{location}/{type}/{grade}")
    @Produces(MediaType.APPLICATION_JSON)
    public RentACarObject searchRentACarObject(@PathParam("name") String name, @PathParam("location") String location, @PathParam("type") String type, @PathParam("grade") double grade ) {
    	RentACarObjectDAO dao = (RentACarObjectDAO) ctx.getAttribute("rentACarObjectDAO");
        return dao.searchRentACarObject(name, location, type, grade);
    }
    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RegisterUserDTO createUser(ObjectCreationDTO objectDTO) {
    	RentACarObject rentACarObject = RentACarObjectDAO.getInstance().create(objectDTO);
    	if(rentACarObject == null) {
    		return null;
    	}
    	
        return RegisterUserDTO.convertToDTO(user);
    }

}