package com.progin4solasido.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestResourceClient extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("tes resource");
		out.println(process());
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
	public static String process() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		// Get XML
		System.out.println(service.path("rest").path("todo")
				.accept(MediaType.TEXT_XML).get(String.class));
		// Get XML for application
		System.out.println(service.path("rest").path("todo")
				.accept(MediaType.APPLICATION_JSON).get(String.class));
		// Get JSON for application
		System.out.println(service.path("rest").path("todo")
				.accept(MediaType.APPLICATION_XML).get(String.class));
		return service.path("rest").path("todo")
				.accept(MediaType.APPLICATION_JSON).get(String.class);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://progin4solasido.appspot.com").build();
	}
}
