<%@include file="/header.jsp"%>
<%
int member_id;
if (request.getParameter("id") != null)
{
	member_id = Integer.parseInt(request.getParameter("id"));
}
else
{
	member_id = (Integer)session.getAttribute("id");
}
PreparedStatement pst1 = con.prepareStatement("SELECT * FROM `members` WHERE id="+member_id);
ResultSet rs1 = pst1.executeQuery();
rs1.next();
%>
<div id="main">
	<div id="konten">
		<div class="atas">
		</div>
		<div class="tengah">
			<div id="done">
				<div class="judul">
					PROFIL
				</div>
				<div class="isi">
					<img src="<% out.print(rs1.getString(7)); %>" alt="avatar" width="150"/>
				</div>
				
				<div id="profiledetail">
					<div class="profilefont"> Username		: <% out.print(rs1.getString(2)); %> </div>
					<div class="profilefont"> Nama Lengkap	: <% out.print(rs1.getString(4)); %> </div>
					<div class="profilefont"> Tanggal lahir : <% out.print(rs1.getString(5)); %> </div>
					<div class="profilefont"> Email			: <% out.print(rs1.getString(6)); %> </div>
					<div class="profilefont"> Jenis Kelamin : <% if (rs1.getString(8).equals("M")) out.print("laki-laki"); else out.print("perempuan"); %> </div>
		            <div class="profilefont"> Tugas :<br/>
		            Sudah selesai:
		            <%
		            PreparedStatement pst2 = con.prepareStatement("SELECT * FROM `assignees` WHERE member="+member_id+" AND finished=1");
		            ResultSet rs2 = pst2.executeQuery();
		            if (rs2.next())
		            {
		        		out.print("<br /><ol>");
		        		do
		        		{
		        			int task_id = rs2.getInt(2);
		        			PreparedStatement pst3 = con.prepareStatement("SELECT * FROM `tasks` WHERE id="+task_id);
		        			ResultSet rs3 = pst3.executeQuery();
		        			rs3.next();
		        			out.print("<li><a href=\"rinciantugas.jsp?id="+task_id+"\">"+rs3.getString(2)+"</a></li>");
		        			rs3.close();
		        			pst3.close();
		        		} while(rs2.next());
		        		out.print("</ol>");
		    		}
		    		rs2.close();
		    		pst2.close();
		            %>
		            Belum selesai:
		            <%
		            PreparedStatement pst4 = con.prepareStatement("SELECT * FROM `assignees` WHERE member="+member_id+" AND finished=0");
		            ResultSet rs4 = pst4.executeQuery();
		            if (rs4.next())
		            {
		        		out.print("<br /><ol>");
		        		do
		        		{
		        			int task_id = rs4.getInt(2);
		        			PreparedStatement pst5 = con.prepareStatement("SELECT * FROM `tasks` WHERE id="+task_id);
		        			ResultSet rs5 = pst5.executeQuery();
		        			rs5.next();
		        			out.print("<li><a href=\"rinciantugas.jsp?id="+task_id+"\">"+rs5.getString(2)+"</a></li>");
		        			rs5.close();
		        			pst5.close();
		        		}
		        		while (rs4.next());
		        		out.print("</ol>");
		    		}
		    		rs4.close();
		    		pst4.close();
		            %>
		            </div>
					<div class="profilefont"> About me		: <% out.print(rs1.getString(9)); %> </div>
					<%
					if (rs1.getInt(1) == (Integer)session.getAttribute("id"))
					{
					%>
					<div class="register-submit"><input type="button" name="register" value="Edit" id="form-button" onclick="edit_task();" /></div>
					<%
					}
					%>
				</div>
			</div>
			<form id="edit" enctype="multipart/form-data" action="editprofil.jsp" method="post">
				<script type="text/javascript" src="mainpage.js"></script>
				Change fullname: <input type="text" name="fullname" value='<% out.print(session.getAttribute("fullname")); %>'/><br />
				Upload new avatar: <input type="file" name="avatar"/ ><br />
				<div id="left">
					Change birth date: <input type="text" name="birthdate" id="form-tgl" value='<% out.print(session.getAttribute("birthdate")); %>' />
				</div>
				<div id="caldad">
					<div id="calendar"></div>
					<a href="javascript:showcal(3,2013);void(0);"><img src="images/cal.gif" alt="Calendar" /></a>
				</div><br /><br />
				Change password: <input type="password" name="passwd" /><br />
				Confirm change password: <input type="password" name="cpasswd" /><br />
				<input type="submit" name="submit" value="Submit" />
			</form>
			
		<div class="bawah">
		</div>
	</div>
</div>
<%
rs1.close();
pst1.close();
%>
<%@include file="/footer.jsp"%>