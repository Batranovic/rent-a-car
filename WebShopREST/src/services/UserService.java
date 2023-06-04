package services;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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

    public void init(){
        if (ctx.getAttribute("userDao") == null) {
            String contextPath = ctx.getRealPath("");
            ctx.setAttribute("userDao", new UserDAO(contextPath));
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAllUsers(){
        UserDAO dao = (UserDAO) ctx.getAttribute("userDao");
        return dao.getAllUsers();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User create(User user) {
        UserDAO dao = (UserDAO) ctx.getAttribute("userDao");
        return dao.create(user);
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        UserDAO dao = (UserDAO) ctx.getAttribute("userDao");
        return dao.updateUser(0, user);
    }

    @GET
    @Path("/searchById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User searchById(@PathParam("id") int id) {
        UserDAO dao = (UserDAO) ctx.getAttribute("userDao");
        return dao.searchById(id);
    }

}
