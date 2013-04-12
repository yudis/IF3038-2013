<%@page import="java.sql.*"%>
<%@include file="/session.jsp"%>
<%@include file="/database.jsp"%>

<%
try
{
	// Connect to server and select database
	String url = "jdbc:mysql://"+host+":3306/"+db_name;
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url, username, password);

	int cat_id = Integer.parseInt(request.getParameter("id"));
	PreparedStatement pst1 = con.prepareStatement("DELETE FROM `categories` WHERE id=?");
	pst1.setInt(1, cat_id);
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