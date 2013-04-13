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

	int user = (Integer)session.getAttribute("id");
	int task = Integer.parseInt(request.getParameter("task"));
	int stat = Integer.parseInt(request.getParameter("stat"));

	PreparedStatement pst1 = con.prepareStatement("UPDATE `assignees` SET finished=? WHERE member=? AND task=?");
	pst1.setInt(1, stat);
	pst1.setInt(2, user);
	pst1.setInt(3, task);
	pst1.executeUpdate();
	pst1.close();

	if (stat == 1)
	{
		out.print("Status : <strong>Selesai</strong><br />");
		out.print("<input name='YourChoice' type='checkbox' value='selesai' checked onclick=change_status('"+task+"',"+stat+","+task+")> Selesai");
	}
	else if (stat == 0)
	{
		out.print("Status : <strong>Belum selesai</strong><br />");
		out.print("<input name='YourChoice' type='checkbox' value='selesai' onclick=change_status('"+task+"',"+stat+","+task+")> Selesai");
	}
	con.close();
}
catch (Exception ex)
{
	out.print("Error : " + ex.getMessage());
}
%>