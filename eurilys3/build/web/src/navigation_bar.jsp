<script type="text/javascript" src="../js/animation.js"> </script>
<div id="navbar">
	<div id="short_profile">
		<a href="profile.jsp"> <img id="profile_picture" src="" alt=""> </a>
		<div id="profile_info">
			Welcome, <br>
                        <a href="profile.jsp" class="darkBlue"> <%= session.getAttribute("fullname") %> </a>
			<br><br>
			<div class="link_tosca" id="edit_profile_button"> <a href="edit_profile.jsp"> Edit Profile </a></div>
		</div>
	</div>
	<div id="category_list">
		<div class="link_blue_rect" id="category_title"><a href="#" onclick=""> All Categories </a> </div>
		<script> loadCategoryList(); </script>
                <ul id="category_item">   
                    <li> <span class='categoryList' onclick=''> CATEGORY 1 </span> </li>
                    <li> <span class='categoryList' onclick=''> CATEGORY 2 </span> </li>
                    <li> <span class='categoryList' onclick=''> CATEGORY 3 </span> </li>
				
		</ul>
		<div id="add_task_link"> <a id="add_task" name="" onclick="addCatName();" href="addtask.jsp"> + new task </a> </div>
		<div id="add_new_category" onclick="toggle_visibility('category_form');"> + new category </div>
		<div id="category_form">
			<div id="category_form_inner">
                                <!-- ganti post action -->
				<form method="POST" action="">
					Category name : <br>
					<input type="text" name="add_category_name" id="add_category_name" value="">
					<br><br>
					Assignee(s) : <br>
					<input type="text" name="add_category_asignee_name" id="add_category_asignee_name" value="">
					<br><br>
					<button type="submit" id="add_category_button" name="add_category_button" class="link_red"> Add </div>
				</form>
			</div>
		</div>
	</div>
</div>