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

	int task_id = Integer.parseInt(request.getParameter("deltask"));
	PreparedStatement pst1 = con.prepareStatement("DELETE FROM `tasks` WHERE id=?");
	pst1.setInt(1, task_id);
	pst1.executeUpdate();
	pst1.close();

	con.close();
	response.sendRedirect("dashboard.jsp");
}
catch (Exception ex)
{
	out.print("Error : " + ex.getMessage());
}
%>