<?php 
	$user_id = 1;
	
	function printCategories($user_id)
	{
		require_once("connectdb.php");
		$query = "SELECT nama, idkategori FROM kategori, asignee_has_kategori WHERE idkategori = kategori_idkategori AND accounts_idaccounts = ".$user_id;
		//mysql_query($query);
		$result = mysql_query($query, $sql);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<div class="tulcat" id ="id'.$row["idkategori"].'" onClick="showTasks('.$row["idkategori"].')"><a href="#"><div class="tombol_hapus">X</div></a>'.$row["nama"].'</div></a>';
		}
		
		mysql_close($sql);
	}
?>

<html>
    <head>
        <title> Next | Dashboard </title>
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/dash.css">
        <script type="text/javascript" src="js/popup.js"></script>
        
		<!--AJAX AUTOCOMPLETE ASSIGNEE-->
		<script>
			function showResult(str)
			{
				if(str.length==0)
				{
					document.getElementById("hasil_autocomplete").innerHTML="";
					document.getElementById("hasil_autocomplete").style.border="0px";
					return;
				}
				
				var str_arr = str.split(", ");
				
				if(window.XMLHttpRequest)
				{
					// untuk IE7, Firefox, Chrome, Opera, Safari
					xmlhttp = new XMLHttpRequest();
				}
				else
				{
					//untuk IE jadul
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				
				xmlhttp.onreadystatechange = function()
				{
					if (xmlhttp.readyState==4 && xmlhttp.status==200)
					{
						document.getElementById("hasil_autocomplete").innerHTML=xmlhttp.responseText;
						document.getElementById("hasil_autocomplete").style.border="1px solid #A5ACB2";
						
						document.getElementById("hasil_autocomplete").style.width="250px";
					}
				}
				xmlhttp.open("GET", "autocomplete_assignee.php?q="+str_arr[str_arr.length -1], true);
				xmlhttp.send();
			}
		</script>
	
		<script>
			function sort_and_unique( my_array ) {
    my_array.sort();
    for ( var i = 1; i < my_array.length; i++ ) {
        if ( my_array[i] === my_array[ i - 1 ] ) {
                    my_array.splice( i--, 1 );
        }
    }
    return my_array;
};
		</script>
	
		<!--JS UNTUK AUTOCOMPLETE-->
		<script>
			function autocomplete_diklik(str)
			{
				var string_awal = document.getElementById("input_assignee").value;
				var str_arr = string_awal.split(", ");
				/*
				if(string_awal.indexOf(", ") != -1)
				{
					document.getElementById("input_assignee").value += str;
					document.getElementById("input_assignee").value += ", ";
				}
				else
				{
				document.getElementById("input_assignee").value = str + ", ";
					
				}*/
				str_arr[str_arr.length-1]=str;
				str_arr = sort_and_unique(str_arr);
				document.getElementById("input_assignee").value = str_arr.join(", ")+", ";
				document.getElementById("input_assignee").focus();
				document.getElementById("hasil_autocomplete").innerHTML="";
			}
		</script>
		
		<!--AJAX UNTUK MENAMPILKAN TASKS-->
		<script>
			var prev_selected = 0;
			function showTasks(str)
			{
				document.getElementById("task").innerHTML="";

				if(prev_selected == 0)
				{
					prev_selected = str;
				}
				document.getElementById("id"+prev_selected).style.border="none";
				
				if(window.XMLHttpRequest)
				{
					// untuk IE7, Firefox, Chrome, Opera, Safari
					xmlhttp = new XMLHttpRequest();
				}
				else
				{
					//untuk IE jadul
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				
				xmlhttp.onreadystatechange = function()
				{
					if (xmlhttp.readyState==4 && xmlhttp.status==200)
					{
					
						var elements = document.getElementsByClassName("tulcat");
						
						document.getElementById("id"+str).style.border="solid #FF0000";
						document.getElementById("task").innerHTML=xmlhttp.responseText;
						document.getElementById("link_buattask").href="buattask.php?idkategori="+str;
						document.getElementById("addtask").style.display = "block";
						document.getElementById("deltask").style.display = "block";
						
						prev_selected = str;
					}
				}
				xmlhttp.open("GET", "show_list_task.php?id_kategori="+str, true);
				xmlhttp.send();
			}
		</script>
	
		<!--JS UNTUK SHOW/HIDE TOMBOL DELETE KATEGORI-->
		<script>
			var isShown = false;
			function show_del_cat()
			{
				var elements = document.getElementsByClassName("tombol_hapus");
				if(isShown)
				{
					document.getElementById("delcat").style.backgroundColor="#ececec";
					for(var i = 0; i < elements.length; i++)
					{
						elements[i].style.display = "none";
					}
					isShown = false;
				}
				else{
					document.getElementById("delcat").style.backgroundColor="#361a2c";
					for(var i = 0; i < elements.length; i++)
					{
						elements[i].style.display = "block";
					}
					isShown = true;
				}
			}
		</script>
	
		<!--JS UNTUK SHOW/HIDE TOMBOL DELETE TASK-->
		<script>
			var isShown = false;
			function show_del_task()
			{
				var elements = document.getElementsByClassName("tombol_hapus_task");
				if(isShown)
				{
					document.getElementById("deltask").style.backgroundColor="#ececec";
					for(var i = 0; i < elements.length; i++)
					{
						elements[i].style.display = "none";
					}
					isShown = false;
				}
				else{
					document.getElementById("deltask").style.backgroundColor="#361a2c";
					for(var i = 0; i < elements.length; i++)
					{
						elements[i].style.display = "block";
					}
					isShown = true;
				}
			}
		</script>
	</head>
    <body>
         <div class="header">
			<div id="logo">
			    <a href="dashboard.php">
			    <img src="pict/logo.png">
			    </a>
			</div>
			<div id="border">
			    
			</div>
			<div id="dashboard">
			    <a href="dashboard.php">DASHBOARD</a>
			</div>
			<div id="profile">
			    <a href="profile.html">PROFILE</a>
			</div>
			<div id="search">
					    <section class="searchform cf">
						    <input class="searchbox" type="search" name="search" placeholder="Search.." required>
						    
					    </section>
					    <section class="searchbuttonbox cf">
						    <img class="searchbutton"src="pict/search button.jpg">
					    </section>
					    
				    </div>
			<div id="wellfaiz">
			    
			</div>
			<div id="logout">
			    <a href="index.html">LOGOUT</a>
			</div>
		  </div>
        <div class="main">
			<div id="menu_kiri">
				<a href="#">
					<div id="addcat" onclick="popup('popUpDiv')">
					</div>
				</a>
				<a href="buattask.html" id="link_buattask">
					<div id="addtask">
					</div>
				</a>
				<a href="#">
					<div id="delcat" onClick="show_del_cat()">
					</div>
				</a>
				<a href="#">
					<div id="deltask" onClick="show_del_task()">
					</div>
				</a>
			</div>
            <div id="category">
				<?php 
					printCategories($user_id); 
				?>
            </div>
            
            <div id="task">
				
			</div>
            
            <div id="blanket" style="display:none;"></div>
            <div id="popUpDiv" style="display: none;">
                <div id="newcategory">
                    <div id="closecat">
                        <a href="#" onclick="popup('popUpDiv')" >CLOSE</a>
                    </div>
                    <div id="cattitle">ADD CATEGORY</div>
                    <div id="elcategory">
                        <form action="buat_kategori.php" method="post">
							<input type="hidden" name="pembuat" value= <?php echo '"'.$user_id.'"'?> >
                            <label>category name</label>
                            <input name="catname" placeholder="category name" autocomplete="off">
                            <label>assignee</label>
                            <input name="catass" placeholder="assignee" id="input_assignee" autocomplete="off" onkeyup="showResult(this.value)">
							<div id="hasil_autocomplete"></div>
							</br></br>
                            <input class= "submitreg" name="submit" type="submit" value="Submit">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            Copyright © Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
        
    </body>
</html>