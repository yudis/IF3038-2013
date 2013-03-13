<div id="navbar">
	<div id="short_profile">
		<img id="profile_picture" src="../img/avatar1.png" alt="">
		<div id="profile_info">
			Welcome, <br>
			<a href="profile.php"> <?php if (isset($_SESSION['fullname'])) {echo $_SESSION['fullname']; }?> </a>
			<br><br>
			<div class="link_tosca" id="edit_profile_button"> Edit Profile </div>
		</div>
	</div>
	<div id="category_list">
		<div class="link_blue_rect" id="category_title"><a href="#" onclick="catchange(0)">All Categories </a> </div>
		<ul id="category_item">
			<li><a href="#" onclick="catchange(1)" id="kuliah">Kuliah</a></li>
			<li><a href="#" onclick="catchange(2)" id="proyek">Proyek</a></li>
			<li><a href="#" onclick="catchange(3)" id="tugas">Tugas</a></li>
			<li><a href="#" onclick="catchange(4)" id="lomba">Lomba</a></li>
		</ul>
		<div id="add_task_link"> <a href="addtask.php"> + new task </a> </div>
		<div id="add_new_category" onclick="toggle_visibility('category_form');"> + new category </div>
		<div id="category_form">
			<div id="category_form_inner">
				Category name : <br>
				<input type="text" id="add_category_name" value="">
				<br><br>
				Assignee(s) : <br>
				<input type="text" id="add_category_asignee_name" value="">
				<br><br>
				<div id="add_category_button" class="link_red" onclick="add_category()"> Add </div>
			</div>
		</div>
	</div>
</div>