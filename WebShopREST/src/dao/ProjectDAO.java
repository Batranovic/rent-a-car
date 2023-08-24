package dao;

public class ProjectDAO {
	public static String ctxPath;
	
	public static void startProject() {
		
		UserDAO.getInstance();
		RentACarObjectDAO.getInstance();
		BasketDAO.getInstance();
		CustomerDAO.getInstance();
		LocationDAO.getInstance();
		VehicleDAO.getInstance();
		
		UserDAO.getInstance().bindBasket();
		UserDAO.getInstance().bindCustomer();
		UserDAO.getInstance().bindRentACarObject();
		RentACarObjectDAO.getInstance().bindLocation();	
		VehicleDAO.getInstance().bindRentACarObject();
		
	}
}
