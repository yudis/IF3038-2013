<%@include file="header.jsp"%>
<%
int id_task = Integer.parseInt(request.getParameter("id"));

// Get task
PreparedStatement pst1 = con.prepareStatement("SELECT * FROM `tasks` WHERE id="+id_task);
ResultSet rs1 = pst1.executeQuery();
rs1.next();
int id_creator = rs1.getInt(3);

// Get creator
PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `members` WHERE id="+id_creator);
ResultSet rs2 = pst2.executeQuery();
rs2.next();

// Get tags
PreparedStatement pst3 = con.prepareStatement("SELECT * FROM `tags` WHERE tagged="+id_task);
ResultSet rs3 = pst3.executeQuery();
int count_tag = 0;
java.util.ArrayList<String> tag = new java.util.ArrayList<String>();
while (rs3.next())
{
	tag.add(rs3.getString(1));
	count_tag++;
}

// Get attachments
PreparedStatement pst4 = con.prepareStatement("SELECT * FROM `attachments` WHERE task="+id_task);
ResultSet rs4 = pst4.executeQuery();
int count_attachment = 0;
java.util.ArrayList<String> attachment_path = new java.util.ArrayList<String>();
java.util.ArrayList<String> attachment_filetype = new java.util.ArrayList<String>();
while (rs4.next())
{
	attachment_path.add(rs4.getString(2));
	attachment_filetype.add(rs4.getString(3));
	count_attachment++;
}


// Get comments
PreparedStatement pst5 = con.prepareStatement("SELECT * FROM `comments` WHERE task="+id_task+" ORDER BY timestamp DESC");
ResultSet rs5 = pst5.executeQuery();
int count_comment = 0;
java.util.ArrayList<Integer> comment_id = new java.util.ArrayList<Integer>();
java.util.ArrayList<String> comment_timestamp = new java.util.ArrayList<String>();
java.util.ArrayList<String> comment_comment = new java.util.ArrayList<String>();
java.util.ArrayList<Integer> commenter_id = new java.util.ArrayList<Integer>();
java.util.ArrayList<String> commenter_fullname = new java.util.ArrayList<String>();
java.util.ArrayList<String> commenter_avatar = new java.util.ArrayList<String>();
while (rs5.next())
{
	comment_id.add(rs5.getInt(1));
	comment_timestamp.add(rs5.getString(4));
	comment_comment.add(rs5.getString(5));
	int id_commenter = rs5.getInt(2);
	PreparedStatement pst6 = con.prepareStatement("SELECT * FROM `members` WHERE id="+id_commenter);
	ResultSet rs6 = pst6.executeQuery();
	rs6.next();
	commenter_id.add(rs6.getInt(1));
	commenter_fullname.add(rs6.getString(4));
	commenter_avatar.add(rs6.getString(7));
	count_comment++;
	rs6.close();
	pst6.close();
}

