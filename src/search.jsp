<%@include file="header.jsp"%>
<div id="main">
	<div id="konten">
		<%
		String q = request.getParameter("q");
		String o = request.getParameter("o");
		%>
		<div class='atas-search'></div>
		<div class='tengah'>
			<div id='search-judul'>SEARCH RESULT</div>
			<div id='search-keyword'>Keyword: <% out.print(q); %></div>
			<div class='line-konten'></div>
		<%
		if (o.equals("All") || o.equals("User"))
		{
			PreparedStatement pst1 = con.prepareStatement("SELECT * FROM `members` WHERE username LIKE '%"+q+"%' OR email LIKE '%"+q+"%' OR fullname LIKE '%"+q+"%' OR about LIKE '%"+q+"%' LIMIT 0, 10");
			ResultSet rs1 = pst1.executeQuery();
			out.print("<span id='searchtype'>[User]</span><br />");
			if (rs1.next())
			{
				out.print("<div id='result1'>");
				do
				{
		%>
			<div class='judul'>
				<img class='search-img' align='middle' src='<% out.print(rs1.getString(7)); %>' alt='avatar' height='150'/>
				<a href="profil.jsp?id=<% out.print(rs1.getInt(1)); %>"><% out.print(rs1.getString(2)); %></a><br />
				<% out.print(rs1.getString(4)); %>
			</div>
		<%
				}
				while (rs1.next());
				out.print("<input type='button' value='More' onclick=\"search_more('User','"+q+"',10);this.style.display='none'\">");
				out.print("</div>");
			}
			else
			{
				out.print("<div id='message'>No Results Found</div>");
			}
			rs1.close();
			pst1.close();
		}
		%>
		<div class='line-konten'></div>
		<%
		if (o.equals("All") || o.equals("Category"))
		{
			PreparedStatement pst2 = con.prepareStatement("SELECT * FROM categories WHERE name LIKE '%"+q+"%' LIMIT 0, 10");
			ResultSet rs2 = pst2.executeQuery();
			out.print("<span id='searchtype'>[Category]</span><br />");
			if (rs2.next())
			{
				out.print("<div id='result2'>");
				do
				{
		%>
			<div class='judul'>
				<% out.print(rs2.getString(2)); %>
			</div>
			<div class='detail'>
				<%
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
				%>
			</div>
		<%
				}
				while (rs2.next());
				out.print("<input type='button' value='More' onclick=\"search_more('Category','"+q+"',10);this.style.display='none'\">");
				out.print("</div>");
			}
			else
			{
				out.print("<div id='message'>No Results Found</div>");
			}
			rs2.close();
			pst2.close();
		}
		%>
		<div class='line-konten'></div>
		<%
		if (o.equals("All") || o.equals("Content"))
		{
			PreparedStatement pst4 = con.prepareStatement("CREATE VIEW task_tags AS SELECT tasks.*, tags.name AS tag FROM tasks, tags WHERE tasks.id = tags.tagged");
			pst4.executeUpdate();
			pst4.close();
			PreparedStatement pst5 = con.prepareStatement("SELECT DISTINCT id, name, creator, timestamp, deadline, category FROM task_tags WHERE name LIKE '%"+q+"%' OR tag LIKE '%"+q+"%' LIMIT 0, 10");
			ResultSet rs5 = pst5.executeQuery();
			out.print("<span id='searchtype'>[Content]</span><br />");
			if (rs5.next())
			{
				out.print("<div id='result3'>");
				do
				{
					int task_id = rs5.getInt(1);
					int member_id = (Integer)session.getAttribute("id");
					PreparedStatement pst6 = con.prepareStatement("SELECT * FROM `assignees` WHERE task="+task_id+" AND member="+member_id);
					ResultSet rs6 = pst6.executeQuery();
		%>
			<div class='judul'>
				<a href="rinciantugas.php?id=<% out.print(rs5.getInt(1)); %>"><% out.print(rs5.getString(2)); %></a>
			</div>
			<div class='detail'>
				<div>
					Tanggal deadline : <br /><% out.print(rs5.getString(5)); %>
					Tags : 
					<%
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
					%>
					<div id="<% out.print(task_id); %>">
						Status : <strong><% if (rs6.getInt(3) == 1) out.print("Selesai"); else out.print("Belum selesai"); %></strong><br />
						<input name="YourChoice" type="checkbox" value="selesai" <% if (rs6.getInt(3) == 1) out.print("checked"); %> onclick="change_status('<% out.print(task_id); %>',<% out.print(rs6.getInt(3)); %>,<% out.print(task_id); %>)"> Selesai
					</div>
					<%
					}
					%>
				</div>
				<div class='dkonten-clear'>
				</div>
			</div>
		<%
				}
				while (rs5.next());
				out.print("<input type='button' value='More' onclick\"search_more('Content','"+q+"',10);this.style.display='none'\">");
				out.print("</div>");
			}
			else
			{
				out.print("<div id='message'>No Results Found</div>");
			}
			PreparedStatement pst8 = con.prepareStatement("DROP VIEW task_tags");
			pst8.executeUpdate();
			pst8.close();
		}
		%>
		</div>
		<div class='bawah-search'></div>
	</div>
</div>
<%@include file="footer.jsp"%>