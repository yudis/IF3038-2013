<html>
	<head>
		<title>Shared To Do List</title>
		<link rel="stylesheet" type="text/css" href="../style.css">
		<link rel="shortcut icon" href="favicon.ico">
		<script type="text/javascript" src="../ajax.js"></script>
		<script type="text/javascript" src="../mpquery.js"></script>
		<script type="text/javascript" src="profilecontroller.js"></script>	
		<script type="text/javascript" src="../validation.js"></script>	
	</head>
	<body onload="checkLogged();">
		<div id="navsearch">
		</div>		
		<div class="clearall container" id="registerform">
			<h2>Edit Profile</h2>
			<form action="profilesubmit.php?u=<?=$_GET['u']?>&e=<?=$_GET['e']?>" method="post" enctype="multipart/form-data" onsubmit="submitChange(); alert('Successfully editing the profile!')">
			<fieldset>
				<p>
					<label>Full name <abbr title="Required">*</abbr></label>
					<input value="<?=$_GET['fullname'];?>" id="fullname" name="fullname"
					required="required" aria-required="true"
					pattern="^[a-zA-Z]+ [a-zA-Z]+$"
					title="Your full name (first and last name)"
					type="text" spellcheck="false" size="20" onkeyup="checkEdited(this);" onfocusout="checkEdited(this);" />
				</p>
				<p>
					<label>Email <abbr title="Required">*</abbr></label>
					<input name="email" id="email" value="<?=$_GET['email'];?>"
					required="required" aria-required="true"
					pattern="^(([-\w\d]+)(\.[-\w\d]+)*@([-\w\d]+)(\.[-\w\d]+)*(\.([a-zA-Z]{2,5}|[\d]{1,3})){1,2})$"
					title="Your email address"
					type="email" spellcheck="false" size="20" onkeyup="checkEdited(this);" onfocusout="checkEdited(this);" />
				</p>
				<p>
					<label>Password <abbr title="Required">*</abbr></label>
					<input value="<?=$_GET['password'];?>" id="password" name="password" onchange="checkPassword()"
					required="required" aria-required="true"
					pattern="^[- \w\d\u00c0-\u024f]{8,}$"
					title="Your password"
					type="password" spellcheck="false" size="20" onkeyup="checkEdited(this);" onfocusout="checkEdited(this);" />
				</p>
				<p>
					<label>Confirm password <abbr title="Required">*</abbr></label>
					<input value="<?=$_GET['password'];?>"  id="confirmpassword" name="confirmpassword" onchange="checkConfirmPassword()"
					required="required" aria-required="true"
					pattern="^[- \w\d\u00c0-\u024f]{8,}$"
					title="Your password (same as above)"
					type="password" spellcheck="false" size="20" />
				</p>			
				<p>
					<label>Date of birth <abbr title="Required">*</abbr></label>
					<input value="<?=$_GET['dob'];?>"  id="dob" name="dob" onchange="checkDOB()"
					required="required" aria-required="true"
					pattern="^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$"
					title="Your date of birth"
					type="date" spellcheck="false" size="20" onfocusout="checkEdited(this);" />
				</p>
				<!--output id="list"></output-->
				<p>
					<div class='box' id="avatar">
						<img id='avatar2' src='<?="../images/".$_GET['photo'];?>' width='200' height='200'></h3>
					</div>
				</p>
				<p>
					<input type="file" id="filename" name="files" value='<?=$_GET['photo'];?>' onchange="CheckFiles(); setPhoto();" />		
				</p>				
			</fieldset>
			<fieldset>
			<button id="edit" type="submit">Submit</button>
			</fieldset>
			</form>
		</div>		
	</body>
</html>