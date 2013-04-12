<%@include file="/header.jsp"%>
<%

Integer id = (Integer)session.getAttribute("id");
%>

<div id="main2">
    <div id ="dashboardcontainer">
        <div id ="staticcomponentlist">
            <div class ="letterpanel">
            <a href="#login_form" id="login_pop">Kategori</a>
            </div>
            <!-- popup form #1 jadi ceritanya awalnya gk keliatan -->
            <a href="#x" class="overlay" id="login_form"></a>
            <div class="popup">
				<form name="add_category" method="post" action="category.jsp">
					<h2>Tambah Kategori Tugas</h2>
					<!--<h2>Welcome Guest!</h2>
					<p>Please enter your login and password here</p>-->
					<div>
						<label>Nama Kategori &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: </label>
						<input type ="text" name="category_name"></input>
					</div>
					<div>
						<label for="password">Pengguna Terkait :</label>
						<input type ="text" name="relateduser"></input>
					</div>
					<input type="hidden" name="creator_id" value="<% out.print(id); %>" />
					<input type="submit" name="btn_addCat" value="Ok" />
				</form>

				<a class="close" href="#close"></a>
            </div>
            <%
            PreparedStatement pst1 = con.prepareStatement("SELECT * FROM `categories` ORDER BY id");
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next())
            {
        		boolean found = false;
        		int cat_id = rs1.getInt(1);
        		PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `tasks` WHERE category="+cat_id);
        		ResultSet rs2 = pst2.executeQuery();
        		while (rs2.next())
        		{
        			int task_id = rs2.getInt(1);
        			PreparedStatement pst3 = con.prepareStatement("SELECT * FROM `assignees` WHERE member="+id+" AND task="+task_id);
        			ResultSet rs3 = pst3.executeQuery();
        			if (rs3.next())
        			{
            %>
            <br /><div onclick='javascript:gettask(<% out.print(session.getAttribute("id")); %>,<% out.print(rs1.getInt(1)); %>);'><a href="#"><% out.print(rs1.getString(2)); %></a></div>
            <%
            			PreparedStatement pst4 = con.prepareStatement("SELECT * FROM editors WHERE member="+id+" AND category="+cat_id);
            			ResultSet rs4 = pst4.executeQuery();
            			if (rs4.next())
            			{
            %>
            <a href ='post.jsp?id=<% out.print(rs1.getInt(1)); %>'><input id ="newtask" type="button" name="Tugas Baru" value="New Task"/></a>
            <%
            				if (rs1.getInt(3) == id)
            				{
            %>
            <form action="deletecategory.jsp" method="post">
            	<input type="hidden" name="id" value="<% out.print(rs1.getInt(1)); %>" />
            	<input type="submit" value="Delete Category" />
            </form>
            <%
            				}
            				else
            				{
            					out.print("<br />");
            				}
            			}
            			rs4.close();
            			pst4.close();
            			found = true;
            			break;
            		}
            		rs3.close();
            		pst3.close();
            	}
            	if (found == false)
            	{
            		PreparedStatement pst5 = con.prepareStatement("SELECT * FROM `editors` WHERE member="+id+" AND category="+cat_id);
            		ResultSet rs5 = pst5.executeQuery();
            		if (rs5.next())
            		{
            %>
        	<br /><div onclick='javascript:gettask(<% out.print(session.getAttribute("id")); %>,<% out.print(rs1.getInt(1)); %>);'><a href="#"><% out.print(rs1.getString(2)); %></a></div>
            <a href ="post.jsp?id=<% out.print(rs1.getInt(1)); %>"><input id ="newtask" type="button" name="Tugas Baru" value="New Task"/></a><br />
            <%
            			if (rs1.getInt(3) == id)
            			{
            %>
            <form action="deletecategory.jsp" method="post">
            	<input type="hidden" name="id" value="<% out.print(rs1.getInt(1)); %>" />
            	<input type="submit" value="Delete Category" />
            </form>
            <%
            			}
            			else
            			{
            				out.print("<br />");
            			}
            		}
            		rs5.close();
            		pst5.close();
            	}
            	rs2.close();
            	pst2.close();
            }
            rs1.close();
            pst1.close();
            %>
        </div>
        <div id="rincian">
			<%
			PreparedStatement pst6 = con.prepareStatement("SELECT * FROM `categories`");
			ResultSet rs6 = pst6.executeQuery();
			while (rs6.next())
			{
				int idc = rs6.getInt(1);
				PreparedStatement pst7 = con.prepareStatement("SELECT * FROM `tasks` WHERE category="+idc);
				ResultSet rs7 = pst7.executeQuery();
				while (rs7.next())
				{
					int task_id = rs7.getInt(1);
					PreparedStatement pst8 = con.prepareStatement("SELECT * FROM `assignees` WHERE task="+task_id+" AND member="+id);
					ResultSet rs8 = pst8.executeQuery();
					if (rs8.next())
					{
			%>
			<br /><a href="rinciantugas.jsp?id=<% out.print(rs7.getInt(1)); %>"><% out.print(rs7.getString(2)); %></a><br />
			Deadline: <strong><% out.print(rs7.getString(5)); %></strong><br />
			<%
						PreparedStatement pst9 = con.prepareStatement("SELECT * FROM `tags` WHERE tagged="+task_id);
						ResultSet rs9 = pst9.executeQuery();
						int count_tag = 0;
						java.util.ArrayList<String> tag = new java.util.ArrayList<String>();
						while (rs9.next())
						{
							tag.add(rs9.getString(1));
							count_tag++;
						}
			%>
			Tag: <strong>
			<%
						for (int i = 0; i < count_tag; i++)
						{
							out.print(tag.get(i));
							if (i < count_tag - 1)
							{
								out.print(",");
							}
						}
			%>
			</strong>
			<br />
			<div id="<% out.print(task_id); %>">
				Status : <strong><% if (rs8.getInt(3) == 1) out.print("Selesai"); else out.print("Belum selesai"); %></strong><br />
				<input name="YourChoice" type="checkbox" value="selesai" <% if (rs8.getInt(3) == 1) out.print("checked"); %> onclick="change_status('<% out.print(task_id); %>',<% out.print(rs8.getInt(3)); %>,<% out.print(task_id); %>)"> Selesai
			</div>
			<%
						if (rs7.getInt(3) == id)
						{
			%>
			<form action="deletetask.jsp" method="post">
				<input type="hidden" name="deltask" value="<% out.print(task_id); %>" />
				<input type="submit" name="submit" value="Delete" />
			</form>
			<%
						}
						rs9.close();
						pst9.close();
					}
					rs8.close();
					pst8.close();
				}
				rs7.close();
				pst7.close();
			}
			rs6.close();
			pst6.close();
			%>
            
		</div>
	</div>
	
<%@include file="/footer.jsp"%>