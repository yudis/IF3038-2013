<%@page import="java.sql.*"%>
<%@include file="session.jsp"%>
<%@include file="database.jsp"%>

<%
try
{
	// Connect to server and select database
	String url = "jdbc:mysql://"+host+":3306/"+db_name;
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url, username, password);

	int id_task = Integer.parseInt(request.getParameter("id"));
	
	String deadlinedate = request.getParameter("inputdeadline");
	int deadlinehour = Integer.parseInt(request.getParameter("hour"));
	int deadlineminute = Integer.parseInt(request.getParameter("minute"));
	int deadlinesecond = Integer.parseInt(request.getParameter("second"));
	String deadlinestring = deadlinedate + " " + deadlinehour + ":" + deadlineminute + ":" + deadlinesecond;
	
	String assignee = request.getParameter("inputassignee");
	String[] assignees = assignee.split(",");
	int count_assignee = assignees.length;

	String tag = request.getParameter("inputtag");
	String[] tags = tag.split(",");
	int count_tag = tags.length;

	// Update deadline
	PreparedStatement pst1 = con.prepareStatement("UPDATE `tasks` SET deadline=? WHERE id=?");
	pst1.setString(1, deadlinestring);
	pst1.setInt(2, id_task);
	pst1.executeUpdate();
	pst1.close();

	//Update assignee
	PreparedStatement pst2 = con.prepareStatement("DELETE FROM `assignees` WHERE task=?");
	pst2.setInt(1, id_task);
	pst2.executeUpdate();
	pst2.close();
	for (int i = 0; i < count_assignee; i++)
	{
		String current = assignees[i].trim();
		PreparedStatement pst5 = con.prepareStatement("SELECT * FROM `members` WHERE username='"+current+"'");
		ResultSet rs5 = pst5.executeQuery();
		rs5.next();
		int id_user = rs5.getInt(1);
		rs5.close();
		pst5.close();
		PreparedStatement pst7 = con.prepareStatement("INSERT INTO `assignees` VALUES (?, ?, 0)");
		pst7.setInt(1, id_user);
		pst7.setInt(2, id_task);
		pst7.executeUpdate();
		pst7.close();
	}

	//Update tag
	PreparedStatement pst8 = con.prepareStatement("DELETE FROM `tags` WHERE tagged=?");
	pst8.setInt(1, id_task);
	pst8.executeUpdate();
	pst8.close();
	for (int i = 0; i < count_tag; i++)
	{
		String current = tags[i].trim();
		PreparedStatement pst9 = con.prepareStatement("INSERT INTO `tags` VALUES (?, ?)");
		pst9.setString(1, current);
		pst9.setInt(2, id_task);
		pst9.executeUpdate();
		pst9.close();
	}

	con.close();
	response.sendRedirect("rinciantugas.jsp?id="+id_task);
}
catch (Exception ex)
{
	out.print("Error : " + ex.getMessage());
}
%>