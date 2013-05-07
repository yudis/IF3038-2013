<link rel="stylesheet" type="text/css" href="header_auto.css">
<script src="header_auto.js"></script>

<div id="dashboard-header">
	<img id="text-logo" src="img/logo_small.png" alt="logo" href="dashboard.html" title="Home"/>
	<a title="Go to Dashboard" href="dashboard.php" id="dashboard">Dashboard</a>
	<a title="Go to Profile" href="profile.php" id="profile"><?php echo ($_SESSION['username']); ?></a>
	<img src="
		<?php 
			$result = ProginDB::getInstance()->query("SELECT * FROM user WHERE username = '".$_SESSION['username']."'");
			$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
			echo $row['avatar'];
		?>" id="avatarsmall"
	/>
	<a title="Log out from here" href="logout.php" id="logout">Logout</a>
	<form id="search" action="result_page.php" method="post">
		<input type="text" name="Search" id="box" onkeyup="h_autocomp(this,'getsearchdata.php');" onfocusin="h_autocompClearAll()">
		<input type="submit" value="Search">
		<br/>
		<input type='radio' name='options' id='option_all' value = 'a' checked="checked"/>All
		<input type='radio' name='options' id='option_user' value = 'u'/>Username
		<input type='radio' name='options' id='option_category' value = 'c'/>Category
		<input type='radio' name='options' id='option_task' value = 't'/>Task
		<input type='hidden' name='usr' id='usr' value = 
			<?php
				$usr = $_SESSION['username'];
				echo $usr;
			?>
		/>
	</form>
</div>
