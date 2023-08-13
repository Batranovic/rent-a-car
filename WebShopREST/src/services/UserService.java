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

import dao.UserDAO;
import model.User;

@Path("/users")
public class UserService {
    @Context
    ServletContext ctx;

    public UserService() {

    }

    @PostConstruct
    public void init() {
        if(ctx.getAttribute("UserDAO") == null) {
        	String contextPath = ctx.getRealPath("");
            ctx.setAttribute("UserDAO", new UserDAO(contextPath));
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAll(){
    	UserDAO dao = (UserDAO) ctx.getAttribute("UserDAO");
        return dao.getAll();
    }

    @POST
    @Path("/createUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
    	UserDAO dao = (UserDAO) ctx.getAttribute("UserDAO");
        return dao.create(user);
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(@PathParam("id") int id, User user) {
    	UserDAO dao = (UserDAO) ctx.getAttribute("UserDAO");
        return dao.update(id, user);
    }

    @GET
    @Path("/searchByUsername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User searchByUsername(@PathParam("username") String username) {
    	UserDAO dao = (UserDAO) ctx.getAttribute("UserDAO");
        return dao.getByUsername(username);
    }
    
 

}