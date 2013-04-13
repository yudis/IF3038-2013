<%@page import="java.sql.*"%>
<%@page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="org.apache.commons.fileupload.servlet.*" %>
<%@page import="org.apache.commons.io.output.*" %>
<%@include file="/database.jsp"%>

<%
try
{
	// Connect to server and select database
	String url = "jdbc:mysql://"+host+":3306/"+db_name;
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url, username, password);

	File file;
	int maxFileSize = 5000 * 1024;
	int maxMemSize = 5000 * 1024;
	ServletContext context = pageContext.getServletContext();
	String filePath = "C:\\xampp\\tomcat\\webapps\\tugasku\\";
	String filePathEnd = "avatars/";

	String contentType = request.getContentType();
	String passsword = null;
	String relFileName = "";
	String usernamee = "";
	String fullname = "";
	String birthdate = "";
	String email = "";
	String gender = "";
	String about = "";
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
		Iterator it = fileItems.iterator();

		while (it.hasNext())
		{
			FileItem fi = (FileItem)it.next();
			if (!fi.isFormField())
			{
				out.print("start...");
				// Get the uploaded file parameters
				String fieldName = fi.getFieldName();
				String fileName = fi.getName();
				out.print("Filename: "+fileName);
				if (fileName != "")
				{
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0)
					{
						relFileName = filePath + filePathEnd + fileName.substring(fileName.lastIndexOf("\\"));
					}
					else
					{
						relFileName = filePath + filePathEnd + fileName.substring(fileName.lastIndexOf("\\") + 1);
					}
					file = new File(relFileName);
					fi.write(file);

					// Update database
					relFileName = filePathEnd + fileName.substring(fileName.lastIndexOf("\\") + 1);
				}
			}
			else
			{
				String name = fi.getFieldName();
				if (name.equals("username"))
				{
					usernamee = fi.getString();
				}
				else if (name.equals("password"))
				{
					passsword = fi.getString();
				}
				else if (name.equals("nama"))
				{
					fullname = fi.getString();
				}
				else if (name.equals("tgl"))
				{
					birthdate = fi.getString();
				}
				else if (name.equals("email"))
				{
					email = fi.getString();
				}
				else if (name.equals("sex"))
				{
					out.print(fi.getString());
					if (fi.getString().equals("male"))
					{
						gender = "M";
					}
					else if (fi.getString().equals("female"))
					{
						gender = "F";
					}
				}
				else if (name.equals("about"))
				{
					about = fi.getString();
				}
			}
		}

		PreparedStatement pst = con.prepareStatement("INSERT INTO `members` (username,password,fullname,birthdate,email,avatar,gender,about) VALUES (?,sha1(?),?,?,?,?,'"+gender+"',?)");
		pst.setString(1, usernamee);
		pst.setString(2, passsword);
		pst.setString(3, fullname);
		pst.setString(4, birthdate);
		pst.setString(5, email);
		pst.setString(6, relFileName);
		pst.setString(7, about);
		pst.executeUpdate();
		pst.close();
	}

	con.close();
	response.sendRedirect("index.jsp?status=4");
}
catch (Exception ex)
{
	out.print("Error : " + ex.getMessage());
}
%>