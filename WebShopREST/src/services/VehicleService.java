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
import dao.RentACarObjectDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import dto.RentACarDTO;
import dto.SearchDTO;
import dto.SearchFreeVehiclesDTO;
import dto.UserDTO;
import dto.VehicleCreationDTO;
import model.RentACarObject;
import model.User;
import model.Vehicle;

@Path("/vehicles")
public class VehicleService {
	@Context
	ServletContext ctx;

	public VehicleService() {

	}

	@PostConstruct
	public void init() {
		String contextPath = ctx.getRealPath("");
		ProjectDAO.ctxPath = contextPath;
		ProjectDAO.getInstance();

	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public VehicleCreationDTO createUser(VehicleCreationDTO vehicleDTO, @Context HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("user");
		if (loggedUser == null) {
			return null;
		}
		Vehicle vehicle = VehicleDAO.getInstance().create(vehicleDTO, loggedUser);
		if (vehicle == null) {
			return null;
		}

		return VehicleCreationDTO.convertToDTO(vehicle);
	}

	@PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public VehicleCreationDTO updateUser(@PathParam("id") int id, VehicleCreationDTO updatedVehicle) {
		Vehicle vehicle = VehicleDAO.getInstance().update(id, updatedVehicle);
		VehicleCreationDTO dto = VehicleCreationDTO.convertToDTO(vehicle);
		return dto;
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<VehicleCreationDTO> getAllVehicles() {
		ArrayList<Vehicle> vehicles = VehicleDAO.getInstance().getAll();

		ArrayList<VehicleCreationDTO> dtos = new ArrayList<VehicleCreationDTO>();
		for (Vehicle v : vehicles) {
			dtos.add(VehicleCreationDTO.convertToDTO(v));
		}

		return dtos;

	}

	@GET
	@Path("/getVehicle/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public VehicleCreationDTO getVehicle(@PathParam("id") int id) {
		Vehicle vehicle = VehicleDAO.getInstance().getById(id);
		return VehicleCreationDTO.convertToDTO(vehicle);
	}

	@POST
	@Path("/searchVehicle")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<VehicleCreationDTO> searchVehicle(SearchFreeVehiclesDTO searchDTO) {
		ArrayList<Vehicle> freeVehicles = VehicleDAO.getInstance().search(searchDTO);
		if (freeVehicles == null) {
			return null;
		}
		ArrayList<VehicleCreationDTO> dtos = new ArrayList<VehicleCreationDTO>();
		for (Vehicle v : freeVehicles) {
			dtos.add(VehicleCreationDTO.convertToDTO(v));
		}
		return dtos;
	}
}
