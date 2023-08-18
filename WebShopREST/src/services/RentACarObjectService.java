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

import dao.RentACarObjectDAO;
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
            ctx.setAttribute("rentACarObjectDAO", new RentACarObjectDAO());
        }
    }


    @GET
    @Path("/searchRentACarObject/{name}/{location}/{type}/{grade}")
    @Produces(MediaType.APPLICATION_JSON)
    public RentACarObject searchRentACarObject(@PathParam("name") String name, @PathParam("location") String location, @PathParam("type") String type, @PathParam("grade") String grade ) {
    	RentACarObjectDAO dao = (RentACarObjectDAO) ctx.getAttribute("rentACarObjectDAO");
        return dao.searchRentACarObject(name, location, type, grade);
    }
    
 

}