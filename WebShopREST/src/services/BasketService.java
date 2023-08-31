package services;

import java.util.ArrayList;

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

import dao.BasketDAO;
import dao.ProjectDAO;
import dto.AddToBasketDTO;
import dto.BasketDTO;
import dto.SimpleVehicleDTO;
import model.Basket;
import model.Vehicle;

@Path("/basket")
public class BasketService {
	@Context
	ServletContext ctx;

	public BasketService() {

	}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("BasketDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ProjectDAO.ctxPath = contextPath;
			ProjectDAO.getInstance();
		}
	}
	
	@GET
	@Path("/getBasketForUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public BasketDTO getBasketForUser(@PathParam("id") int id){
		Basket basket = BasketDAO.getInstance().getBasketForUser(id);
		BasketDTO dto = BasketDTO.convertToDTO(basket);
		return dto;
	}
	
	@GET
	@Path("/getAllVehicles/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SimpleVehicleDTO> getAllVehicles(@PathParam("id") int id){
		ArrayList<Vehicle> vehicles = BasketDAO.getInstance().getAllVehicesForBasket(id);
		ArrayList<SimpleVehicleDTO> dtos = new ArrayList<SimpleVehicleDTO>();
		for(Vehicle v : vehicles) {
			dtos.add(SimpleVehicleDTO.convertToDTO(v));
		}
		return dtos;
	}
	
	
	
	@POST
	@Path("/addToBasket")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BasketDTO addtoBasket(AddToBasketDTO addToBasketDTO) {
		Basket basket = BasketDAO.getInstance().getVehiclesForBasket(addToBasketDTO.getUserId(), addToBasketDTO.getVehicleId());
		BasketDTO dto = BasketDTO.convertToDTO(basket);
		return dto;
		
	}
}
