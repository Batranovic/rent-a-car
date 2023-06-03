package services;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/users")
public class UserService {
	@Context
	ServletContext ctx;
	
	public UserService() {
		
	}
}
