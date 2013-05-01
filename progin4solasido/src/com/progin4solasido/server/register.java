package com.progin4solasido.server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 
 * @author Asus
 */
public class register extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */

	DBConnector dbc = new DBConnector();

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/* TODO output your page here. You may use following sample code. */
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet register</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet register at " + request.getContextPath()
					+ "</h1>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String userid = null;
		String password = null;
		String name = null;
		String email = null;
		String avatar = null;

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {

		} else {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);

				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();

					if (item.isFormField()) {
						String name1 = item.getFieldName();
						String value = item.getString();
						if (name1.equals("DID")) {
							userid = value;

						} else if (name1.equals("DP")) {
							password = value;
						} else if (name1.equals("DName")) {
							name = value;
						} else if (name1.equals("DMail")) {
							email = value;
						}
					} else {
						try {
							String itemName = item.getName();
							avatar = itemName;
							File savedFile = new File(this.getServletConfig()
									.getServletContext().getRealPath("/")
									+ "images\\image\\" + avatar);
							item.write(savedFile);

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}

		try {
			dbc.Init();

			// int i =
			// dbc.ExecuteUpdate("insert into profil values ('"+userid+"','"+password+"','"+name+"','"+avatar+"','2111-11-12','"+email+"')");
			session.setAttribute("userid", userid);

			dbc.Close();
			response.sendRedirect("Dashboard.jsp");
		} catch (Exception e) {
			out.println(e);
		}

	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
