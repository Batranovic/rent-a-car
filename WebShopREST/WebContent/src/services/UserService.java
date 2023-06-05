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

import dao.UserDao;
import model.User;

@Path("/users")
public class UserService {
    @Context
    ServletContext ctx;

    public UserService() {

    }

    @PostConstruct
    public void init() {
        if(ctx.getAttribute("userDao") == null) {
            ctx.setAttribute("userDao", new UserDao());
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAll(){
        UserDao dao = (UserDao) ctx.getAttribute("userDao");
        return dao.getAll();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        UserDao dao = (UserDao) ctx.getAttribute("userDao");
        return dao.create(user);
    }


    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateBook(User user) {
        UserDao dao = (UserDao) ctx.getAttribute("userDao");
        return dao.updateUser(user);
    }

    @GET
    @Path("/searchByUsername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User searchBook(@PathParam("username") String username) {
        UserDao dao = (UserDao) ctx.getAttribute("userDao");
        return dao.searchUserByUsername(username);
    }
}