int id_user = (Integer)session.getAttribute("id");
PreparedStatement pst7 = con.prepareStatement("SELECT * FROM `assignees` WHERE member="+id_user+" AND task="+id_task);
ResultSet rs7 = pst7.executeQuery();
rs7.next();
%>
<!-- created by Enjella-->
<div id="main">
	<div id="konten">
		<div class="atas">
		</div>
		<div class="tengah">
			<div class="judul">
				<% out.print(rs1.getString(2)); %>
			</div>
			<div class="isi">
			</div>
            
			<div class="detail">
				<div class="byon">
					Posted by <strong><span class="by"><% out.print(rs2.getString(2)); %></span></strong> on <strong><% out.print(rs1.getString(4)); %></strong>
				</div>
				<div class="byon" id="<% out.print(id_task); %>">
					Status : <strong><% if (rs7.getInt(3) == 1) out.print("Selesai"); else out.print("Belum selesai"); %></strong><br />
					<input name="YourChoice" type="checkbox" value="selesai" <% if (rs7.getInt(3) == 1) out.print("checked"); %> onclick="change_status('<% out.print(id_task); %>',<% out.print(rs7.getInt(3)); %>,<% out.print(id_task); %>)"> Selesai
				</div>
				<div id="done">
					<br />
					<div class="byon">
						Deadline : <strong><% out.print(rs1.getString(5)); %></strong>
					</div>
					<br />
					<div class="byon">
						Assignee : <strong>
						<%
						// Get assignee
						PreparedStatement pst8 = con.prepareStatement("SELECT * FROM `assignees` WHERE task="+id_task);
						ResultSet rs8 = pst8.executeQuery();
						int count_assignee = 0;
						java.util.ArrayList<String> assignee = new java.util.ArrayList<String>();
						while (rs8.next())
						{
							int id_assignee = rs8.getInt(1);
							PreparedStatement pst9 = con.prepareStatement("SELECT * FROM `members` WHERE id="+id_assignee);
							ResultSet rs9 = pst9.executeQuery();
							rs9.next();
							assignee.add(rs9.getString(2));
							count_assignee++;
							rs9.close();
							pst9.close();
						}
						for (int i = 0; i < count_assignee; i++)
						{
							out.print(assignee.get(i));
							if (i < count_assignee - 1) out.print(", ");
						}
						rs8.close();
						pst8.close();
						%>
						</strong>
					</div>
					<br />
	                <div class="byon">
						Tag : <strong>
						<%
						for (int i = 0; i < count_tag; i++)
						{
							out.print(tag.get(i));
							if (i < count_tag - 1) out.print(", ");
						}
						%>
						</strong>
					</div>
					<br />
					<%
					if (rs1.getInt(3) == (Integer)session.getAttribute("id"))
					{
					%>
					<div class="count"><input type="button" name="edit" onclick="edit_task()" value="Edit"/></div>
					<%
					}
					%>
				</div>
				<form id="edit" action="edittask.jsp" method="post">
					<script type="text/javascript" src="mainpage.js"></script>
					<div class="byon">
						<%
						String[] partdead = rs1.getString(5).split(" ");
						%>
						<div id="left">
							Deadline : <input type="text" name="inputdeadline" id="form-tgl" value="<% out.print(partdead[0]); %>"/>
						</div>
						<div id="caldad">
							<div id="calendar"></div>
							<a href="javascript:showcal(3,2013);void(0);"><img src="images/cal.gif" alt="Calendar" /></a>
						</div>
						Jam: 
						<%
						String[] parthour = partdead[1].split(":");
						%>
						<select name="hour">
							<%
							for (int i = 0; i < 24; i++)
							{
								out.print("<option value = '"+i+"'");
								if (i == Integer.parseInt(parthour[0]))
								{
									out.print(" selected");
								}
								out.print(">"+i+"</option>");
							}
							%>
						</select>
						<select name="minute">
							<%
							for (int i = 0; i < 60; i++)
							{
								out.print("<option value = '"+i+"'");
								if (i == Integer.parseInt(parthour[1]))
								{
									out.print(" selected");
								}
								out.print(">"+i+"</option>");
							}
							%>
						</select>
						<select name="second">
							<%
							for (int i = 0; i < 60; i++)
							{
								out.print("<option value = '"+i+"'");
								if (i == Integer.parseInt(parthour[2].substring(0, 2)))
								{
									out.print(" selected");
								}
								out.print(">"+i+"</option>");
							}
							%>
						</select>
					</div>
					<br />
					<div class="byon">
						Assignee : <input type="text" name="inputassignee"
						<%
						out.print(" value='");
						for (int i = 0; i < count_assignee; i++)
						{
							out.print(assignee.get(i));
							if (i < count_assignee - 1) out.print(", ");
						}
						out.print("' ");
						%>
						list="user"/>
						<datalist id ="user" />
						<option value = "enjella" />
						<option value = "kevin" />
						<option value = "vincentius" />
						</datalist>
					</div>
					
	                <div class="byon">
						Tag : <input type="text" name="inputtag"
						<%
						out.print(" value='");
						for (int i = 0; i < count_tag; i++)
						{
							out.print(tag.get(i));
							if (i < count_tag - 1) out.print(", ");
						}
						out.print("' ");
						%>
						autocomplete="on"/>
					</div>
					<input type="hidden" name="id" value="<% out.print(id_task); %>" />
                	<div class="count"><input type="submit" name="submit" value="Submit"/></div>
				</form>
                
			</div><br /><br /><br />
			<form action="deletetask.jsp" method="post">
				<input type="hidden" name="deltask" value="<% out.print(id_task); %>" />
				<input type="submit" name="submit" value="Delete" />
			</form>
			<div class="videomode" align="center"> <br> Attachment :
				<%
				for (int i = 0; i < count_attachment; i++)
				{
					if (attachment_filetype.get(i).equals("file"))
					{
						int pos = attachment_path.get(i).lastIndexOf('/');
						String filename;
						if (pos == -1)
						{
							filename = new String(attachment_path.get(i));
						}
						else
						{
							filename = new String(attachment_path.get(i).substring(pos + 1));
						}
				%>
				<a href="<% out.print(attachment_path.get(i)); %>"><% out.print(filename); %></a><br />
				<%
					}
					else if (attachment_filetype.get(i).equals("image"))
					{
				%>
				<img src="<% out.print(attachment_path.get(i)); %>" /><br />
				<%
					}
					else if (attachment_filetype.get(i).equals("video"))
					{
				%>
				<video width="320" height="240" controls src="<% out.print(attachment_path.get(i)); %>"></video><br />
				<%
					}
				}
				%>
				<!--<a href="images/Up.png" target="images/Up.png">Up.png</a><br><img src="images/gajah.png"></img><br><video width="320" height="240" controls src="images/movie.ogg"></video>-->
				</div>
			<div class="komen">
				<div class="komenjudul">Comments</div>
				<div id="konten-commenter">
					<strong><span id="jmlkomen"><% out.print(count_comment); %></span></strong> comments
				</div>
				<div class="line-konten"></div>
				<div id="komen-tulis"><strong>Tulis Komentar</strong></div>
					<input type="hidden" id="id" name="id" value='<% out.print(session.getAttribute("id")); %>'>
					<input type="hidden" id="task" name="task" value="<% out.print(id_task); %>">
					<textarea id="komentar" name="komentar" rows="3" cols="60" id="form-komen"></textarea>
					<div class="komen-submit"><input type="button" name="submit" value="Submit" onclick="comment();"/></div>
				<div class="line-konten"></div>
				<div id="lkomen">
					<%
					if (count_comment > 10) count_comment = 10;
					for (int i = 0; i < count_comment; i++)
					{
						out.print("<div class=\"komen-avatar\"><imag src=\""+commenter_avatar.get(i)+"\" height=\"24\"/></div>");
						out.print("<div class=\"komen-nama\">"+commenter_fullname.get(i)+"</div>");
						out.print("<div class=\"komen-tgl\">"+comment_timestamp.get(i)+"</div>");
						out.print("<div class=\"komen-isi\">"+comment_comment.get(i)+"</div>");
						if ((Integer)session.getAttribute("id") == commenter_id.get(i))
						{
							out.print("<input type=\"button\" name=\"delete\" value=\"Delete\" onclick=\"delete_comment("+id_task+","+comment_id.get(i)+")\">");
						}
						out.print("<div class=\"line-konten\"></div>");
					}
					out.print("<input type=\"button\" value=\"More\" onclick=\"comment_more("+rs1.getInt(1)+",10);this.style.display=\'none\'\">");
					%>
				</div>
				
					
				
			</div>
		</div>
		<div class="bawah">
		</div>
	</div>
</div>
<%
rs7.close();
pst7.close();
rs5.close();
pst5.close();
rs4.close();
pst4.close();
rs3.close();
pst3.close();
rs2.close();
pst2.close();
rs1.close();
pst1.close();
%>
<%@include file="footer.jsp"%>