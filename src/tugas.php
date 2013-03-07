<?php include "template/is_login.php"; ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="Description" content="" />
		<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
		<title>MOA - Detail Tugas</title>
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
				<div id="task_left">
					<div id="task_form_wrap">
						<div id="work_head">
							<h1>Tugas</h1>
						</div>
						<div class="row">
							<span class="label">Nama Kategori</span>
							<span id="category_name"></span>
						</div>
						<div class="row">
							<span class="label">Nama Tugas</span>
							<span id="task_name"></span>
						</div>
						<div class="row">
							<span class="label">Attachment</span>
							<div id="task_attachment"></div>
						</div>
						<div class="row">
							<span class="label">Deadline</span>
							<span id="birth_date"></span>
						</div>
						<div class="row">
							<span class="label">Asignee</span>
							<span id="task_username"></span>
						</div>
						<div class="row">
							<span class="label">Tag</span>
							<span id="task_tag"></span>
						</div>
						<div class="clear"></div>
						<a id="edit_button" class="button_link">Edit Tugas</a>
					</div>
				</div>
				<div id="task_right">
					<div id="work_head">
						<h1>Komentar</h1>
					</div>
					<div id="comments">
					</div>
					<div id="comment_form_wrap">
						<form id="comment_form" method="post">
							<div class="row">
								<label for="comment_input">Komentar</label>
								<textarea id="comment_input"></textarea>
							</div>
							<input id="comment_submit" type="submit" value="Komen"/>
						</form>
					</div>
				</div>
			</div>
		</section>
		<?php
			$breadcrumbs = array();
			$breadcrumbs["Dashboard"] = array("href" => "dashboard.php");
			$breadcrumbs["Detail Tugas"] = array("href" => "#", "class" => "active");
		?>
		<?php include "template/footer.php";?>
		
		<script type="text/javascript" src="js/search.js"></script>
		<script type="text/javascript" src="js/logout.js"></script>
		<script type="text/javascript">
			var comment_form = document.getElementById("comment_form");
			var comments = document.getElementById("comments");
			var comment_input = document.getElementById("comment_input");
			comment_form.onsubmit = function()
			{
				var listtask = JSON.parse(localStorage.MOA_taskList);
				var userlist = JSON.parse(localStorage.MOA_userList);
				var commentars = listtask[number].comment;
				var username = userlist[sessionStorage.MOA_userId].userName;
				var tempstring = '<div class="comment">';
					tempstring += username+': '+comment_input.value;
				tempstring += '</div>';
				comments.innerHTML += tempstring;
				
				var comment = new Object();
				comment.commentator = username;
				comment.talk = comment_input.value;
				commentars[commentars.length] = comment;
				listtask[number].comment = commentars;

				localStorage.MOA_taskList = JSON.stringify(listtask);
				comment_input.value = "";
				return false;
			}
			var number;
			window.onload = function()
			{
				var listtask = JSON.parse(localStorage.MOA_taskList);
				var listcategory = JSON.parse(localStorage.MOA_categoryList);
				number = window.location.search.replace( "?", "" )=='' ? "taskId=0" : window.location.search.replace( "?", "" );
				number = number.split("=")[number.split("=").length-1];
				document.getElementById("category_name").innerHTML = listcategory[listtask[number].category].name;
				document.getElementById("task_name").innerHTML = listtask[number].name;
				var task_attachment = document.getElementById("task_attachment");
				var extension = listtask[number].attachment.split(".")[listtask[number].attachment.split(".").length-1];
				if (extension.match("^(jpe?g|JPE?G|png|PNG|gif|GIF)$"))
				{
					task_attachment.innerHTML = "<img src='"+listtask[number].attachment+"' alt='attachment'/>";
				}
				else if (extension.match("^(txt|TXT|pdf|PDF)$"))
				{
					task_attachment.innerHTML = "<span><a href='"+listtask[number].attachment+"' target='_blank'>Unduh attachment</a></span>";
				}
				else if (extension.match("^(mp4|ogv|webm|MP4|OGV|WEBM)$"))
				{
					var tempstring = "<video width='200px' height='200px' controls='controls' autoplay='autoplay'>";
						tempstring += "<source src='"+listtask[number].attachment+"' type='video/"+extension+"' >";
						tempstring += "\"Your browser does not support video tag\"";
					tempstring += "</video>";
					task_attachment.innerHTML = tempstring;
				}
				else
				{
					task_attachment.innerHTML = "<span>Attachment tidak didukung.</span>"
				}
				
				document.getElementById("birth_date").innerHTML = listtask[number].deadline;
				var asignee = listtask[number].asignee;
				for (j=0;j<asignee.length-1;++j)
				{
					document.getElementById("task_username").innerHTML += asignee[j];
					document.getElementById("task_username").innerHTML += ";";
				}
				document.getElementById("task_username").innerHTML += asignee[asignee.length-1];
				var tags = listtask[number].tag;
				for (j=0;j<tags.length-1;++j)
				{
					document.getElementById("task_tag").innerHTML += tags[j];
					document.getElementById("task_tag").innerHTML += ",";
				}
				document.getElementById("task_tag").innerHTML += tags[tags.length-1];
				
				var commentars = listtask[number].comment;
				comments.innerHTML = "";
				for (i=0;i<commentars.length;++i)
				{
					var tempstring = '<div class="comment">';
					tempstring += commentars[i].commentator+': '+commentars[i].talk;
					tempstring += '</div>';
					comments.innerHTML += tempstring;
				}
				
				var edit_button = document.getElementById("edit_button");
				edit_button.href = "edittugas.html?taskId="+number;
				
				document.getElementById("work_head").innerHTML = "<h1>Tugas "+listtask[number].name+"</h1>";
			}
		</script>
	</body>
</html>

