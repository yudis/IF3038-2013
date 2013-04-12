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

	String category_name = request.getParameter("category_name");
	String relateduser = request.getParameter("relateduser");
	int creator_id = Integer.parseInt(request.getParameter("creator_id"));

	PreparedStatement pst1 = con.prepareStatement("INSERT INTO `categories` (name, creator) VALUES (?, ?)");
	pst1.setString(1, category_name);
	pst1.setInt(2, creator_id);
	pst1.executeUpdate();
	pst1.close();

	PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `categories` WHERE name='"+category_name+"'");
	ResultSet rs2 = pst2.executeQuery();
	rs2.next();
	int idCategory = rs2.getInt(1);
	rs2.close();
	pst2.close();

	PreparedStatement pst3 = con.prepareStatement("INSERT INTO `editors` (member, category) VALUES (?, ?)");
	pst3.setInt(1, creator_id);
	pst3.setInt(2, idCategory);
	pst3.executeUpdate();
	pst3.close();

	String[] member = relateduser.split(",");
	int i = 0;
	int j = member.length;
	PreparedStatement pst5 = con.prepareStatement("INSERT INTO `editors` (member, category) VALUES (?, ?)");
	while (i < j)
	{
		member[i] = member[i].trim();
		PreparedStatement pst4 = con.prepareStatement("SELECT * FROM `members` WHERE username='"+member[i]+"'");
		ResultSet rs4 = pst4.executeQuery();
		rs4.next();
		int idMember = rs4.getInt(1);
		rs4.close();
		pst4.close();

		pst5.setInt(1, idMember);
		pst5.setInt(2, idCategory);
		pst5.executeUpdate();

		i++;
	}
	pst5.close();
	
	con.close();
	response.sendRedirect("dashboard.jsp");
}
catch (Exception ex)
{
	out.print("Error : " + ex.getMessage());
}
%>
