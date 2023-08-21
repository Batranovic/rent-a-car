package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.ProjectDAO;
import dao.VehicleDAO;
import dto.RegisterUserDTO;
import dto.VehicleCreationDTO;
import model.User;
import model.Vehicle;

public class VehicleService {
	@Context
    ServletContext ctx;

    public VehicleService() {

    }

    @PostConstruct
    public void init() {
        if(ctx.getAttribute("VehicleDAO") == null) {
        	String contextPath = ctx.getRealPath("");
        	ProjectDAO.ctxPath = contextPath;
        	ProjectDAO.startProject();
        }
    }

    
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public VehicleCreationDTO createUser(VehicleCreationDTO vehicleDTO, @Context HttpServletRequest request) {
    	User loggedUser = (User) request.getSession().getAttribute("user");
    	if(loggedUser == null) {
    		return null;
    	}
    	Vehicle vehicle = VehicleDAO.getInstance().create(vehicleDTO, loggedUser);
    	if(vehicle == null) {
    		return null;
    	}
    	
        return VehicleCreationDTO.convertToDTO(vehicle);
    }
}
