package com.progin4solasido.server;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import com.progin4solasido.model.Todo;

@Path("/todo")
public class TestResource {
	
	@GET
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public Todo getXML() {
	    Todo todo = new Todo();
	    todo.setSummary("This is my first todo");
	    todo.setDescription("This is my first todo");
	    return todo;
	  }
	  
	  // This can be used to test the integration with the browser
	  @GET
	  @Produces({ MediaType.TEXT_XML })
	  public Todo getHTML() {
	    Todo todo = new Todo();
	    todo.setSummary("This is my first todo");
	    todo.setDescription("This is my first todo");
	    return todo;
	  }
}
