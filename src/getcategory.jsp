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

	int user_id = Integer.parseInt(request.getParameter("id"));
	int cat_id = Integer.parseInt(request.getParameter("cat"));

	PreparedStatement pst1 = con.prepareStatement("SELECT * FROM `tasks` WHERE category="+cat_id);
	ResultSet rs1 = pst1.executeQuery();
	while (rs1.next())
	{
		int task_id = rs1.getInt(1);
		PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `assignees` WHERE task="+task_id+" AND member="+user_id);
		ResultSet rs2 = pst2.executeQuery();
		if (rs2.next())
		{
			out.print("<a href='rinciantugas.jsp?id="+rs1.getInt(1)+"'>"+rs1.getString(2)+"</a></br>");
			out.print("Deadline: <strong>"+rs1.getString(5)+"</strong><br />");
			PreparedStatement pst3 = con.prepareStatement("SELECT * FROM `tags` WHERE tagged="+task_id);
			ResultSet rs3 = pst3.executeQuery();
			int count_tag = 0;
			java.util.ArrayList<String> tag = new java.util.ArrayList<String>();
			while (rs3.next())
			{
				tag.add(rs3.getString(1));
				count_tag++;
			}
			rs3.close();
			pst3.close();
			out.print("Tag: <strong>");
			for (int i = 0; i < count_tag; i++)
			{
				out.print(tag.get(i));
				if (i < count_tag -1) out.print(", ");
			}
			out.print("</strong><br />");
			out.print("<div id='"+task_id+"'>");
			out.print("Status: <strong>");
			if (rs2.getInt(3) == 1) out.print("Selesai");
			else out.print("Belum selesai");
			out.print("</strong><br />");
			out.print("<input name=\"YourChoice\" type=\"checkbox\" value=\"Selesai\" ");
			if (rs2.getInt(3) == 1) out.print("checked ");
			out.print("onclick= change_status('"+task_id+"',"+rs2.getInt(3)+","+task_id+")> Selesai");
			out.print("</div>");
			if (rs1.getInt(3) == user_id)
			{
				out.print("<form action='deletetask.jsp' method='post'>");
				out.print("<input type='hidden' name='deltask' value='"+task_id+"' />");
				out.print("<input type='submit' name='submit' value='Delete' />");
				out.print("</form>");
			}
		}
		rs2.close();
		pst2.close();
		out.print("<br />");
	}
	rs1.close();
	pst1.close();
	con.close();
}
catch (Exception ex)
{
	out.print("Error : " + ex.getMessage());
}
%>