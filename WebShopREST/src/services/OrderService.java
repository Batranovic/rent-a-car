package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.OrderDAO;
import dao.ProjectDAO;
import dao.RentACarObjectDAO;
import dto.OrderDTO;
import dto.RentACarDTO;
import dto.SearchDTO;
import dto.SearchOrderDTO;
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

    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<OrderDTO> searchOrder(SearchOrderDTO searchDTO) {
    	ArrayList<Order> orders = OrderDAO.getInstance().searchOrder(searchDTO);
    	ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
    	for(Order order : orders) {
    		dtos.add(OrderDTO.toObject(order) );
    	}
    	
        return dtos;
    }
    
}
