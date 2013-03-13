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
			?>
		</script>
    </head>
    <body>
        <header>
            <a href="dashboard.html" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
            <div id="dashboard"><a title="Go to Dashboard" href="dashboard.html">Dashboard</a></div>
            <div id="profile"><a title="Go to Profile" href="profile.html">My Profile</a></div>
            <div id="logout"><a title="Log out from here" href="index.html">Log Out</a></div>
            <form id="search">
                <input type="text" name="Search" id="box">
                <input type="submit" value="Search">
            </form>
        </header>
        <div id="panel"></div>
        <div id="donelist">
            Ridho Ramadan<br/>
            Daniel Ginting<br/>
            Timotius Nugraha<br/>
            Nicolas Rio<br/>
            Aditya Agung
        </div>
        <div id="todolist">
            Afif Alhawari<br/>
            Flora Monica <br/>
            Nugroho Satrijandi<br/>
        </div>
        <div id="biodata">
            <img id="foto" src="<?php echo $current_user['avatar'];?>">
            <img id="badge" src="img/badge.png">
            <div id="biousername">
				<?php
					echo $current_user['username'];
				?>
			</div>
            <div id="bioleft">
                Name<br>
                Date of Birth<br>
                Email<br>
            </div>
            <div id="bioright">
                : 	
				<?php
					echo $current_user['fullname'];
				?><br>
                : 
				<?php
					echo $current_user['birthday'];
				?><br>
                : 
				<?php
					echo $current_user['email'];
				?>
				<br>
            </div>
        </div>
        
    </body>
</html>
