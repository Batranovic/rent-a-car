package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import dao.UserDAO;
import dto.LoginDTO;
import dto.RegisterUserDTO;
import dto.SimpleUserDTO;
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
            ProjectDAO.ctxPath = contextPath;
            ProjectDAO.startProject();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAll(){
    	return UserDAO.getInstance().getAll();
    }

    @POST
    @Path("/createUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RegisterUserDTO createUser(RegisterUserDTO userDTO) {
    	User user = UserDAO.getInstance().createCustomer(userDTO);
    	if(user == null) {
    		return null;
    	}
    	
        return RegisterUserDTO.convertToDTO(user);
    }

    @POST
    @Path("/loginUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RegisterUserDTO loginUser(LoginDTO dto, @Context HttpServletRequest request) {
    	User user = UserDAO.getInstance().login(dto);
    	if(user == null) {
    		return null;
    	}
    	request.getSession().setAttribute("user", user);
        return RegisterUserDTO.convertToDTO(user);
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
    @GET
    @Path("/getFreeManagers")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<SimpleUserDTO> getFreeManagers(){
    	ArrayList<User> freeManagers = UserDAO.getInstance().getAllFreeManagers();
    	ArrayList<SimpleUserDTO> dtos = new ArrayList<SimpleUserDTO>();
    	for(User user : freeManagers) {
    		dtos.add(SimpleUserDTO.ConvertSimpleUserDTO(user));
    	}
    	return dtos;
    }

    
 

}