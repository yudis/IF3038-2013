<!DOCTYPE html>


	<%--<?php
			include_once("../php/loginchecker.php");
		?>
	
<?php include '../php/fungsiget.php'?>
<?php include '../php/getnewtaskdetail.php'?>
	<?php
      $kategori = get_allkategoriphp();
      print_r($kategori);
    //$all_task = get_alltaskphp();
    ?>--%>
<%@include file="../php/loginchecker.jsp"%>
<%@include file="../php/fungsiget.jsp"%>
<%@include file="../php/getnewtaskdetail.jsp"%>
<%@include file="../php/tampilkomen.jsp"%>
<%String username="ruth";%>

<html>
	<!--
	<IFRAME name="iframe" src="src/header.html" width='100%' height='auto' marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=auto></IFRAME>
	-->
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script>
		<script type="text/javascript" src="../js/catselector.js"> </script> 	
                <script type="text/javascript" src="../js/add_task.js"></script>
                <script type="text/javascript" src="../js/addkomen.js">   </script>
                <script type="text/javascript" src="../js/edit_task2.js">   </script>
                <script type="text/javascript" src="../js/fungsiget.js">
                
                   <!-- var taskid = "<?php echo $last_idx; ?>";-->
                   <!-- alert(taskid);-->
                </script>
                <script type="text/javascript" src="../js/edit_task2.js"></script>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>

	<body>
		<!-- Web Header -->
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
				
			</div>
                        <%
                            String namakategori=request.getParameter("ct");
                            String namatask=request.getParameter("tm");
                            String taskstatus=request.getParameter("ts");
                            String tasktag=request.getParameter("ttm");
                            String checkbox=request.getParameter("c");
                            String asigne=request.getParameter("asign");
                             ResultSet komen = getkomen(namakategori,namatask);
                        %>
                     
			<div id="dynamic_content">
                            <div id="edit_task_header" class="left top30 dynamic_content_head">
                                    <%out.print(namatask);%>
				</div>
				
				<input id="edit_task_button" class="left top30 link_blue_rect" 
					onclick="edit_task2('<%out.print(namatask);%>')" type="button" value="Edit Task" />
				
				
				<input id="save_button_td" class="left top30 link_blue_rect" 
					onclick="save_edit_task()" type="button" value="Save"/>
				
				
				<div id="row1_taskdetail" class="left top30 dynamic_content_row">
					<div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>
					<div id="task_name_rtd" class="left dynamic_content_right"><%out.print(namatask);%></div>
				</div>
				
				<div id="row2_taskdetail" class="left top10 dynamic_content_row">
					<div id="attachment_ltd" class="left dynamic_content_left">Attachment</div>
					<div id="attachment_rtd" class="left dynamic_content_right">
						<a id="task_attachment" href="../file/taskdetail_file.pdf">Assignment.pdf</a>
					</div>
				</div>
				
				<div id="row3_taskdetail" class="left top10 dynamic_content_row">
					<div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>
					<div id="deadline_rtd" class="left dynamic_content_right"></div>
				</div>
				
				<div id="row4_taskdetail" class="left top10 dynamic_content_row">
					<div id="assignee_ltd" class="left dynamic_content_left">Assignee</div>
					<div id="assignee_rtd" class="left dynamic_content_right"><%out.print(asigne);%></div>
				</div>
			
				<div id="row5_taskdetail" class="left top10 dynamic_content_row">
					<div id="tag_ltd" class="left dynamic_content_left">Tag</div>
					<div id="tag_rtd" class="left dynamic_content_right"><%out.print(namatask);%></div>
				</div>
				
				<div id="row6_taskdetail" class="left top10 dynamic_content_row">
					<div id="comment_ltd" class="left dynamic_content_left">Comment</div>
					<div id="comment_rtd" class="left dynamic_content_right">
                                            <ul >
                                           
                                           
                                            </ul>
					</div>
				</div>
				
				<div id="row7_taskdetail" class="left top10 dynamic_content_row">
					<div id="addcomment_ltd" class="left dynamic_content_left">Add Comment</div>
					<div id="addcomment_rtd" class="left dynamic_content_right">
						<form autocomplete="off">
							<textarea id="comment_textarea" rows="5" cols="50" name="CommentBox">
							</textarea><br>
							<input id="edit_profile_button" class="link_tosca" 
                                                        onclick="add_komen('<%out.print(namakategori);%>','<%out.print(namatask);%>')" type="button" value="Add Comment" />
						</form>
					</div>
				</div>
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
	</body>

<!-- ini nanti jadiin footer -->
</html>
