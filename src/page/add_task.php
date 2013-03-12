<!DOCTYPE html>
<html>
	<head>
		<title>Add New Task</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<script>
			var chktaskname = false;
			var chkattach = false;
			var chkassignee = false;
			var chktag = false;
			function hide_create_button() {
				document.getElementById("create").style.display = 'none';
			}
			function show_create_button() {
				if (chktaskname == true && chkattach == true && chkassignee == true && chktag == true) {
					document.getElementById("create").style.display = 'block';
				}
			}
			function check_string() {
				var i;
				var str = document.getElementById("taskname").value;
				for (i = 0; i < str.length; i++) {
					if ((str.charAt(i) != ' ' && str.charCodeAt(i) < 48) || (str.charCodeAt(i) > 57 && str.charCodeAt(i) < 65) || (str.charCodeAt(i) > 90 && str.charCodeAt(i) < 97) || str.charCodeAt(i) > 122) {
						return false;
					}
				}
				return true;
			}
			function check_task_name() {
				if (document.getElementById("taskname").value.length > 0 && document.getElementById("taskname").value.length < 26) {
					chktaskname = check_string();
					if (chktaskname == true) {
						show_create_button();	
					} else {
						hide_create_button();
					}
				} else {
					chktaskname = false;
					hide_create_button();
				}
			}
			function check_attachment() {
				var str = document.getElementById("attached").value;
				var ext = str.substring(str.lastIndexOf('.') + 1, str.length).toLowerCase();
				if (ext == "pdf" || ext == "doc" || ext == "docx" || ext == "ppt" || ext == "pptx" || ext == "java" || ext == "jpg" || ext =="jpeg" || ext == "gif" || ext == "mp4") {
					chkattach = true;
					show_create_button();
				} else {
					chkattach = false;
					hide_create_button();
				}
			}
			function check_assignee() {
				if (document.getElementById("assignee").value.length > 0) {
					chkassignee = true;
					show_create_button();
				} else {
					chkassignee = false;
					hide_create_button();
				}
			}
			function check_tag() {
				if (document.getElementById("tag").value.length > 0) {
					chktag = true;
					show_create_button();
				} else {
					chktag = false;
					hide_create_button();
				}
			}
			function check_html5() {
				if (navigator.userAgent.indexOf('Chrome') != -1 || navigator.userAgent.indexOf('Opera') != -1){
					document.getElementById("date_html5").style.display = 'block';
					document.getElementById("date_html").style.display = 'none';
				} else {
					document.getElementById("date_html5").style.display = 'none';
					document.getElementById("date_html").style.display = 'block';
				}
			}
		</script>
	</head>
	<body onLoad="check_html5()">
		<div id="main-body">
			<!--Header-->
			<div id="header">
				<?php
					include("header.php");
				?>
			</div>
			<div><hr id="border"></div>
			<!--Body-->
			<div id="task-page-body">
				<h1>Category: IF3054 Inteligensi Buatan</h1>
				<div id="add-task">
					Task name:<br>
					Attach file:<br>
					Deadline:<br><br>
					Asignee:<br>
					Tag:<br>
				</div>
				<div id="add-task-form">
				<form>
					<!--Name-->
					<input type="text" id="taskname" onKeyUp="check_task_name()"/><br />
					<!--Attachment-->
					<input type="file" id="attached" onChange="check_attachment()"/><br />
					<!--Deadline-->
					<input type="date" id="date_html5" /><div id="date_html">
							<select>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
							</select>&nbsp;
							<select>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>&nbsp;
							<select>
								<option value="1955">1955</option>
								<option value="1956">1956</option>
								<option value="1957">1957</option>
								<option value="1958">1958</option>
								<option value="1959">1959</option>
								<option value="1960">1960</option>
								<option value="1961">1961</option>
								<option value="1962">1962</option>
								<option value="1963">1963</option>
								<option value="1964">1964</option>
								<option value="1965">1965</option>
								<option value="1966">1966</option>
								<option value="1967">1967</option>
								<option value="1968">1968</option>
								<option value="1969">1969</option>
								<option value="1970">1970</option>
								<option value="1971">1971</option>
								<option value="1972">1972</option>
								<option value="1973">1973</option>
								<option value="1974">1974</option>
								<option value="1975">1975</option>
								<option value="1976">1976</option>
								<option value="1977">1977</option>
								<option value="1978">1978</option>
								<option value="1979">1979</option>
								<option value="1980">1980</option>
								<option value="1981">1981</option>
								<option value="1982">1982</option>
								<option value="1983">1983</option>
								<option value="1984">1984</option>
								<option value="1985">1985</option>
								<option value="1986">1986</option>
								<option value="1987">1987</option>
								<option value="1988">1988</option>
								<option value="1989">1989</option>
								<option value="1990">1990</option>
								<option value="1991">1991</option>
								<option value="1992">1992</option>
								<option value="1993">1993</option>
								<option value="1994">1994</option>
								<option value="1995">1995</option>
								<option value="1996">1996</option>
								<option value="1997">1997</option>
								<option value="1998">1998</option>
								<option value="1999">1999</option>
								<option value="2000">2000</option>
								<option value="2001">2001</option>
								<option value="2002">2002</option>
								<option value="2003">2003</option>
								<option value="2004">2004</option>
								<option value="2005">2005</option>
								<option value="2006">2006</option>
								<option value="2007">2007</option>
								<option value="2008">2008</option>
								<option value="2009">2009</option>
								<option value="2010">2010</option>
								<option value="2011">2011</option>
								<option value="2012">2012</option>
							</select>
						</div><br /><br />
					<!--Assignee-->
					<input type="text" id="assignee" onKeyUp="check_assignee()"/><br />
					<!--Tag (multivalue)-->
					<input type="text" id="tag" onKeyUp="check_tag()"/><br />
					<br>
					<button id="create">Add Task</button>
				</form>
				</div>
			</div>
		</div>
	</body>
</html>