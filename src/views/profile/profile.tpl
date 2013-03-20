<!DOCTYPE html>
<html> 
    <head>
        <link rel="stylesheet" type="text/css" href="./styles/default.css" />
        <link rel="stylesheet" type="text/css" href="./styles/mediaqueries.css" />
		<link rel="stylesheet" type="text/css" href="./styles/profile.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
		<script src="scripts/ajaxhelper.js" type="application/javascript"></script>
		<script src="scripts/profile.js" type="application/javascript"></script>
		<script src="scripts/helper.js" type="application/javascript"></script>
        <script type="text/javascript" src="scripts/datetimepicker.js"></script>	
        <title>Todolist: User Profile</title>
    </head> 
    <body>
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.html"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.html">Dashboard</a></div></li><li><div><a href="profile.html">Profile</a></div></li><li><div><a href="index.html">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form action="#">
                                <input type="text" class="searchbox" name="q" value="" placeholder="Enter task name here.." />
                                <input type="image" src="images/search.png" name="sumbit" class="searchbox_submit" alt="search..."/>
                            </form>
                        </div>
                    </div>
                </nav>
            </header>
            <div class="content">
                <h1>User Profile</h1>
                <div class="profile">
					<form name="updateprof" method="post" enctype="multipart/form-data" action="./ajax/updateUser.php" >
						<div class="profiledetail" id="profileContent">
							  <div><div class="rincianLabelProfile" > Name </div>
								<div id="usernameDisplayDiv" ><?php echo $username;?></div>
								<div id="nameDisplayDiv" name="fullname"><strong id="Fullnama"><?php echo $fullname?></strong> <a href="#">@<?php echo $username?></a></div>
								<div id="nameEditDiv"><input type="text" id="fname" name="fname" value="<?php echo $fullname?>" onkeyup="validateFullName();" onchange="validateFullName();"/></div>
							  </div>	
							  <div>
								<div class="rincianLabelProfile" > Email </div> 
								<div id="emailDisplayDiv"><strong id="emailValue"><?php echo $_SESSION["user"]["email"]?></strong></div>
							  </div>

							  <div><div class="rincianLabelProfile">Born date </div>					  
									<div class="rincianDetailProfile" id="birthDisplayDiv" class="inlineblock" ><strong id="birthDay"><?php echo $tgl_lahir?></strong></div>
									<div class="rincianDetailProfile" id="birthEditDiv" >
									
										<input type="text" class="regbox" id="Bday" name="Bday" value="<?php echo $tgl_lahir ?>"required="required" placeholder="Birthday (yyyy-mm-dd)" onkeyup="validateBday();" onchange="validateBday();" /><a href="#" onclick="NewCal('Bday', 'YYYYMMDD');return false;"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
									</div>
							  </div>
							  
							  <div>
								<div class="rincianLabelProfile" > Major </div> 
								<div> <strong>Teknik Informatika</strong> </div> 
							  </div>   
							  
							  <div>
								<div class="rincianLabelProfile" > Last Login </div> 
								<div> <strong>Sabtu, 16 Februari 2013, 17:01 WIT</strong> </div>
							  </div> 
							  <div><div class="rincianLabelProfile" id="oldpassEditLabel"> Old Password </div>
								<div id="oldpassEditDetail"><input id="oldpass" type="password" value="" onkeyup="validateOldPassword();" onchange="validateOldPassword();"/></div>
							  </div>
							  <div><div class="rincianLabelProfile" id="passEditLabel"> New Password </div>
								<div id="passEditDetail"><input type="password" id="pwd" name="pwd" value="" onkeyup="validatePassword();" onchange="validatePassword();"/></div>
								<div id="repassEditDetail"><input type="password" id="pwd_confirm" name="pwd_confirm" value="" onkeyup="validateRePassword();" onchange="validateRePassword();"/></div>
							  </div>
							  <div id="CurPwd"><input type="password" id="Oldpwd" name="pwd" value="<?php echo $pwd?>"></div>
							  <div>
								<div id="changeAvatarLabel" class="rincianLabelProfile">Avatar</div>
								<div id="changeAvatarDetail"><input type="file" id="ava" name="ava" accept="image/*"  onkeyup="validateAvatar();" onchange="validateAvatar();" /></div>
							  </div>
							  <div id="editButton" class="edit_button"><button type="button" onclick="return editProfile()">Edit</button> </div>
							  <div id="doneButton" class="done_button"><input type="submit" onclick="return saveProfile()" value="Done" /> </div>			
						</div>
					</form>
				<div class="profilepict"><img src="images/avatar.png" width="200" height="200" alt="Edo Thobi Bram"/></div>
                </div>
				
				<div id="tasklist">

				</div>
			</div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
            </footer>
        </div>
    </body> 
</html>
