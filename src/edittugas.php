<?php include "template/is_login.php"; ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="Description" content="" />
		<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
		<title>MOA - Edit Tugas</title>
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/work.css" />
	</head>
	<body>
		<?php
			$menu = array();
			$menu["Dashboard"] =  array("href" => "dashboard.php", "class" => "active");
			$menu["Profil"] = array("href" => "profil.php");
		?>
		<?php include "template/header.php";?>		
		<section>
			<div id="content_wrap" class="wrap">
				<div id="work_area">
					<div id="edit_task_form_wrap">
						<div id="work_head">
							<h1>Edit Tugas</h1>
						</div>
						<form id="edit_task_form" action="dashboard.html" method="post">
							<div class="row">
								<label for="category_name">Nama Kategori</label>
								<select id="category_name"></select>
							</div>
							<div class="row">
								<label for="edit_task_name">Nama Tugas</label>
								<input id="edit_task_name" name="edit_task_name" pattern="^[a-zA-Z0-9 ]{1,25}$" type="text" title="Nama tugas maksimal 25 karakter terdiri dari karakter alfanumerik dan spasi." required />
							</div>
							<div class="row">
								<label for="edit_task_attachment">Attachment</label>
								<input id="edit_task_attachment" name="edit_task_attachment" type="file" title="Attachment dari tugas." />
							</div>
							<div class="row">
								<label for="birth_date">Deadline</label>
								<input id="birth_date" name="birth_date" type="text" pattern="^[0-3][0-9]/[0-1][0-9]/[1-9][0-9][0-9][0-9]$" onclick="datePicker.showCalendar(event);" title="Deadlinenya harus setelah hari ini." required/>
							</div>
							<div class="row">
								<label for="edit_task_username">Asignee</label>
								<input id="edit_task_username" name="edit_task_username" list="asignee_option" pattern="^[^;]{5,}(;[^;]{5,})*$" type="text" title="Asignee yang dimasukkan dan terdaftar dan dipisahkan tanda titik-koma. Jika tidak dimasukkan akan jadi task pribadi."/>
								<datalist id="asignee_option">
									<option value="admin">
								</datalist>
							</div>
							<div class="row">
								<label for="edit_task_tag">Tag</label>
								<input id="edit_task_tag" name="edit_task_tag" pattern="^[^,]+(,[^,]+)*$" type="text" title="Tag harus dimasukkan dan dipisahkan tanda koma jika banyak." required />
							</div>
							<input id="edit_task_submit" type="submit" value="Edit" disabled="disabled" title="Semua elemen form harus diisi dengan benar dahulu."/>
						</form>
					</div>
				</div>
			</div>
		</section>
		<?php
			$breadcrumbs = array();
			$breadcrumbs["Dashboard"] = array("href" => "dashboard.php");
			$breadcrumbs["Edit Tugas"] = array("href" => "#", "class" => "active");
		?>
		<?php include "template/footer.php";?>
		
		<?php include "template/calendar.php"; ?>
		
		<script type="text/javascript" src="js/search.js"></script>
		<script type="text/javascript" src="js/logout.js"></script>
		<script type="text/javascript">			
			/*----- Bagian tugas baru ----*/
			var edit_task_form = document.getElementById("edit_task_form");
			
			var edit_task_name = document.getElementById("edit_task_name");
			var edit_task_attachment = document.getElementById("edit_task_attachment");
			var birth_date = document.getElementById("birth_date");
			var edit_task_username = document.getElementById("edit_task_username");
			var edit_task_tag = document.getElementById("edit_task_tag");
			
			edit_task_form.onsubmit = function()
			{
				var listcategory = JSON.parse(localStorage.MOA_categoryList);
				var listtask = JSON.parse(localStorage.MOA_taskList);
				var category_name = document.getElementById("category_name");
				if ((category_name.value >= 0)&&(category_name.value < listcategory.length))
				{
					var userlist = JSON.parse(localStorage.MOA_userList);
					var task = listtask[number];
					task.category = category_name.value;
					task.name = edit_task_name.value;
					task.attachment = (edit_task_attachment.value=="")? task.attachment : "task_files/"+edit_task_attachment.value.split("\\")[edit_task_attachment.value.split("\\").length-1];
					task.deadline = birth_date.value;
					var asignee = new Array();
					if (edit_task_username.value!="")
					{
						var names = edit_task_username.value.split(";");
						for (i=0;i<names.length;++i)
						{
							if (names[i]!='')
								asignee[i] = names[i];
						}
					}
					else
					{
						asignee[asignee.length] = userlist[sessionStorage.MOA_userId].userName;
					}
					task.asignee = asignee;
					var tag = new Array();
					var tags = edit_task_tag.value.split(",");
					for (i=0;i<tags.length;++i)
					{
						if (tags[i]!='')
							tag[i] = tags[i];
					}
					task.tag = tag;
					
					listtask[number] = task;
					localStorage.MOA_taskList = JSON.stringify(listtask);
					return true;
				}
				else
				{
					alert("Jangan aneh-aneh ya.");
					return false;
				}
				return false;
			}
			
			edit_task_name.onkeyup = function()
			{
				if (this.checkValidity())
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			edit_task_username.onkeyup = function()
			{
				if ((this.checkValidity())&&(check_username(this.value)))
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
			
			edit_task_tag.onkeyup = function()
			{
				if (this.checkValidity())
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			edit_task_attachment.onchange = function()
			{
				if (check_file(this))
					this.style.backgroundImage = "url('images/valid.png')";
				else
					this.style.backgroundImage = "url('images/warning.png')";
				check_submit();
			}
			
			function check_date(date)
			{
				var temp = date.split("/");
				var d = new Date(parseInt(temp[2]), parseInt(temp[1]) - 1, parseInt(temp[0]));
				var now = new Date();
				if ((d) && ((d.getMonth() + 1) == parseInt(temp[1])) && (d.getDate() == Number(parseInt(temp[0]))) && 
				(d >= now))
				{
					datePicker.populateTable(d.getMonth(),d.getFullYear());
					return true;
				}
				else
					return false;
			}
			
			function check_file(image)
			{
				if (image.value=="")
					return true;
				return (image.value.match("^.+\.(jpe?g|JPE?G|png|PNG|gif|GIF|txt|TXT|pdf|PDF|mp4|ogv|webm|MP4|OGV|WEBM)$"));
			}
			
			function check_username(username)
			{
				if (username!="")
				{
					var userlist = JSON.parse(localStorage.MOA_userList);
					var names = username.split(";");
					var check1 = true;
					var i=0;
					while ((check1)&&(i<names.length))
					{
						if (names[i]!='')
						{
							var check2 = true;
							var j=0;
							while ((check2)&&(j<userlist.length))
							{
								if (names[i]==userlist[j].userName)
									check2 = false;
								j++;
							}
							if (check2)
								check1 = false;
						}
						i++;
					}
					return check1;
				}
				return true;
			}
			
			function check_submit()
			{
				if (edit_task_name.checkValidity()&& edit_task_username.checkValidity() && 
					birth_date.checkValidity() && edit_task_tag.checkValidity() && 
					check_file(edit_task_attachment) && check_date(birth_date.value) && check_username(edit_task_username.value))
				{
					document.getElementById("edit_task_submit").disabled="";
				}
				else
				{
					document.getElementById("edit_task_submit").disabled="disabled";
				}
			}
		</script>
		<script type="text/javascript">
			var number;
			window.onload = function()
			{
				datePicker.init(document.getElementById("calendar"), document.getElementById("edit_task_form"));
				var category_name = document.getElementById("category_name");
				var listcategory = JSON.parse(localStorage.MOA_categoryList);
				var listtask = JSON.parse(localStorage.MOA_taskList);
				number = window.location.search.replace( "?", "" )=='' ? "taskId=0" : window.location.search.replace( "?", "" );
				number = number.split("=")[number.split("=").length-1];
				for (i=0;i<listcategory.length;++i)
				{
					var tempstring = "";
					tempstring += '<option';
					if (i==listtask[number].category)
						tempstring += ' selected="selected"';
					tempstring += ' value="'+i+'">';
					tempstring += listcategory[i].name+'</option';
					category_name.innerHTML += tempstring;
				}
				document.getElementById("edit_task_name").value = listtask[number].name;
				document.getElementById("birth_date").value = listtask[number].deadline;
				var asignee = listtask[number].asignee;
				for (j=0;j<asignee.length-1;++j)
				{
					document.getElementById("edit_task_username").value += asignee[j];
					document.getElementById("edit_task_username").value += ";";
				}
				document.getElementById("edit_task_username").value += asignee[asignee.length-1];
				var tags = listtask[number].tag;
				for (j=0;j<tags.length-1;++j)
				{
					document.getElementById("edit_task_tag").value += tags[j];
					document.getElementById("edit_task_tag").value += ",";
				}
				document.getElementById("edit_task_tag").value += tags[tags.length-1];
				
				category_name.pattern = "^[0-9]+$";
				check_submit();
			}
		</script>
	</body>
</html>
