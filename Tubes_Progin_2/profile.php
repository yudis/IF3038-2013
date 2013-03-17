<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title>BANG! - Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
		<script>
			<?php
				require "config.php";
				$sql = "SELECT * FROM user WHERE username = 'EndyDoank'";
				$user = mysqli_query($con,$sql);
				$current_user = mysqli_fetch_array($user);
				$sql_notdone_list = "SELECT task.name FROM task, assignee WHERE status='0' and username='EndyDoank' and assignee.id_task = task.id_task;";
				$not_done_list = mysqli_query($con,$sql_notdone_list);
				$sql_done_list = "SELECT task.name FROM task, assignee WHERE status='1' and username='EndyDoank' and assignee.id_task = task.id_task;";
				$done_list = mysqli_query($con,$sql_done_list);
			?>
		</script>
    </head>
    <body>
        <header>
            <a href="dashboard.php" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
            <div id="dashboard"><a title="Go to Dashboard" href="dashboard.php">Dashboard</a></div>
            <div id="profile"><a title="Go to Profile" href="profile.php">My Profile</a></div>
            <div id="logout"><a title="Log out from here" href="index.php">Log Out</a></div>
            <form id="search">
                <input type="text" name="Search" id="box">
                <input type="submit" value="Search">
            </form>
        </header>
        <div id="panel"></div>
        <div id="donelist">
            <?php
				$done_list = mysqli_query($con,$sql_done_list);
				while($done_row = mysqli_fetch_array($done_list)){
					echo $done_row['name'];
					echo "<br />";
				}
			?>
        </div>
        <div id="todolist">
            <?php
				$not_done_list = mysqli_query($con,$sql_notdone_list);
				while($not_done_row = mysqli_fetch_array($not_done_list)){
					echo $not_done_row['name'];
					echo "<br />";
				}
			?>
        </div>
        <div id="biodata">
            <img id="foto" src="<?php echo $current_user['avatar'];?>">
            <img id="badge" src="img/badge.png">
            <div id="biousername">
				<?php
					$user = mysqli_query($con,$sql);
					$current_user = mysqli_fetch_array($user);
					echo $current_user['username'];
				?>
			</div>
            <div id="bioleft">
                Name<br>
                Date of Birth<br>
                Email<br>
            </div>
            <div id="bioright">: <?php echo $current_user['fullname'];?><br>
                :<?php echo $current_user['birthday'];?><br>
                :<?php echo $current_user['email'];?><br>
            </div>
			<div id="editProfile">
				<a onclick="editProfile();">Edit Profile</a>
			</div>
        </div>
		<div id='edit'>
			<div id='editProfileForm'>
					Full Name<br>
					Date of birth<br>
					Avatar<br>
					Password<br>
					Confirm Password<br>
			</div>
			<div id='inputEditProfile'>
				<form method="post" action="edit.php" enctype="multipart/form-data">
					<input type="text" name="editname"><br>
					<input type="date" name="editdob"><br>
					<input type="file" name="editavatar"><br>
					<input type="password" name="editpassword1"><br>
					<input type="password" name="editpassword2"><br>
					<input type="submit" value="edit">
					<input type="button" onclick="profileRestore();" value="cancel">
				</form>
			</div>
		</div>
    </body>
</html>