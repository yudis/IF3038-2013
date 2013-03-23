

<html>
    <head>
		<?php
		session_start();
		if(!isset($_SESSION['id']))
			header("location:index.php");
		?>
        <title>Dashboard</title>
        <link href="styles/header.css" rel="stylesheet" type="text/css" />
        <link href="styles/search.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="search.js"></script>
		<script>
			function searchWord1(){
				var find = document.search.find.value;
				var searching = document.search.searching.value;
				var field = document.search.field.value;
				var xmlhttp;
				document.getElementById("task1").innerHTML="";
				if (find.length==0) { 
					  document.getElementById("task1").innerHTML="";
					  return;
				}
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("task1").innerHTML=xmlhttp.responseText;
				  }
				  
				}
				
				xmlhttp.open("GET","functionsearch.php?find="+find+"&searching="+searching+"&field="+field,true);
				xmlhttp.send();
			}
		</script>
		<script>
			function searchWord2(){
				var find = document.search.find.value;
				var searching = document.search.searching.value;
				var field = document.search.field.value;
				var xmlhttp;
				document.getElementById("task2").innerHTML="";
				if (find.length==0) { 
					  document.getElementById("task2").innerHTML="";
					  return;
				}
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("task2").innerHTML=xmlhttp.responseText;
				  }
				  
				}
				
				xmlhttp.open("GET","functionsearch2.php?find="+find+"&searching="+searching+"&field="+field,true);
				xmlhttp.send();
			}
		</script>
		<script>
			function searchWord3(){
				var find = document.search.find.value;
				var searching = document.search.searching.value;
				var field = document.search.field.value;
				var xmlhttp;
				document.getElementById("task3").innerHTML="";
				if (find.length==0) { 
					  document.getElementById("task3").innerHTML="";
					  return;
				}
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("task3").innerHTML=xmlhttp.responseText;
				  }
				  
				}
				
				xmlhttp.open("GET","functionsearch3.php?find="+find+"&searching="+searching+"&field="+field,true);
				xmlhttp.send();
			}
		</script>
		<script>
			function changeStatus(idtugas){
				var find = document.search.find.value;
				var searching = document.search.searching.value;
				var field = document.search.field.value;
				var xmlhttp;
				document.getElementById("task3").innerHTML="";
				if (find.length==0) { 
					  document.getElementById("task3").innerHTML="";
					  return;
				}
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("task3").innerHTML=xmlhttp.responseText;
				  }
				  
				}
				
				xmlhttp.open("GET","changestatussearch2.php?find="+find+"&searching="+searching+"&field="+field+"&q="+idtugas,true);
				
				xmlhttp.send();
			}
		</script>
		<script>
			function searchWords(){
					searchWord1();
					searchWord2();
					searchWord3();
			}
		</script>
        <script>
            function showK1(){
                document.getElementById('k1').style.borderColor = '#836fff';
                document.getElementById('k2').style.borderColor = '#d9d9d9';
                document.getElementById('k3').style.borderColor = '#d9d9d9';
                document.getElementById('k11').style.display = 'block';
                document.getElementById('k12').style.display = 'block';
                document.getElementById('k13').style.display = 'block';
                document.getElementById('k14').style.display = 'block';
                document.getElementById('k21').style.display = 'none';
                document.getElementById('k22').style.display = 'none';
                document.getElementById('k31').style.display = 'none';
            }
        </script>

        <script>
            function showK2(){
                document.getElementById('k1').style.borderColor = '#d9d9d9';
                document.getElementById('k2').style.borderColor = '#836fff';
                document.getElementById('k3').style.borderColor = '#d9d9d9';
                document.getElementById('k11').style.display = 'none';
                document.getElementById('k12').style.display = 'none';
                document.getElementById('k13').style.display = 'none';
                document.getElementById('k14').style.display = 'none';
                document.getElementById('k21').style.display = 'block';
                document.getElementById('k22').style.display = 'block';
                document.getElementById('k31').style.display = 'none';
            }
        </script>

        <script>
            function showK3(){
                document.getElementById('k1').style.borderColor = '#d9d9d9';
                document.getElementById('k2').style.borderColor = '#d9d9d9';
                document.getElementById('k3').style.borderColor = '#836fff';
                document.getElementById('k11').style.display = 'none';
                document.getElementById('k12').style.display = 'none';
                document.getElementById('k13').style.display = 'none';
                document.getElementById('k14').style.display = 'none';
                document.getElementById('k21').style.display = 'none';
                document.getElementById('k22').style.display = 'none';
                document.getElementById('k31').style.display = 'block';
            }
        </script>
    </head>
		
		<body onLoad = "searchWords();">

        <div id="container">
            <div id="header">
                <div class=logo id="logo">
                    <a href="dashboard.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
                </div>
                <div id="space">
                </div>
                <div class = "menu" id = "search" action="search.php">
                    <form name="search" method="post">
                        Search for: <input type="text" onkeyup= "searchWords();" name="find" value = "<?php
			//check whether the variable $_POST['name'] exists
			//this condition will be false for the loading
			if(isset($_POST['find'])){
			  $name = $_POST['find'];
			}
			else{
			  //if the $_POST['name'] is not set
			  $name = '';
			}
			echo $name;
		?>"/> in 
                        <Select NAME="field" onchange= "searchWords();">
                            <Option VALUE="semua"
							<?php
								//check whether the variable $_POST['name'] exists
								//this condition will be false for the loading
								if(isset($_POST['field']) && ($_POST['field']) == 'semua'){
								  echo 'selected';
								}
							?>
							>Semua</option>
                            <Option VALUE="username"
							<?php
								//check whether the variable $_POST['name'] exists
								//this condition will be false for the loading
								if(isset($_POST['field']) && ($_POST['field']) == 'username'){
								  echo 'selected';
								}
							?>
							>Username</option>
                            <Option VALUE="namakategori"
							<?php
								//check whether the variable $_POST['name'] exists
								//this condition will be false for the loading
								if(isset($_POST['field']) && ($_POST['field']) == 'namakategori'){
								  echo 'selected';
								}
							?>
							>Judul Kategori</option>
                            <Option VALUE="tasktag"
							<?php
								//check whether the variable $_POST['name'] exists
								//this condition will be false for the loading
								if(isset($_POST['field']) && ($_POST['field']) == 'tasktag'){
								  echo 'selected';
								}
							?>
							>Task atau Tag</option>
							
  
	
                        </Select>
                        <input type="hidden" name="searching" value="yes" />
                        <button type="submit" id="searchbutton" onclick= "searchWords();"></button>
                    </form>
                </div>
                <div class="menu" id="logout">
                    <a href="index.php">Logout</a>
                </div>
                <div class="menu" id="home">
				<a href="dashboard.php">Home</a>
			</div>
			<div class="menu" id="profile">
				<a href="profile.php">
				<img alt="" height=25 width=25 src="<?php
					$con = mysql_connect("localhost:3306","progin","progin");
					if (!$con)
					  {
					  die('Could not connect: ' . mysql_error());
					  }

					mysql_select_db("progin_405_13510057", $con);
				
					$result = mysql_query("SELECT * FROM user WHERE username='$_SESSION[id]'");
					while($row = mysql_fetch_array($result)) 
						echo $row['avatar'];
				?>">
				Profile</a>
			</div>
            </div>

            <div id="category">
                <div id="category_head">
                    Hasil Search
                </div>
            </div>
            <div id="task1">

            </div>
            <div id="task2">
                
            </div>
            <div id="task3">

            </div>
        </div>
    </body>
</html>