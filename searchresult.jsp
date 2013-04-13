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

	String filter = request.getParameter("filter");
	String string = request.getParameter("string");
	int start = Integer.parseInt(request.getParameter("start"));
	int next = start + 10;

	if (filter.equals("User"))
	{
		PreparedStatement pst1 = con.prepareStatement("SELECT * FROM `members` WHERE username LIKE '%"+string+"%' OR email LIKE '%"+string+"%' OR fullname LIKE '%"+string+"%' OR about LIKE '%"+string+"%' LIMIT "+start+", 10");
		ResultSet rs1 = pst1.executeQuery();
		if (rs1.next())
		{
			do
			{
				out.print("<div class='judul'>");
				out.print("<img class='search-img' align='middle' src='"+rs1.getString(7)+"' alt='avatar' height='150'>");
				out.print("<a href='profil.jsp?id="+rs1.getInt(1)+"'>"+rs1.getString(2)+"</a>");
				out.print(rs1.getString(4));
				out.print("</div>");
			}
			while (rs1.next());
			out.print("<input type='button' value='More' onclick=\"search_more('User', '"+string+"', "+next+");this.style.display='none'\">");
		}
		rs1.close();
		pst1.close();
	}
	else if (filter.equals("Category"))
	{
		PreparedStatement pst2 = con.prepareStatement("SELECT * FROM categories WHERE name LIKE '%"+string+"%' LIMIT "+start+", 10");
		ResultSet rs2 = pst2.executeQuery();
		int count = 1; // Change this part of code to the number of result set rows
		if (rs2.next())
		{
			do
			{
				out.print("<div class='judul'>"+rs2.getString(2)+"</div>");
				out.print("<div class='detail'>");
				int cat_id = rs2.getInt(1);
				PreparedStatement pst3 = con.prepareStatement("SELECT * FROM `tasks` WHERE category="+cat_id);
				ResultSet rs3 = pst3.executeQuery();
				if (rs3.next())
				{
					out.print("<ol>");
					do
					{
						out.print("<li><a href='rinciantugas.jsp?id="+rs3.getInt(1)+"'>"+rs3.getString(2)+"</a></li>");
					}
					while (rs3.next());
					out.print("</ol>");
				}
				rs3.close();
				pst3.close();
				out.print("</div>");
			}
			while(rs2.next());
			out.print("<input type='button' value='More' onclick=\"search_more('Category', '"+string+"', "+next+");this.style.display='none'\">");
		}
		rs2.close();
		pst2.close();
	}
	else if (filter.equals("Content"))
	{
		PreparedStatement pst4 = con.prepareStatement("CREATE VIEW task_tags AS SELECT tasks.*, tags.name AS tag FROM tasks, tags WHERE tasks.id=tags.tagged");
		pst4.executeUpdate();
		pst4.close();
		PreparedStatement pst5 = con.prepareStatement("SELECT DISTINCT id, name, creator, timestamp, deadline, category FROM task_tags WHERE name LIKE '%"+string+"%' OR tag LIKE '%"+string+"%' LIMIT "+start+", 10");
		ResultSet rs5 = pst5.executeQuery();
		if (rs5.next())
		{
			do
			{
				int task_id = rs5.getInt(1);
				int member_id = (Integer)session.getAttribute("id");
				PreparedStatement pst6 = con.prepareStatement("SELECT * FROM `assignees` WHERE task="+task_id+" AND member="+member_id);
				ResultSet rs6 = pst6.executeQuery();
				out.print("<div class='judul'>");
				out.print("<a href='rinciantugas.jsp?id="+rs5.getInt(1)+"'>"+rs5.getString(2)+"</a>");
				out.print("</div>");
				out.print("<div class='detail'>");
				out.print("<div>");
				out.print("Tanggal deadline : "+rs5.getString(5)+"<br />");
				out.print("Tags :");
				PreparedStatement pst7 = con.prepareStatement("SELECT name FROM tags WHERE tagged="+task_id);
				ResultSet rs7 = pst7.executeQuery();
				rs7.next();
				out.print(rs7.getString(1));
				while (rs7.next())
				{
					out.print(", "+rs7.getString(1));
				}
				if (rs6.next())
				{
					out.print("<div id='"+task_id+"'>");
					out.print("Status : <strong>");
					if (rs6.getInt(3) == 1) out.print("Selesai");
					else out.print("Belum selesai");
					out.print("</strong><br />");
					out.print("<input name='YourChoice' type='checkbox' value='selesai' ");
					if (rs6.getInt(3) == 1) out.print("checked");
					out.print(" onclick=\"change_status('"+task_id+"',"+rs6.getInt(3)+","+task_id+")\"> Selesai");
					out.print("</div>");
				}
				rs7.close();
				pst7.close();
				rs6.close();
				pst6.close();
				out.print("</div>");
				out.print("<div class='dkonten-clear'>");
				out.print("</div>");
				out.print("</div>");
			}
			while (rs5.next());
			out.print("<input type='button' value='More' onclick=\"search_more('Content','"+string+"',"+next+");this.style.display='none'\">");
			out.print("</div>");
		}
		rs5.close();
		pst5.close();
		PreparedStatement pst8 = con.prepareStatement("DROP VIEW task_tags");
		pst8.executeUpdate();
		pst8.close();
	}

	con.close();
}
catch(Exception ex)
{
	out.print("Error : "+ex.getMessage());
}
%>