<%@page import="java.sql.*"%>
<%@page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="org.apache.commons.fileupload.servlet.*" %>
<%@page import="org.apache.commons.io.output.*" %>
<%@include file="session.jsp"%>
<%@include file="database.jsp"%>

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
	String filePath = "C:\\xampp\\tomcat\\webapps\\tugasku\\src\\";
	String filePathEnd = "uploads/";
	int idTask = 0;

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
		Iterator it = fileItems.iterator();

		int id = 0;
		String judul = null;
		String filem = null;
		String deadline = null;
		String time = null;
		String asignee = null;
		String tag = null;
		String relFileName = "";

		while (it.hasNext())
		{
			FileItem fi = (FileItem)it.next();
			if (!fi.isFormField())
			{
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
					out.print(relFileName);
					relFileName = filePathEnd + fileName.substring(fileName.lastIndexOf("\\") + 1);
				}
			}
			else
			{
				String name = fi.getFieldName();
				if (name.equals("id"))
				{
					id = Integer.parseInt(fi.getString());
				}
				else if (name.equals("judul"))
				{
					judul = fi.getString();
				}
				else if (name.equals("file"))
				{
					filem = fi.getString();
				}
				else if (name.equals("deadline"))
				{
					deadline = fi.getString();
				}
				else if (name.equals("time"))
				{
					time = fi.getString();
				}
				else if (name.equals("asignee"))
				{
					asignee = fi.getString();
				}
				else if (name.equals("tag"))
				{
					tag = fi.getString();
				}
			}
		}

		java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String timestamp = dateFormat.format(date);

		int creator = (Integer)session.getAttribute("id");

		PreparedStatement pst2 = con.prepareStatement("INSERT INTO `tasks` (name, creator, deadline, category, timestamp) VALUES (?, ?, ?, ?, ?)");
		pst2.setString(1, judul);
		pst2.setInt(2, creator);
		pst2.setString(3, deadline+" "+time);
		pst2.setInt(4, id);
		pst2.setString(5, timestamp);
		pst2.executeUpdate();
		pst2.close();

		PreparedStatement pst3 = con.prepareStatement("SELECT id FROM `tasks` WHERE name='"+judul+"'");
		ResultSet rs3 = pst3.executeQuery();
		rs3.next();
		idTask = rs3.getInt(1);
		rs3.close();
		pst3.close();

		String[] member = asignee.split(",");
		int i = 0;
		int j = member.length;
		while (i < j)
		{
			member[i] = member[i].trim();
			PreparedStatement pst4 = con.prepareStatement("SELECT * FROM `members` WHERE username='"+member[i]+"'");
			ResultSet rs4 = pst4.executeQuery();
			rs4.next();
			int idMember = rs4.getInt(1);
			rs4.close();
			PreparedStatement pst5 = con.prepareStatement("INSERT INTO `assignees` (member, task, finished) VALUES (?, ?, 0)");
			pst5.setInt(1, idMember);
			pst5.setInt(2, idTask);
			pst5.executeUpdate();
			pst5.close();
			i++;
		}

		PreparedStatement pst6 = con.prepareStatement("INSERT INTO `assignees` (member, task, finished) VALUES (?, ?, 0)");
		pst6.setInt(1, creator);
		pst6.setInt(2, idTask);
		pst6.executeUpdate();
		pst6.close();

		String[] tagx = tag.split(",");
		i = 0;
		j = tagx.length;

		while (i < j)
		{
			tagx[i] = tagx[i].trim();
			PreparedStatement pst7 = con.prepareStatement("INSERT INTO `tags` (name, tagged) VALUES (?, ?)");
			pst7.setString(1, tagx[i]);
			pst7.setInt(2, idTask);
			pst7.executeUpdate();
			pst7.close();
			i++;
		}

		PreparedStatement pst8 = con.prepareStatement("INSERT INTO `attachments` (path, filetype, task) VALUES (?, ?, ?)");
		pst8.setString(1, relFileName);
		pst8.setString(2, filem);
		pst8.setInt(3, idTask);
		pst8.executeUpdate();
		pst8.close();
	}

	con.close();
	response.sendRedirect("rinciantugas.jsp?id="+idTask);
}
catch(Exception ex)
{
	out.print("Error : "+ex.getMessage());
}
%>