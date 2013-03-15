<?php 
	$user_id = 00000000003;
	function printHeader()
	{
	
	}
	
	function printCategories($user_id)
	{
		require_once("connectdb.php");
		$query = "SELECT nama FROM kategori, asignee_has_kategori WHERE idkategori = kategori_idkategori AND accounts_idaccounts = ".$user_id;
		//mysql_query($query);
		$result = mysql_query($query, $sql);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<div id = "listcat"><div id="tulcat" onClick="showTasks()">'.$row["nama"].'</div></div>';
		}
		
		mysql_close($sql);
	}
	
	function printTasks()
	{
		$server = "localhost";
		$sql_id = "progin";
		$sql_pass = "progin";
		$sql_database = "progin";
		
		$sql = mysql_connect($server, $sql_id, $sql_pass);
		mysql_select_db($sql_database, $sql);
		$query = "select exists(select idaccounts from accounts where idaccounts = 1)";
		//mysql_query($query);
		$result = mysql_query($query, $sql);
		
		$row = mysql_fetch_row($result);
		echo $row[0];
		if($row[0])
		{
			echo 'true';
		}
		else
		{
			echo 'false';
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
        <script>
            var itotal=5;
            var ipartin=0;
            var ipartout=0;
            var itulis=0;
            
            function taskawal(itotal){
                for(var i=0;i<itotal;i++){
                var para=document.createElement("p");
                if(i===0){
                    var node=document.createTextNode("TUBES 1" + " " + (11+(i*4)) + "AGUSTUS2013"  +" KAP");   
                }else if(i===1){
                    var node=document.createTextNode("TUBES 2" + " " + ((i*6)-1) + "OKTOBER2013"  +" MSDI");   
                }else if(i===2){
                    var node=document.createTextNode("TUBES 3" + " " + ((i*6)-1) + "OKTOBER2013"  +" MSDI");   
                }else if(i===3){
                    var node=document.createTextNode("TUBES 4" + " " + ((i*6)-1) + "OKTOBER2013"  +" MSDI");   
                }else if(i===4){
                    var node=document.createTextNode("TUBES 5" + " " + ((i*4)-4) + "APRIL2013"  +" PROGIN" );   
                }else if(i===5){
                    var node=document.createTextNode("TUBES 6" + " " + ((i*4)-4) + "APRIL2013"  +" PROGIN" );   
                }
                para.appendChild(node);
                para.id="listtask";
                
                var element=document.getElementById("div1");
                element.appendChild(para);
                ipartout = itotal;
                }
            }
            function addTask(ipartin){
                
                for(var i=0;i<ipartin;i++){
                var para=document.createElement("p");
                if(ipartin === 1){
                    var node=document.createTextNode("TUBES " + (i+1) + " " + (11+(i*4)) + "AGUSTUS2013"  +" KAP");
                } else if(ipartin === 2){
                    var node=document.createTextNode("TUBES " + (i+5) + " " + (12+(i*4)) + "APRIL2013"  +" PROGIN" );
                } else if(ipartin === 3){    
                    var node=document.createTextNode("TUBES " + (i+2) + " " + (5+(i*6)) + "OKTOBER2013"  +" MSDI");
                }
                
                para.appendChild(node);
                para.id="listtask";
                
                var element=document.getElementById("div1");
                element.appendChild(para);
                }
                
                ipartout=ipartin;
                itulis=ipartin;
            }
            
            function removeTask(){
                for(var i=0;i<ipartout;i++){
                var parent=document.getElementById("div1");
                var child=document.getElementById("listtask");
                parent.removeChild(child);
                }
            }
            
            
            function showTask(){
                document.getElementById("addtask").style.visibility="visible";
            }
            
            function hideAddTask(){
                document.getElementById("addtask").style.visibility="hidden";
            }
            
        </script>
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
			function showTasks()
			{
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
						document.getElementById("task").innerHTML=xmlhttp.responseText;
					}
				}
				xmlhttp.open("GET", "autocomplete_assignee.php?q="+str_arr[str_arr.length -1], true);
				xmlhttp.send();
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
            <div id="addcat" onclick="popup('popUpDiv')">
            </div>
            <a href="buattask.html">
            <div id="addtask" style="visibility: hidden";>
            </div>
            </a>
            <div id="category">
				<?php 
					printCategories($user_id); 
				?>
            </div>
            
            <div id="task">
                <p id="listtask">
					assdas
				</p>
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
                            <input name="catname" placeholder="category name">
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