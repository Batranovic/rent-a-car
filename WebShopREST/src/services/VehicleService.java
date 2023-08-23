package services;

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
import dto.ObjectCreationDTO;
import dto.RegisterUserDTO;
import model.RentACarObject;

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


}
