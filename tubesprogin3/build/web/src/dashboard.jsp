<!DOCTYPE html>

<%@include file="../php/fungsiget.jsp"%>
<%@include file="../php/getalltaskfordashboard.jsp"%>
<%@include file="../php/getassignee.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<?php
			include_once("../php/loginchecker.php");
?>--%>

<%
    //String catchoosen = request.getParameter("kategori")
   
    ResultSet kategori = getallkategori();
%>


<html>
	<!--
	<IFRAME name="iframe" src="src/header.html" width='100%' height='auto' marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=auto></IFRAME>
	-->
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script>
                <script type="text/javascript" src="../js/add_kategori.js"> </script>
                
		<script type="text/javascript" src="../js/catselector.js"> </script> 	
                <script type="text/javascript" src="../js/add_task.js"></script>
                <script type="text/javascript" src="../js/fungsiget.js"></script>
                
                <script type="text/javascript" src="../js/tampilprofile.js"></script>
                <script type="text/javascript" src="../js/add_kategori.js"></script>
                <script type="text/javascript" src="../js/allkategori.js"></script>
                <script type="text/javascript" src="../js/hapus.js"></script> 
                <script type="text/javascript" src="../js/edit_task.js"></script>
             
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>

	<body>
		<!-- Web Header -->
		<%--<?php
			include_once("header.php");
		?>--%>
                <%@include file="../src/header.jsp"%>
                
                
		
	
		
		<!-- Web Content -->
		<section>
			<div id="navbar">
				<div id="short_profile">
					<img id="profile_picture" src="../img/avatar1.png" alt="">
					<div id="profile_info">
						Ruth Natasha 
						<br><br>
						<div class="link_tosca" id="edit_profile_button"> Edit Profile </div>
					</div>
				</div>
				<div id="category_list">
					<div class="link_blue_rect" id="category_title"><a href="#" onclick="catchange(0)">All Categories </a> </div>
					<ul id="category_item">
                                            <% while(kategori.next()){%>
                                                 <li><a href="#" onclick="get_taskkategorijs('<%out.print(kategori.getString("cat_name"));%>')" > <%out.println(kategori.getString("cat_name"));%></a>
                                                            <img src="../img/no.png" id="del_cat" onclick="deletecat('<%out.print(kategori.getString("cat_name"));%>')" class="task_done_button" alt="" />
                                                 </li>
                                            <%}%>
                                           
					</ul>
					<!--<div id="add_task_link"> <a href="../src/addtask.php"> + new task </a> </div>-->
                                        <div class="link_blue_rect" id="category_title"><a href="#join_form"> +New Category </a></div>
				
				</div>
			</div>
			<div id="dynamic_content">
                            <ul>
                                <% ResultSet rs= getalltask();%>
                                  <%  while(rs.next()){%>
                                            <li>
                                            <br><br>

                                            <img src="../img/done.png" id="finish_1" onclick="finishTask('+i+')" class="task_done_button" alt="" />
                                            <div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>
                                            <div id="task_name_rtd" class="left dynamic_content_right">  <a href="#" onclick="tampil_edit_task('<%out.print(rs.getString("task_name"));%>')"><%out.println(rs.getString("task_name"));%></a> </div>
                                                   
                                                    
                                            <br>
                                            <div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>
                                            <div id="deadline_rtd" class="left dynamic_content_right"><%--out.println(rs.getString("task_deadline"));--%></div>
                                            <br>
                                            <div id="tag_ltd" class="left dynamic_content_left">Tag</div>
                                            <div id="tag_rtd" class="left dynamic_content_right"><%out.println(rs.getString("task_tag_multivalue"));%></div>
                                            <br>
                                            <div id="tag_ltd" class="left dynamic_content_left">Status</div>
                                            <div id="tag_rtd" class="left dynamic_content_right">
                                                <%
                                                    if(rs.getString("task_status").equals("0")){
                                                        out.println("Not Finish");
                                                    }else{
                                                        out.println("Finish");
                                                    }
                                                %>
                                            </div>
                                            <br>
                                            <div id="tag_ltd" class="left dynamic_content_left">Checkbox</div>
                                            <div id="tag_rtd" class="left dynamic_content_right"><input type="checkbox" onclick="check_value('<%out.print(rs.getString("task_name"));%>','<%out.print(rs.getString("cat_task_name"));%>')" name="checkboxtask"><%out.println(rs.getString("checkbox"));%></div>
                                            <br>
                                            <div class="task_view_category"><%--<?php echo $eachtask['cat_task_name']?>--%></div>
                                            <br>

                                            </li>
                        
                                     
                                    <% }
                                %>
                               
                            
                                
                            </ul>
			</div>
		</section>
		
		<!-- Footer Section -->
		<div class="thin_line"></div>
		<footer>
			<div id="footer_container"> 
				<br><br>
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
                
                <a href="#" class="overlay" id="join_form"></a>
		
		<div class="popup">
			<div id="category_form_inner">
                            Category name : <br>
                            <input type="text" id="add_category_name" name="nama_kategori" value="">
                            <br><br>
                            Assignee(s) : <br>
                            <div id="arr_of_asignee">
                                
                            </div>
                            <select id="add_category_asignee_name" name="customers" onchange="set_assignee(this)">
                                  <option id="asignee_name" value="">Select assignee:</option>
                                
                                <%--<?php foreach($asignee as $eachasignee){?>
                                    <option value=""><?php echo $eachasignee['asignee_name']?></option>
                                <?php } ?>--%>
                               
                            </select>
                           
                            <br><br>
                            <div id="add_category_button" class="link_red" onclick="add_category()"> Add Category</div>
                            <div id="add_category_button" class="link_red" ><a href="#close">Close </a></div>
                            
                        </div>
			
	
		</div>
	</body>

<!-- ini nanti jadiin footer -->
</html>
