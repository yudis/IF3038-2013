<%@ page import="java.io.*,java.util.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html>
<html>
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script> 
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>
	
	<body>
		<!-- Web Header -->
		<header>
			<div id="header_container"> 
				<div class="left">
					<a href="dashboard.html"> <img src="../img/logo.png" alt=""> </a>
				</div>
				<input id="search_box" type="text" placeholder="search...">
				<div class="header_menu"> 
					<div class="header_menu_button"> <a href="dashboard.html"> DASHBOARD </a>  </div>
					<div class="header_menu_button current_header_menu"> PROFILE </div>
					<div class="header_menu_button"> <a id="logout" href="../index.html"> LOGOUT </a> </div>
				</div>
				
			</div>
			<div class="thin_line"></div>
		</header>	
	
		
		<!-- Web Content -->
		<section>
			<%@include file="navbar.jsp"%>
				<%
					try {
						Class.forName("com.mysql.jdbc.Driver");
						System.out.println("Koneksi ke JBDC driver berhasil");
					} catch (ClassNotFoundException ex) {
						System.out.println("koneksi gagal! ");
					}
					Connection editprof_conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510060","root","");
					
					//Get data detail dari user
					String user_name = (String) session.getAttribute("username");
					String fullname = "";
					String birthdate = "";
					String email = "";
					
					PreparedStatement editprofile_state = editprof_conn.prepareStatement("SELECT * FROM user WHERE username=?");
					editprofile_state.setString(1, user_name);
					ResultSet detaildata = editprofil_state.executeQuery();
					detaildata.beforeFirst();
					while (detaildata.next()) {
						fullname = detaildata.getString("full_name");
						birthdate = detaildata.getString("birthdate");
						email = detaildata.getString("email");
					}
				%>
			
			<div id="dynamic_content">
				<div class="half_div">
					<div id="upperprof">
						<img id ="displaypicture" src="" alt="">
						<div id="namauser"><%=fullname%></div>
					</div>
					<input type="file" id="profile_pic_upload" name="profile_picture"/>
					<br/><br/>
					<%=user_name%>
					<br>
					<%=birthdate%>
					<br>
					<%=email%>

				</div>
				<div class="half_div">
					<div class="half_tall">
						<div class="headsdeh">Current Tasks:</div>
						<%
							editprofile_state = editprofile_conn.prepareStatement("SELECT DISTINCT task.task_name, task.task_ID, task.task_status FROM asignee LEFT JOIN task ON task.task_ID=asignee.task_ID WHERE username=?");
							editprofile_state.setString(1, user_name);
							editprofile_state.setString(2, user_name);
							detaildata = editprofile_state.executeQuery();
							detaildata.beforeFirst();
							while (detaildata.next()) {
								if (detaildata.getString("task_status").equals("0")) { %>
									<div class='pointer' onclick='javascript:viewTask("<%=detaildata.getString("task_ID")%>")'> <%= detaildata.getString("task_name") %> </div>   
								<%}
							}
						%>
					</div>
					<div class="half_tall">
						<div class="headsdeh">Finished Tasks:</div>
						<%
							editprofile_state = editprofile_conn.prepareStatement("SELECT DISTINCT task.task_name, task.task_ID, task.task_status FROM asignee LEFT JOIN task ON task.task_ID=asignee.task_ID WHERE username=?");
							editprofile_state.setString(1, user_name);
							editprofile_state.setString(2, user_name);
							detaildata = editprofile_state.executeQuery();
							detaildata.beforeFirst();
							detaildata.beforeFirst();
							while (detaildata.next()) {
								if (detaildata.getString("task_status").equals("1")) { %>
									<div class='pointer' onclick='javascript:viewTask("<%=detaildata.getString("task_ID")%>")'> <%= detaildata.getString("task_name") %> </div>   
								<% }
							}
						%>
					</div>
				</div>
			</div>
		</section>
<%@include file="footer.jsp"%>
</html>