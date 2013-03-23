<?php include 'db_creator.php'; ?>	
<?php include 'header.php'; ?>	

<!-- Web Content -->
<section>
	<div id="index_left">
		<div id="tutorial_container">
			<div>
				<img id="tutorial_image" src="img/eurilys_comic.png" alt="tutorial_pic"/>
			</div>
			<div id="tutorial">
				<span class="red"> Eurilys </span> is an online to-do-list website which is so easy to use, user-friendly, and the most important 
				of all, it's completely <span class="blue">FREE</span>!
				<br>
				You can add task, category for each task, and you can write down all the task's details here. 
				<br>
				No more papers, pens, and confusion. <span class="red"> Eurilys </span> is here to be your organization's partner.
			</div>
		</div>
	</div>
	<div id="index_right">
		<div id="login_form_container">
			<form id="login_form" method="POST" action="login_script.php">
				<label> Username </label> <input type="text" id="login_username" name="username"/>
				<br/><label> Password </label> <input type="password" id="login_password" name="password"/> 
				<input type="submit" name="login_submit" class="link_red top10 bold" id="login_submit" value="LOG IN">
			</form>
		</div>
		
		<div class="signup_label">
			Have No Account ? 
		</div>
		<div id="signup_form_container">
			<form id="signup_form" method="POST" action="register_script.php" enctype="multipart/form-data">
				<label> Username </label>	
				<input type="text" name="signup_username" onkeypress="javascript:regCheck();" id="reg_username" title="Username should be at least 5 characters long" required>
				<img src="img/yes.png" id="username_validation" class="signup_form_validation" alt="validation image">
				
				<label> Password </label> 	
				<input type="password" name="signup_password" onkeypress="javascript:regCheck();" id="reg_password" title="Password should be at least 8 characters long" required>
				<img src="img/no.png" id="password_validation" class="signup_form_validation" alt="validation image">
				
				<label> Confirm </label> 	
				<input type="password" name="signup_confirm_password" onkeypress="javascript:regCheck();" id="reg_confirm" title="Confirmation password should be the same with Password" required>
				<img src="img/no.png" id="confirm_validation" class="signup_form_validation" alt="validation image">
				
				<label> Name </label>
				<input type="text" name="signup_long_name" id="reg_name" onkeypress="javascript:regCheck();" title="Your name should be at least consists first name and last name" required>
				<img src="img/yes.png" id="name_validation" class="signup_form_validation" alt="validation image">
				
				<label> Email </label> 		
				<input type="text" name="signup_email" id="reg_email" onkeypress="javascript:regCheck();" title="Email should be written in the right format" required>
				<img src="img/yes.png" id="email_validation" class="signup_form_validation" alt="validation image">
				
				<label> Birth Date </label>
				<input type="date" name="signup_birth_date" onchange="javascript:regCheck();" id="reg_birthdate" title="YYYY-MM-DD">
				<img src="img/yes.png" id="birthdate_validation" class="signup_form_validation" alt="validation image">
				
				<label> Avatar </label>
				<input type="file" onchange="javascript:regCheck();" name="signup_avatar_upload" id="avatar_upload"> 
				<img src="img/yes.png" id="avatar_validation" class="signup_form_validation" alt="validation image">
				
				<!--
				<div id="signup_button_submit" onclick="javascript:signup();" class="right link_tosca top10 bold" title="Semua elemen form harus diisi dengan benar dahulu."> SIGN UP </div>
				-->
				<input type="submit" name="signup_submit" id="signup_submit" class="link_tosca top10 bold" title="Semua elemen form harus diisi dengan benar dahulu." value="SIGN UP" disabled="disabled">
			</form>
		</div>
	</div>
</section>
		
<?php include 'footer.php'; ?>		