<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
						"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">

<?php
	session_start();
?>

<head>
	
	<title>dashboard</title>	
	<script type="text/javascript" src="validator.js"></script>
	<script type="text/javascript" src="datetimepicker_css.js"></script>
	<script type="text/javascript" src="showtask.js"></script>
	<script type="text/javascript">
		function register(){
				alert("Registration Success!");
				window.location = "dashboard.php";
		}
	</script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>


	<div class="content">
		<div id="leftside">
                    <div id="newkategoritombol">
                        <a id="font" href="#join_form" >Tambah kategori</a>
                    </div>
                    
                    <div id="listkategori">
                        <ul id="listkategory" class="ullistkategori">
                        <li class="lilistkategori" ><a href="progin.html">PROGIN</a></li>
                        </ul>
                    </div>
                </div>
		
		<div id="mainarea">
			<table class="namatask" border="1" align="center" bgcolor="#FFFFFF">
			<tr>
				<th>Nama Task</th>
				<th>Deadline</th>
				<th>Tag Multivalue</th>
			</tr>
			<tr>
				<td><a href="spesifikasi2.php">Tubes Progin I</a></td>
				<td>23 Februari 2013</td>
				<td>html,css</td>
			</tr>
			<tr class="alt">
				<td><a href="spesifikasi2.php">Tubes IMK Flash</a></td>
				<td>23 Februari 2013</td>
				<td>ngeselin</td>
			</tr>
			</table>
		</div>
		
            
        </div>

	<script language="javascript">
		function addElement()
		{
			var element = document.getElementById('namakategori').value;
			if(element=="")
			{
				document.getElementById('listkategori').style.display="block";
				document.getElementById('listkategori').innerHTML= "Error! Insert a description for the element";
			}	
			else
			{
				var container = document.getElementById('listkategory');
				var new_element = document.createElement("li");
				new_element.className="lilistkategori";
				a1= document.createElement("a");
			
				a1.href="progin.html";
				new_element.appendChild(a1);
				
				
				a1.innerHTML = element;
				
				container.appendChild(new_element);
				container.insertBefore(new_element,container.firstChild);
			
			}
		}
	</script>
	
	<div class="footer">
	</div>
        
</body>

</html>