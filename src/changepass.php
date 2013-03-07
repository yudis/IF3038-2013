<?php include "template/is_login.php"; ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="Description" content="" />
		<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
		<title>MOA - Ganti Sandi</title>
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
							<h1>Ganti Sandi</h1>
						</div>
						<form id="profil_form" method="post" action="profil.html">
							<div class="row">
								<label for="profil_password">Sandi baru</label>
								<input id="profil_password" name="password" pattern="^.{8,}$" type="password" title="Sandi minimal harus terdiri dari 8 karakter dan tidak sama dengan username dan email." required />							
							</div>
							<div class="row">
								<label for="profil_confirm_password">Konfirmasi Sandi baru</label>
								<input id="profil_confirm_password" name="confirm_password" pattern="^.{8,}$" type="password" title="Konfirmasi sandi harus sama dengan sandi." required />
							</div>
							<input id="profil_submit" type="submit" value="Ganti Sandi" disabled="disabled" title="Semua elemen form harus diisi dengan benar dahulu."/>
						</form>
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
			$breadcrumbs["Profil"] = array("href" => "profil.php");
			$breadcrumbs["Ganti Sandi"] = array("href" => "changepass.php", "class" => "active");
		?>
		<?php include "template/footer.php";?>

		<script type="text/javascript" src="js/search.js"></script>
		<script type="text/javascript" src="js/logout.js"></script>
		<script type="text/javascript">
			
			/*----- Bagian pass ----*/
			var pass_form = document.getElementById("pass_form");
			
			var profil_password = document.getElementById("profil_password");
			var profil_confirm_password = document.getElementById("profil_confirm_password");
			
			profil_form.onsubmit = function()
			{
				if ((profil_password.value==profil_confirm_password.value))
				{
					var userlist = JSON.parse(localStorage.MOA_userList);
					userlist[sessionStorage.MOA_userId].passWord=profil_password.value;
					localStorage.MOA_userList = JSON.stringify(userlist);
				}
				else if (profil_password.value!=profil_confirm_password.value)
				{
					alert("Konfirmasi sandi harus sama dengan sandi");
				}
			}
			
			profil_password.onkeyup = function()
			{
				if (this.checkValidity())
				{
					this.style.backgroundImage = "url('images/valid.png')";
					document.getElementById("profil_confirm_password").pattern = this.value;
				}
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			profil_confirm_password.onkeyup = function()
			{
				if (this.checkValidity() && profil_password.checkValidity())
					this.style.backgroundImage = "url('images/valid.png')";
				else
				
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			function check_submit()
			{
				if ((profil_password.checkValidity()) && 
					(profil_confirm_password.checkValidity()))
				{
					document.getElementById("profil_submit").disabled="";
				}
				else
				{
					document.getElementById("profil_submit").disabled="disabled";
				}
			}
			
		</script>
		
	</body>
</html>
