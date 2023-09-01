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

import dao.CommentDAO;
import dao.OrderDAO;
import dao.ProjectDAO;
import dao.RentACarObjectDAO;
import dto.CommentCreationDTO;
import dto.CommentDTO;
import dto.OrderDTO;
import dto.RentACarDTO;
import dto.SearchDTO;
import dto.SearchOrderDTO;
import model.Comment;
import model.Order;
import model.RentACarObject;

@Path("/orders")
public class OrderService {
	@Context
	ServletContext ctx;

	public OrderService() {

	}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("OrderDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ProjectDAO.ctxPath = contextPath;
			ProjectDAO.getInstance();
		}
	}

	@GET
	@Path("/getOrderForUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OrderDTO> getAllOrdersForUser(@PathParam("id") int userId) {
		ArrayList<Order> orders = OrderDAO.getInstance().getAllOrdersForUser(userId);
		ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
		for (Order order : orders) {
			dtos.add(OrderDTO.toObject(order));
		}

		return dtos;

	}
	
	   @GET
	    @Path("/getOrdersForRentObject/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public ArrayList<OrderDTO> getAllOrdersForRentObject(@PathParam("id") int id){
	    	ArrayList<Order> orders = OrderDAO.getInstance().getAllOrdersForObject(id);
	    	ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
	    	for(Order order : orders) {
	    		dtos.add(OrderDTO.toObject(order) );
	    	}
	    	
	        return dtos;
	    	
	    }
	    

    @POST
    @Path("/search/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<OrderDTO> searchOrder(@PathParam("id") int id, SearchOrderDTO searchDTO) {
    	ArrayList<Order> orders = OrderDAO.getInstance().searchOrder(searchDTO, id);
    	ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
    	for(Order order : orders) {
    		dtos.add(OrderDTO.toObject(order) );
    	}
    	
        return dtos;
    }
    
    @POST
    @Path("/searchForManagerOrder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<OrderDTO> searchForManagerOrder(@PathParam("id") int id, SearchOrderDTO searchDTO) {
    	ArrayList<Order> orders = OrderDAO.getInstance().searchManagerOrder(searchDTO, id);
    	ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
    	for(Order order : orders) {
    		dtos.add(OrderDTO.toObject(order) );
    	}
    	
        return dtos;
    }
    
<<<<<<< HEAD
    @PUT
    @Path("/acceptOrder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDTO acceptComment(@PathParam("id") int id) {
    	
    	Order order = OrderDAO.getInstance().acceptOrder(id);
    	if(order == null) {
    		return null;
    	}
    	
        return OrderDTO.toObject(order);
    }

    @PUT
    @Path("/collectOrder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDTO collectOrder(@PathParam("id") int id) {
    	
    	Order order = OrderDAO.getInstance().collectOrder(id);
    	if(order == null) {
    		return null;
    	}
    	
        return OrderDTO.toObject(order);
    }
    
    @PUT
    @Path("/returnOrder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDTO returnOrder(@PathParam("id") int id) {
    	
    	Order order = OrderDAO.getInstance().returnOrder(id);
    	if(order == null) {
    		return null;
    	}
    	
        return OrderDTO.toObject(order);
    }
    
    @PUT
    @Path("/denyOrder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDTO denyOrder(@PathParam("id") int id) {
    	
    	Order order = OrderDAO.getInstance().denyOrder(id);
    	if(order == null) {
    		return null;
    	}
    	
        return OrderDTO.toObject(order);
    }
    @PUT 
    @Path("/quitOrder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void quitOrder(@PathParam("id") int id) {
    	OrderDAO.getInstance().quitOrder(id);
    }
}
