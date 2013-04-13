<html>
	<head>
		<title>Insert New Task</title>
		<link href="styles/newtask.css" rel="stylesheet" type="text/css" />
		<link href="styles/header.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="styles/calendar.css">
		<script src="js/calendar.js" > </script>
		<script>
			var fullPath = "empty";

//			function makeTgl(){
//				for(var i=1; i<=31; i++){
//					var isi=document.createTextNode(i);
//					var opsi = document.createElement("option");
//					opsi.setAttribute("value",i);
//					opsi.appendChild(isi);
//					document.getElementById("tgl").appendChild(opsi);
//				}
//			}
//
//			function makeThn(){
//				for(var i=1955; i<=2013; i++){
//					var isi=document.createTextNode(i);
//					var opsi = document.createElement("option");
//					opsi.setAttribute("value",i);
//					opsi.appendChild(isi);
//					document.getElementById("thn").appendChild(opsi);
//				}
//			}

			function checkTaskName(taskName){
				var pattern = /[^a-zA-Z0-9 ]/
				var l = taskName.length;
				if(!pattern.test(taskName)){         
					document.getElementById('v_tname').innerHTML='<img src="images/Check.png" title="Benar"/img>';
					return true;
				}else{   
					document.getElementById('v_tname').innerHTML='<img src="images/Cross.png" title="Nama task tidak boleh menggunakan karakter khusus"/img>';
					return false;
				}
			}

			function checkAssignee(assignee){
				var pattern = /[a-zA-Z] [a-zA-Z]/
				if(pattern.test(assignee)){         
					document.getElementById('v_assignee').innerHTML='<img src="images/Check.png" title="Benar"/img>'; 
					return true;
				}else{   
					document.getElementById('v_assignee').innerHTML='<img src="images/Cross.png" title="Nama harus tersusun dari minimal 2 kata"/img>';
					return false;
				}
			}

			function checkAttachment(attachment,v_attachment) {
				attachment = attachment.substring(attachment.lastIndexOf('.')+1);
				if (attachment) {
					var startIndex = (attachment.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
					var filename = attachment.substring(startIndex);
					if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0)
						filename = filename.substring(1);
					if(filename == "docx"|| filename == "jpeg"|| filename == "jpg" || filename == "bmp" || filename == "gif" || filename == "pdf" || filename == "doc" 
						|| filename == "docx" || filename == "xls" || filename == "xlsx" || filename == "ppt" || filename == "pptx" || filename == "mp4"
						|| filename == "ogg"|| filename == "webm"|| filename == "3gp"){         
						document.getElementById(v_attachment).innerHTML='<img src="images/Check.png" title="Benar"/img>'; 
						return true;
					}else{   
						document.getElementById(v_attachment).innerHTML='<img src="images/Cross.png" title="Ekstensi file tidak valid"/img>';
						return false;
					}
				}
			}
		</script>
	</head>

	<body onload="">
		<div id="container">
			<div id="header">
        	<div class=logo id="logo">
				<a href="dashboard.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
			</div>
			<div id="space">
			</div>
			<div id="search">
				<input type="text" name="search" id="searchbox">
				<button type="submit" id="searchbutton"></button>
			</div>
			<div class="menu" id="logout">
				<a href="index.html">Logout</a>
			</div>
			<div class="menu" id="profile">
				<a href="profile.html">Profile</a>
			</div>
			<div class="menu" id="home">
				<a href="dashboard.html">Home</a>
			</div>
        </div>
			<div id="leftspace">
				
			</div>
			<div id="newtask">
				
                <form name="newtask_form" method="POST" action="createtask" enctype="multipart/form-data">
					<div id="newtask_space">
					</div>
					<div id="formulir">
						<div class="form_field">
							<div class="newtask_label">
								Nama Tugas
							</div>
							<div class="newtask_field">
								<input name="taskname" type="text" size="35" maxlength="25" onkeyup="checkTaskName(document.newtask_form.taskname.value)" class="inputtext">
							</div>
							<div class="newtask_warning" id="v_tname">
							</div>
						</div><br>
						<div class="form_field" id="form_attach">
							<div class="newtask_label">
								Attachment
							</div>
                            <br>
							<div class="newtask_field">
								<input type="file" name="attachment" id="upload" onchange="checkAttachment(document.newtask_form.attachment.value,'va1')"/>
							</div>
							<div class="newtask_warning" id="va1">
							</div>
                            <br>
							<div class="newtask_field">
								<input type="file" name="attachment2" id="upload" onchange="checkAttachment(document.newtask_form.attachment.value,'va2')"/>
							</div>
							<div class="newtask_warning" id="va2">
							</div>
                            <br>
							<div class="newtask_field">
								<input type="file" name="attachment3" id="upload" onchange="checkAttachment(document.newtask_form.attachment.value,'va3')"/>
							</div>
							<div class="newtask_warning" id="va3">
							</div>
                            <br>
						</div><br>
						<div class="form_field">
							<div class="newtask_label">
								Deadline
							</div>
							<div class="newtask_field">
                                <input type="text" name="deadline" id="datedead" onmousedown="dead_validating()" />
                                <script type="text/javascript">
                                    calendar.set("datedead");
                                </script>
                                
							</div>
							<div class="newtask_warning" id="v_deadline">
							</div>
						</div><br>
						<div class="form_field">
							<div class="newtask_label">
								Assignee
							</div>
							<div class="newtask_field">
								<input type="text" name="assignee" size="35" maxlength="256" onkeyup="checkAssignee(document.newtask_form.assignee.value)" class="inputtext">
							</div>
							<div class="newtask_warning" id="v_assignee">
							</div>
						</div><br>
						<div class="form_field">
							<div class="newtask_label">
								Tag
							</div>
							<div class="newtask_field">
								<input type="text" name="tag" size="35" maxlength="256"  class="inputtext">
							</div>
							<div class="newtask_warning" id="v_tag">
							</div>
						</div><br>
						<div class="form_field">
							<div class="newtask_label">
							</div>
							<div class="newtask_field">
                                <input type="hidden" name="kategori"  value="<%= request.getParameter("idkategori") %>">
								<input type="submit" value="Add Task" id="addtask_button">
							</div>
						</div><br>
					</div>
				</form>
			</div>
			<div id="rightspace">
				
			</div>
		</div>
	</body>
</html>