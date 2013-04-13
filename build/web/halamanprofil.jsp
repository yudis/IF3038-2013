<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">


<head>
	<title>profil</title>
	<link rel="stylesheet" type="text/css" title="antartica" href="css/style3.css"/>
	<script type="text/javascript" src="js/datetimepicker.js"></script>
	<script type="text/javascript">
		function register(){
				alert("Registration Success!");
				window.location = "dashboard.php";
		}
	</script>
</head>

<%@include file="login.jsp"%>

<body>
	<header>
		<div class='profpict'>
			<a href ='dashboard.php'></a>
			<?php
				if(isset($_SESSION['avatar'])) {echo "<img src='{$_SESSION['avatar']}'>";}
				elseif(isset($_SESSION['name'])) {echo "<img src='avatar/default.jpg'>";}
			?>
			</a>
		</div>
		<div class="header">
			<ul class="ulheader">
				<li><a href="dashboard.php"><img src="images/todolistss.jpg" alt="logo"/></a></li>
				<li><a href="dashboard.php" id="linkdashboard">DashBoard</a></li>
				<li><a href="halamanprofil.php" id="linkprofile">My Profile</a></li>
				<li><a href="logout.php" id="linklogout">LogOut</a></li>
				<li>
			<div id="search">	
				<form id="form1" name="form1" method="post" action="Test Result.php">
					<label>Search:</label> 
					<input type="text_search" name="text_search" id="text_search" placeholder="Search" />
					<input type="SUBMIT" name="SUBMIT" id="SUBMIT" value="Search" > 
					
					<label>Filter: </label>
					<select id="filter" name="filter" width="400">
					<option> All </option>
					<option> Users </option>
					<option> Username </option>
					<option> E-mail </option>
					</select>

				</form>
				</li>
			</div>
			</ul>
			
			<div id="sign">
			<?php
				if(isset($_SESSION['uname'])){
					echo "Hello there, <b><a href='halamanprofil.php'>".$_SESSION['uname']."</a></b> Welcome Home!  <b><a href='logout.php'>Log Out</a></b>";
				}
			?>
			</div>
		</div>
		
    </header>
           
	<div class="centeract">
		<!--<form action="" method="post">-->
            <div class="satu">
				<h1> MY PROFILE <br /></h1>
				<div id="isiprofil">
				<div> 
					<?php
						if(isset($_SESSION['avatar'])) {echo "<img src='{$_SESSION['avatar']}'>";}
						elseif(isset($_SESSION['name'])) {echo "<img src='avatar/default.jpg'>";}
					?>
					</br>
						<button class="buttoncontent"  type="file" onclick="toggle_visibility('change_ava_form');"> Change Avatar </button>
					<br></br>
				</div>
				<b>Username: </b>
				<div>
					<?php
						echo $_SESSION['uname'];
					?>
					<br></br>
				</div>
				<b>Name: </b>
				<div>
					
					<?php
						echo $_SESSION['name'];
					?>
					<button class="buttoncontent"  type="button" onclick="toggle_visibility('change_name_form');"> Change Name </button>
					<div id="change_name_form">
						<div id="change_name_form_inner">
							New Name :
							<br>
							<input type="text" id="new_name" value="">
							<br>
							<div id="edit_name" class="link_red" onclick="edit_name()"> Save </div>
						</div>
					</div>
					<br></br>
				</div>
				<b>Tanggal Lahir: </b>
				<div>
					<?php
						echo $_SESSION['dob'];
					?>
					<button class="buttoncontent"  type="button" onclick="submitHandler()"> Change Date of Birth </button>
					<br></br>
				</div>
				<b>Email: </b>
				<div>
					<?php
						echo $_SESSION['email'];
					?>
					<button class="buttoncontent"  type="button" onclick="submitHandler()"> Change Email </button>
					<br></br>
				</div>
				
				</div>
            </div> 
				
			<div class = "dua">
				<h2> MY TASK </h2>
				<div id = 'ayam'>
				<!--<div class="tulisan">-->

					<table align="center" border="1" bordercolor="blue" cellpadding="5" cellspacing="3">
					<tr>
						<th>Task</th>
						<th>Deadline</th>
						<th>Tag Multivalue</th>
						<th>Status</th>
						
					</tr>
					<tr>
						<td>Tubes Progin I</td>
						<td>23 Februari 2013</td>
						<td>HTML, CSS</td>
						<td><p><input type="checkbox" name="status" value="one"/> Done<br />
						<input type="checkbox" name="status" value="two"checked /> Undone</p></td>
					</tr> 
					<tr class="alt">
						<td>Tubes IMK Flash</td>
						<td>23 Februari 2013</td>
						<td>video</td>
						<td><p><input type="checkbox" name="status" value="one" checked/> Done<br />
						<input type="checkbox" name="status" value="two"/> Undone</p></td>
					</tr>
					</table><br /><br />
					<!--<br/><a href="http://www.google.com" id="edit">edit profile</a><br/>
				<!--</div>-->
				</div>
			</div>
			
			</div>
		<!--</form>-->
	</div>
</body>

</html>  
