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
import javax.ws.rs.core.Response;

import dto.UserDTO;
import dao.ProjectDAO;
import dao.RentACarObjectDAO;
import dao.UserDAO;
import dto.DetailedRentACarDTO;
import dto.LoginDTO;
import dto.ManagerCreationForObjectDTO;
import dto.RegisterUserDTO;
import dto.RentACarDTO;
import dto.SearchDTO;
import dto.SimpleUserDTO;
import model.RentACarObject;
import model.User;
import dto.SearchUserDTO;

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
            ProjectDAO.getInstance();
        }
    }

   
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserDTO> getAllUsers(){
    	ArrayList<User> users = UserDAO.getInstance().getAll();
    	ArrayList<UserDTO> dtos = new ArrayList<UserDTO>();
    	for(User user : users) {
    		dtos.add(UserDTO.toObject(user));
    	}
    	
        return dtos;
    	
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
    @Path("/createManager")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ManagerCreationForObjectDTO createManager(ManagerCreationForObjectDTO managerDTO) {
    	User user = UserDAO.getInstance().createManager(managerDTO);
    	if(user == null) {
    		return null;
    	}
    	
        return ManagerCreationForObjectDTO.ConvertToDTO(user);
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


  

    @GET
    @Path("/searchByUsername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO searchByUsername(@PathParam("username") String username) {
    	User user = UserDAO.getInstance().getByUsername(username);
    	UserDTO dto = UserDTO.toObject(user);
    	return dto;
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
    
    
    @POST
    @Path("/searchUser")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserDTO> searchUser(SearchUserDTO searchDTO) {
    	ArrayList<User> users = UserDAO.getInstance().searchUser(searchDTO);
    	ArrayList<UserDTO> dtos = new ArrayList<UserDTO>();
    	for(User user : users) {
    		dtos.add(UserDTO.toObject(user));
    	}
    	
        return dtos;
    }
    
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO updateUser(@PathParam("id") int id, UserDTO updatedUserDTO) {
    	User user = UserDAO.getInstance().update(id, updatedUserDTO);
    	UserDTO dto = UserDTO.toObject(user);
    	return dto;
    }

    
 

}