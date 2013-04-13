<%@page import="java.sql.Blob"%>
	<script type="text/javascript" src="../js/animation.js"> </script> 
		<div id="navbar">
				<div id="short_profile">
					<img id="profile_picture" src="../img/avatar1.png" alt="">
					<div id="profile_info">
						<class = "fullname" a href ="profile.jsp"> <%= session.getAttribute("fullname") %> </a>
						<br><br>
						<div class="link_tosca" id="edit_profile_button"> <a href ="editprofile.jsp"> Edit Profile </a> </div>
					</div> 
				</div>
		</div>