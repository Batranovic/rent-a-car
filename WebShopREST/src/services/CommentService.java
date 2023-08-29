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

import dao.CommentDAO;
import dao.ProjectDAO;
import dto.CommentCreationDTO;
import dto.CommentDTO;
import model.Comment;


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
	        	ProjectDAO.getInstance();
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
	    
	    @GET
	    @Path("/getAcceptedCommentsForRentObject/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public ArrayList<CommentDTO> getAcceptedCommentsForRentObject(@PathParam("id") int id){
	    	ArrayList<Comment> comments = CommentDAO.getInstance().getAcceptedCommentsForRentObject(id);
	    	ArrayList<CommentDTO> dtos = new ArrayList<CommentDTO>();
	    	for(Comment comment : comments) {
	    		dtos.add(CommentDTO.toObject(comment) );
	    	}
	    	
	        return dtos;
	    	
	    }
	    
	    @POST
	    @Path("/")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public CommentCreationDTO createComment(CommentCreationDTO commentDTO, @Context HttpServletRequest request) {
	    	
	    	Comment comment = CommentDAO.getInstance().createComment(commentDTO);
	    	if(comment == null) {
	    		return null;
	    	}
	    	
	        return CommentCreationDTO.convertToDTO(comment);
	    }
	    
	    @PUT
	    @Path("/acceptComment/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public CommentCreationDTO acceptComment(@PathParam("id") int id) {
	    	
	    	Comment comment = CommentDAO.getInstance().acceptComment(id);
	    	if(comment == null) {
	    		return null;
	    	}
	    	
	        return CommentCreationDTO.convertToDTO(comment);
	    }
	
	    @PUT
	    @Path("/denyComment/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public CommentCreationDTO denyComment(@PathParam("id") int id) {
	    	
	    	Comment comment = CommentDAO.getInstance().denyComment(id);
	    	if(comment == null) {
	    		return null;
	    	}
	    	
	        return CommentCreationDTO.convertToDTO(comment);
	    }
}
