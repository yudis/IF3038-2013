<!DOCTYPE html>
<?php
	if(!isset($_COOKIE['name'])){
		header("Location: ../index.php");
	}
?>
<?php include 'connect.php'?>
<?php
	$result = mysql_query("SELECT * FROM user WHERE username ='".$_COOKIE['name']."'");
	$user = mysql_fetch_array($result);
?>
<html>
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script>
		<script type="text/javascript" src="../js/profil.js"> </script>		
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>
	
	<body>
		<!-- Web Header -->
		<header>
			<div id="header_container"> 
				<div id="logo_container">
					<a href="dashboard.php"> <img src="../img/logo.png" alt=""> </a>
				</div>
				<input id="search_box" type="text" placeholder="Type here to search">
				<div id="header_menu"> 
					<div class="header_menu_button current_header_menu"> <a href="dashboard.php"> DASHBOARD </a>   </div>
					<div class="header_menu_button">  
					<a href="profile.php">
						<?php echo "<img id='header_img' src='../".$user['avatar']."'>";?>
						<div id="header_profile">
							&nbsp;&nbsp;<?php echo $user['username'];?>
						</div>					 
					</a> 
					</div>
					<div class="header_menu_button"> <a id="logout" href="../clear.php"> LOGOUT </a> </div>
				</div>
				
			</div>
		</header>
	
		
		<!-- Web Content -->
		<section>
			<div id="navbar">
				<div id="short_profile">
					
				</div>
				<div id="category_list">
					<div class="link_blue_rect" id="category_title"><a id="edit_button" onclick="getResult()">Search</a></div>
			<input id="searchBox" list="sought" onkeyup="autoComplete(this.value)">
			<datalist id="sought" >	
			</datalist>
			<br>
			<input type="checkbox" id="userName" checked="checked">User Name<br>
			<input type="checkbox" id="judulKategori" checked="checked">Judul Kategori<br>
			<input type="checkbox" id="taskTag" checked="checked">Judul Task dan Tag<br> 					
				</div>
			</div>
			<div id="dynamic_content">
			<div style="border: 1px solid black; position: relative; height: 400px; width: 500px;">	
				<div style="overflow: auto; height: 100%; width: 100%;" onscroll="OnDivScroll();" id="scrollContainer">
					<div id="result"></div>
					<div style="padding: 5px; position: absolute; bottom: 2px; right: 20px; display: none; background-color: Black; color: White;" id="loadingDiv">Loading...</div>
				</div>
			</div>										
			</div>
				
			</div>
		</section>
		
		<!-- Footer Section -->
		<footer>
			<div id="footer_container"> 
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
	</body>	
<script>
	var itemCount = 0;
			var taskCount = 0;
			var userCount = 0;
			var itemShow = 0;
			var taskList;
			var userList;
			function getResult(){
				if (window.XMLHttpRequest)
				  {// code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				  }
				else
				  {// code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				  }	
				xmlhttp.onreadystatechange=function()
				  {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200)
					{
						document.getElementById("result").innerHTML = "Loading";
						showResult(xmlhttp.responseText);
					}
				  }  
				xmlhttp.open("GET","result.php?",true);
				xmlhttp.send();
			}
			function showResult(str){
				var resultShow = "";
				var firstSplit = str.split("<end_task>");
				taskList = firstSplit[0].split("<pemisah>");
				userList = firstSplit[1].split("<pemisah>");
				userList.shift();
				taskCount = taskList.length;
				userCount = userList.length
				itemCount = taskCount + userCount;
				resultShow = resultShow+"<h1>TASK</h1>";
				while((itemShow < 10) && (itemShow < itemCount)){
					if (itemShow < taskCount){
						resultShow = resultShow+"<br>"+taskList[itemShow];
						itemShow++;
					}
					else if (itemShow-taskCount == 0){
						resultShow = resultShow+"<br><h1>USER</h1><br>";
						resultShow = resultShow+"<br>"+userList[itemShow-taskCount];
						itemShow++;
					}
					else{
						resultShow = resultShow+"<br>"+userList[itemShow-taskCount];
						itemShow++;
					}
				}
				document.getElementById("result").innerHTML = resultShow;
			}
			// -------------------------------------------------------------------
			function OnDivScroll()
			{  
			  var el = document.getElementById('scrollContainer');
			  if(el.scrollTop < el.scrollHeight - 500)
				return;
			  
			  
			  var loading = document.getElementById('loadingDiv');
			  if(loading.style.display == '')
				return; //already loading
			
			  if(itemShow == itemCount)
				return;
				
			  loading.style.display = '';
			  
			  LoadMoreElements();
			}
	
			function LoadMoreElements()
			{
			  //Do a server callback to load 
			  //more elements
			  setTimeout(LoadCallback, 350);
			}

			function LoadCallback()
			{
			  var el = document.getElementById('scrollContainer');
			  var loading = document.getElementById('loadingDiv');
			  var resultShow = document.getElementById('result').innerHTML;
			  loading.style.display = 'none';
			  
			  if(itemShow < taskCount){
				resultShow = resultShow+'<br>'+taskList[itemShow];
				document.getElementById('result').innerHTML = resultShow;
				itemShow++;
			  }
			  else if (itemShow-taskCount == 0){
				resultShow = resultShow+"<br><h1>USER</h1><br>";
				resultShow = resultShow+'<br>'+userList[itemShow-taskCount];
				document.getElementById('result').innerHTML = resultShow;
				itemShow++;
			  }
			  else{
				resultShow = resultShow+'<br>'+userList[itemShow-taskCount];
				document.getElementById('result').innerHTML = resultShow;
				itemShow++;
			  }
			  
			}
</script>	
</html>