<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<?php
	session_start();
?>

<head>
	<title>To-Do - Profile</title>
	<link rel="stylesheet" type="text/css" title="antartica" href="css/style.css"/>
    <script type="text/javascript" src="validator.js"></script>
	<script type="text/javascript" src="datetimepicker_css.js"></script>
	
	<script type="text/javascript">
		function register(){
				alert("Registration Success!");
				window.location = "dashboard.php";
		}
	</script>
</head>

<body onLoad='getTaskUser();'>
	<header>
		<a href ='dashboard.php'>
		<?php
			if(isset($_SESSION['avatar'])) {echo "<img1 src='{$_SESSION['avatar']}'>";}
			elseif(isset($_SESSION['name'])) {echo "<img1 src='avatar/default.jpg'>";}
		?>
		</a>
		
		<div class="header">
			<ul class="ulheader">
				<li><a href="dashboard.php"><img src="images/todolistss.jpg" alt="logo"/></a></li>
				<li><a href="dashboard.php" id="linkdashboard">DashBoard</a></li>
				<li><a href="halamanprofil.php" id="linkprofile">My Profile</a></li>
				<li><a href="logout.php" id="linklogout">LogOut</a></li>
				<li>
					
				<form id="form1" name="form1" method="post" action="Test Result.php">
				<label>Search: 
				<input type="text_search" name="text_search" id="text_search" placeholder="Search" />
				<input type="SUBMIT" name="SUBMIT" id="SUBMIT" value="Search" > 
				</label>
			
			
				<label>Filter: 
				<select id="filter" name="filter" width="400">
				<option> All </option>
				<option> Users </option>
				<option> Username </option>
				<option> E-mail </option>
				</select>
				</label>
				</form>
				</li>
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
				<h1> Profil User </h1>	
				<div id="isiprofil">
				<div> 
					<?php
						if(isset($_SESSION['avatar'])) {echo "<img src='{$_SESSION['avatar']}'>";}
						elseif(isset($_SESSION['name'])) {echo "<img src='avatar/default.jpg'>";}
					?>
					</br>
						<button class="buttoncontent"  type="button" onclick="submitHandler()"> Change Avatar </button>
					<br></br>
				</div>
				<b>Username: </b>
				<div>
					<?php
						echo $_SESSION['uname'];
					?><br></br>
				</div>
				<b>Name: </b>
				<div>
					<?php
						echo $_SESSION['name'];
					?><br></br>
				</div>
				<b>Tanggal Lahir: </b>
				<div>
					<?php
						echo $_SESSION['dob'];
					?>
				</div><br></br>
				<b>Email: </b>
				<div>
					<?php
						echo $_SESSION['email'];
					?>
					<button class="buttoncontent"  type="button" onclick="submitHandler()"> Change Email </button>
					<br></br>
				</div>
				<b>Task List: </b>
				<div>
					<?php
						echo $_SESSION['jmltask'];
					?>
				</div><br></br>
				</div>
            </div> 
				
			<div class = "dua">
				<h2> Daftar Task </h2>
				<div id = 'ayam'>
				</div>
			</div>
			
			</div>
		<!--</form>-->
	</div>
</body>

</html>  
