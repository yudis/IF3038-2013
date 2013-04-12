<%@page import="java.sql.*"%>
<%@page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="org.apache.commons.fileupload.servlet.*" %>
<%@page import="org.apache.commons.io.output.*" %>

<%@include file="/session.jsp"%>
<%@include file="/database.jsp"%>

<%
try
{
	// Connect to server and select database
	String url = "jdbc:mysql://"+host+":3306/"+db_name;
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url, username, password);

	int user_id = (Integer)session.getAttribute("id");
	String fullname = request.getParameter("fullname");

	if (!request.getParameter("avatar").equals(""))
	{
		File file;
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		ServletContext context = pageContext.getServletContext();
		String filePath = "uploads/";

		// Verify content type
		String contentType = request.getContentType();
		if (contentType.indexOf("multipart/form-data") >= 0)
		{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxMemSize);
			// location to save data that is larger than maxMemSize
			factory.setRepository(new File("C:\\xampp\\tomcat\\temp"));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// maximum fiel size to be uploaded
			upload.setSizeMax(maxFileSize);

			// Parse the request to get file items
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			while (i.hasNext())
			{
				FileItem fi = (FileItem)i.next();
				if (!fi.isFormField())
				{
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					String relFileName = "";
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0)
					{
						relFileName = filePath + fileName.substring(fileName.lastIndexOf("\\"));
					}
					else
					{
						relFileName = filePath + fileName.substring(fileName.lastIndexOf("\\") + 1);
					}
					file = new File(relFileName);
					fi.write(file);

					// Update database
					PreparedStatement pst1 = con.prepareStatement("UPDATE `members` SET avatar=? WHERE id=?");
					pst1.setString(1, relFileName);
					pst1.setInt(2, user_id);
					pst1.executeUpdate();
					pst1.close();
					session.setAttribute("avatar",relFileName);
				}
			}
		}
	}

	String birthdate = request.getParameter("birthdate");
	String passsword = request.getParameter("passwd");
	String cpassword = request.getParameter("cpasswd");

	if (!fullname.equals((String)session.getAttribute("fullname")))
	{
		PreparedStatement pst2 = con.prepareStatement("UPDATE `members` SET fullname=? WHERE id=?");
		pst2.setString(1, fullname);
		pst2.setInt(2, user_id);
		pst2.executeUpdate();
		pst2.close();
		session.setAttribute("fullname",fullname);
	}

	if (!birthdate.equals((String)session.getAttribute("birthdate")))
	{
		PreparedStatement pst3 = con.prepareStatement("UPDATE `members` SET birthdate=? WHERE id=?");
		pst3.setString(1, birthdate);
		pst3.setInt(2, user_id);
		pst3.executeUpdate();
		pst3.close();
		session.setAttribute("birthdate",birthdate);
	}

	if (!passsword.equals(""))
	{
		if (passsword.equals(cpassword))
		{
			PreparedStatement pst4 = con.prepareStatement("UUPDATE `members` SET password=sha1(?) WHERE id=?");
			pst4.setString(1, passsword);
			pst4.setInt(2, user_id);
			pst4.executeUpdate();
			pst4.close();
		}
	}

	con.close();
	response.sendRedirect("profil.jsp");
}
catch (Exception ex)
{
	out.print("Error : " + ex.getMessage());
}
%>