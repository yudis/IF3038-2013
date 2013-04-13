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

	int task = Integer.parseInt(request.getParameter("task"));
	int start = Integer.parseInt(request.getParameter("start"));
	int next = start + 10;

	PreparedStatement pst1 = con.prepareStatement("SELECT * FROM `comments` WHERE task="+task+" ORDER BY timestamp DESC LIMIT "+start+", 10");
	ResultSet rs1 = pst1.executeQuery();
	int count_comment = 0;
	java.util.ArrayList<Integer> comment_id = new java.util.ArrayList<Integer>();
	java.util.ArrayList<String> comment_timestamp = new java.util.ArrayList<String>();
	java.util.ArrayList<String> comment_comment = new java.util.ArrayList<String>();
	java.util.ArrayList<Integer> commenter_id = new java.util.ArrayList<Integer>();
	java.util.ArrayList<String> commenter_fullname = new java.util.ArrayList<String>();
	java.util.ArrayList<String> commenter_avatar = new java.util.ArrayList<String>();
	while (rs1.next())
	{
		comment_id.add(rs1.getInt(1));
		comment_timestamp.add(rs1.getString(4));
		comment_comment.add(rs1.getString(5));
		int id_commenter = rs1.getInt(2);
		PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `members` WHERE id="+id_commenter);
		ResultSet rs2 = pst2.executeQuery();
		rs2.next();
		commenter_id.add(rs2.getInt(1));
		commenter_fullname.add(rs2.getString(4));
		commenter_avatar.add(rs2.getString(7));
		count_comment++;
		rs2.close();
		pst2.close();
	}
	rs1.close();
	pst1.close();
	if (count_comment > 10) count_comment = 10;
	for (int i = 0; i < count_comment; i++)
	{
		out.print("<div class=\"komen-avatar\"><imag src=\""+commenter_avatar.get(i)+"\" height=\"24\"/></div>");
		out.print("<div class=\"komen-nama\">"+commenter_fullname.get(i)+"</div>");
		out.print("<div class=\"komen-tgl\">"+comment_timestamp.get(i)+"</div>");
		out.print("<div class=\"komen-isi\">"+comment_comment.get(i)+"</div>");
		if ((Integer)session.getAttribute("id") == commenter_id.get(i))
		{
			out.print("<input type=\"button\" name=\"delete\" value=\"Delete\" onclick=\"delete_comment("+task+","+comment_id.get(i)+")\">");
		}
		out.print("<div class=\"line-konten\"></div>");
	}
	out.print("<input type=\"button\" value=\"More\" onclick=\"comment_more("+task+","+next+");this.style.display=\'none\'\">");

	con.close();
}
catch (Exception ex)
{
	out.print("Error : " + ex.getMessage());
}
%>