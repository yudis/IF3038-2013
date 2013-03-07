<?php
	$session_time = 30*24*60*60;
	ini_set('session.gc-maxlifetime', $session_time);

	session_start();
	if ((!ISSET($_SESSION['base_url'])) || (!ISSET($_SESSION['full_url'])) || (!ISSET($_SESSION['full_path'])))
	{
		$folder = substr($_SERVER['SCRIPT_NAME'], 0, strrpos($_SERVER['SCRIPT_NAME'], "/")+1);  
		$protocol = (ISSET($_SERVER['HTTPS']) && $_SERVER['HTTPS'] != "off") ? "https" : "http";
		$_SESSION['base_url'] = $folder;
		$_SESSION['full_url'] = $protocol . "://" . $_SERVER['HTTP_HOST'] . $folder;
		$_SESSION['full_path'] = $_SERVER['DOCUMENT_ROOT'].$folder;
	}
?>
<?php include $_SESSION['full_path']."template/is_login.php"; ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="Description" content="" />
		<link rel="shortcut icon" type="image/x-icon" href="<?php echo $_SESSION['base_url']; ?>images/favicon.ico" />
		<title>MOA - Edit Profil</title>
		<link rel="stylesheet" href="<?php echo $_SESSION['base_url']; ?>css/style.css" />
		<link rel="stylesheet" href="<?php echo $_SESSION['base_url']; ?>css/profil.css" />
	</head>
	<body>
		<?php
			$menu = array();
			$menu["Dashboard"] =  array("href" => "dashboard.php");
			$menu["Profil"] = array("href" => "profil.php", "class" => "active");
		?>
		<?php include $_SESSION['full_path']."template/header.php";?>
		<section>
			<div id="content_wrap" class="wrap">
				<div id="profil_left">
					<div id="profil_form_wrap">
						<form id="profil_form" action="profil.html" method="post">
							<div id="profil_head">
								<h1>Edit Profil</h1>
							</div>
							<div class="row">
								<label for="profil_name">Nama Lengkap</label>
								<input id="profil_name" name="name" pattern="^.+ .+$" type="text" title="Nama lengkap harus terdiri dari 2 kata dipisah oleh sebuah spasi." required/>
							</div>
							<div class="row">
								<label for="birth_date">Tanggal Lahir</label>
								<input id="birth_date" name="birth_date" pattern="^[0-3][0-9]/[0-1][0-9]/[1-9][0-9][0-9][0-9]$" type="text" onclick="datePicker.showCalendar(event);" title="Tahun harus minimal dari tahun 1955." required/>
							</div>
							<div class="row">
								<label for="profil_email">Email</label>
								<input id="profil_email" name="email" pattern="^.+@.+\...+$" type="text" title="Email yang dimasukkan harus benar dan tidak sama dengan sandi." required/>
							</div>
							<div class="row">
								<label for="profil_avatar">Avatar</label>
								<input id="profil_avatar" name="avatar" type="file" title="Avatar yang diupload harus berekstensi jpg atau jpeg."/>
							</div>
							<div class="row">
								<img id="avatar_image" src="images/avatar.jpg" alt="avatar profil" />
							</div>
							<input id="profil_submit" type="submit" value="Edit" title="Semua elemen form harus diisi dengan benar dahulu."/>
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
						<div class="row">
							<div id="profil_do_text" ><li><a href="#">Pemrograman Internet</a></li></div>
						</div>
					</div>
					<div id="task_right">
						<div class="profil_sub_head">
							<h4>Selesai</h4>
						</div>
						<div class="row">
							<div id="profil_do_text" ><li><a href="#">Kriptografi</a></li></div>
						</div>
						<div class="row">
							<div id="profil_do_text" ><li><a href="#">Intelegensi Buatan</a></li></div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<?php
			$breadcrumbs = array();
			$breadcrumbs["Dashboard"] = array("href" => "dashboard.php");
			$breadcrumbs["Profil"] = array("href" => "profil.php");
			$breadcrumbs["Edit Profil"] = array("href" => "profiledit.php", "class" => "active");
		?>
		<?php include $_SESSION['full_path']."template/footer.php";?>
		
		<?php include $_SESSION['full_path']."template/calendar.php"; ?>
		
		<script type="text/javascript" src="<?php echo $_SESSION['base_url']; ?>js/search.js"></script>
		<script type="text/javascript" src="<?php echo $_SESSION['base_url']; ?>js/logout.js"></script>
		<script type="text/javascript">
			/*----- Bagian Profil ----*/
			var profil_form = document.getElementById("profil_form");
			profil_form.onsubmit = function()
			{
				var userlist = JSON.parse(localStorage.MOA_userList);
				var profil_password = userlist[sessionStorage.MOA_userId].passWord;
				if (profil_email.value!=profil_password)
				{
					userlist[sessionStorage.MOA_userId].fullName = profil_name.value;
					userlist[sessionStorage.MOA_userId].birthDate = birth_date.value;
					userlist[sessionStorage.MOA_userId].emailAddress = profil_email.value;
					
					var tempavatar = (profil_avatar.value=="") ? userlist[sessionStorage.MOA_userId].Avatar : "images/"+profil_avatar.value.split("\\")[profil_avatar.value.split("\\").length-1];
					userlist[sessionStorage.MOA_userId].Avatar = tempavatar;
					
					localStorage.MOA_userList = JSON.stringify(userlist);
					return true;
				}
				return false;
			}
			
			var profil_name = document.getElementById("profil_name");
			var birth_date = document.getElementById("birth_date");
			var profil_email = document.getElementById("profil_email");
			var profil_avatar = document.getElementById("profil_avatar");
			
			profil_name.onkeyup = function()
			{
				if (this.checkValidity())
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			birth_date.onkeyup = function()
			{
				if ((this.checkValidity()) && (check_date(this.value)))
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			profil_email.onkeyup = function()
			{
				var userlist =  JSON.parse(localStorage.MOA_userList);
				var profil_password = userlist[sessionStorage.MOA_userId].passWord;
				if ((this.checkValidity())&&(this.value!=profil_password))
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			profil_avatar.onchange = function()
			{
				if (check_file_jpeg(this))
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			function check_date(date)
			{
				var temp = date.split("/");
				var d = new Date(parseInt(temp[2]), parseInt(temp[1]) - 1, parseInt(temp[0]));
				if ((d) && ((d.getMonth() + 1) == parseInt(temp[1])) && (d.getDate() == Number(parseInt(temp[0]))) && (parseInt(temp[2]) >= 1955))
				{
					datePicker.populateTable(d.getMonth(),d.getFullYear());
					return true;
				}
				else
					return false;
			}
			
			function check_file_jpeg(image)
			{
				return (image.value.match("^.+\.(jpe?g|JPE?G)$"));
			}
			
			function check_submit()
			{
				var userlist = JSON.parse(localStorage.MOA_userList);
				var profil_password = userlist[sessionStorage.MOA_userId].passWord;
				if ((profil_name.checkValidity()) && (birth_date.checkValidity()) && 
					(profil_email.checkValidity()) && ((profil_avatar.value=="")||(check_file_jpeg(profil_avatar))) &&
					(profil_email.value != profil_password.value) && (check_date(birth_date.value)))
				{
					document.getElementById("profil_submit").disabled="";
				}
				else
				{
					document.getElementById("profil_submit").disabled="disabled";
				}
			}
		</script>
		<script type="text/javascript">	
			window.onload = function() 
			{
				datePicker.init(document.getElementById("calendar"), document.getElementById("profil_form"));
				
				var userlist =  JSON.parse(localStorage.MOA_userList);
				document.getElementById("profil_name").value = userlist[sessionStorage.MOA_userId].fullName;
				document.getElementById("birth_date").value = userlist[sessionStorage.MOA_userId].birthDate;
				document.getElementById("profil_email").value = userlist[sessionStorage.MOA_userId].emailAddress;
				document.getElementById("avatar_image").src = userlist[sessionStorage.MOA_userId].Avatar;
			}
		</script>
	</body>
</html>

