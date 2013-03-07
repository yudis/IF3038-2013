<?php include "template/is_login.php"; ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="Description" content="" />
		<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
		<title>MOA - Profil</title>
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/profil.css" />
	</head>
	<body>
		<?php
			$menu = array();
			$menu["Dashboard"] =  array("href" => "dashboard.php");
			$menu["Profil"] = array("href" => "profil.php", "class" => "active");
		?>
		<?php include "template/header.php";?>	
		<section>
			<div id="content_wrap" class="wrap">
				<div id="profil_left">
					<div id="profil_form_wrap">
						<div id="profil_head">
							<h1>Admin</h1>
						</div>
						<div class="row">
							<span class="label">Username</span>
							<span id="profil_username"></span>
						</div>
						<div class="row">
							<span class="label">Nama Lengkap</span>
							<span id="profil_name"></span>
						</div>
						<div class="row">
							<span class="label">Tanggal Lahir</span>
							<span id="profil_birth_date"></span>
						</div>
						<div class="row">
							<span class="label">Email</span>
							<span id="profil_email"></span>
						</div>
						<div class="row">
							<span class="label">Avatar</span>
							<img id="avatar_image" src="images/avatar.jpg" alt="avatar profil" />
						</div>
						<a href="changepass.php" class="button_link"> Ganti Sandi</a>
						<a href="profiledit.php" class="button_link"> Edit Profil </a>
						
					</div>
				</div>
				<div id="profil_right">
					<div id="task_head">
						<h1>Tugas</h1>
					</div>
					<div id="task_left">
						<div class="profil_sub_head">
							<h4>Dalam Proses</h4>
						</div>
						<ul>
							<li>
								<div class="row">
									<div><a href="#">Pemrograman Internet</a></div>
								</div>
							</li>
						</ul>
					</div>
					<div id="task_right">
						<div class="profil_sub_head">
							<h4>Selesai</h4>
						</div>
						<ul>
							<li>
								<div class="row">
									<div><a href="#">Kriptografi</a></div>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="row">
									<div><a href="#">Intelegensi Buatan</a></div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<?php
			$breadcrumbs = array();
			$breadcrumbs["Dashboard"] = array("href" => "dashboard.php");
			$breadcrumbs["Profil"] = array("href" => "profil.php", "class" => "active");
		?>
		<?php include "template/footer.php";?>
		
		<script type="text/javascript" src="js/search.js"></script>
		<script type="text/javascript" src="js/logout.js"></script>
		<script type="text/javascript">
			window.onload = function()
			{
				var userlist =  JSON.parse(localStorage.MOA_userList);
				document.getElementById("profil_head").innerHTML = "<h1>"+userlist[sessionStorage.MOA_userId].fullName+"</h1>";
				document.getElementById("profil_username").innerHTML = userlist[sessionStorage.MOA_userId].userName;
				document.getElementById("profil_name").innerHTML = userlist[sessionStorage.MOA_userId].fullName;
				document.getElementById("profil_birth_date").innerHTML = userlist[sessionStorage.MOA_userId].birthDate;
				document.getElementById("profil_email").innerHTML = userlist[sessionStorage.MOA_userId].emailAddress;
				document.getElementById("avatar_image").src = userlist[sessionStorage.MOA_userId].Avatar;
			}
		</script>
	</body>
</html>

