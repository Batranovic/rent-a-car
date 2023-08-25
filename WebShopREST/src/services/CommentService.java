package services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.ProjectDAO;
import dao.RentACarObjectDAO;
import dto.CommentDTO;
import dto.RentACarDTO;
import dao.CommentDAO;
import model.Comment;
import model.RentACarObject;


@Path("/comments")
public class CommentService {
	
		@Context
	    ServletContext ctx;

	    public CommentService() {

	    }

	    @PostConstruct
	    public void init() {
	        if(ctx.getAttribute("commentDAO") == null) {
	        	String contextPath = ctx.getRealPath("");
	        	ProjectDAO.ctxPath = contextPath;
	        	ProjectDAO.startProject();
	        }
	    }
	    
	    @GET
	    @Path("/getCommentsForRentObject/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public ArrayList<CommentDTO> getAllCommentsForRentObject(@PathParam("id") int id){
	    	ArrayList<Comment> comments = CommentDAO.getInstance().getAllCommentsForRentObject(id);
	    	ArrayList<CommentDTO> dtos = new ArrayList<CommentDTO>();
	    	for(Comment comment : comments) {
	    		dtos.add(CommentDTO.toObject(comment) );
	    	}
	    	
	        return dtos;
	    	
	    }
	
}
