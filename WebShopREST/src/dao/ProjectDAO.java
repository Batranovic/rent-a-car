package dao;

public class ProjectDAO {
	public static String ctxPath;
	
	private static ProjectDAO instance = null;
	
	private ProjectDAO() {
		startProject();
	}
	
	public static ProjectDAO getInstance() {
		if(instance == null) {
			instance = new ProjectDAO();
		}
		return instance;
	}
	
	
	private void startProject() {
	
		UserDAO.getInstance();
		RentACarObjectDAO.getInstance();
		BasketDAO.getInstance();
		CustomerDAO.getInstance();
		LocationDAO.getInstance();
		VehicleDAO.getInstance();
		
		UserDAO.getInstance().bindRentACarObject();
		UserDAO.getInstance().bindCustomer();
		RentACarObjectDAO.getInstance().bindLocation();	
		VehicleDAO.getInstance().bindRentACarObject();
		BasketDAO.getInstance().bindUser();
		BasketDAO.getInstance().bindVehicle();
		OrderDAO.getInstance().bindRentACarObject();
		OrderDAO.getInstance().bindUser();
		CommentDAO.getInstance().bindRentACarObject();
		CommentDAO.getInstance().bindUser();
		
		
	}
}
