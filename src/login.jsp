<%@page import="java.sql.*"%>
<%@include file="database.jsp"%>

<%
try
{
	// Connect to server and select database.
	String url = "jdbc:mysql://"+host+":3306/"+db_name;
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url, username, password);

	// username and password sent from form 
	String myusername = request.getParameter("inputusername");
	String mypassword = request.getParameter("inputpass");
	PreparedStatement pst = con.prepareStatement("SELECT * FROM `members` WHERE username='"+myusername+"' and password=sha1('"+mypassword+"')");
    ResultSet rs = pst.executeQuery();

	if (rs.next())
	{
		session.setAttribute("myusername",myusername);
		session.setAttribute("id",rs.getInt(1));
		session.setAttribute("fullname",rs.getString(4));
		session.setAttribute("birthdate",rs.getString(5));
		session.setAttribute("email",rs.getString(6));
		session.setAttribute("avatar",rs.getString(7));
		session.setAttribute("gender",rs.getString(8));
		session.setAttribute("about",rs.getString(9));
		rs.close();
		pst.close();
		con.close();
		response.sendRedirect("dashboard.jsp");
	}
	else
	{
		rs.close();
		pst.close();
		con.close();
		response.sendRedirect("index.jsp?status=1");
	}
}
catch (SQLException ex)
{
	out.print("Error : " + ex.getMessage());
}
%>