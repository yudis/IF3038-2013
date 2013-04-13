<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
						"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<link id="styleagnes" rel="stylesheet" type="text/css" href="css/style.css"></link>
	
	<title>kategori progin</title>
	
</head>
<body>
		<span id="background"></span>
		
		<div id="logo">
			<img src="images/image.jpg" alt="logogambar" width="100" height="75"/>
			<a href="dashboard.html"><img src="images/logo1.jpg" alt="logo" width="100" height="30"/></a>
		</div>
	
		<div class="header">
			<ul id="header">
				<li ><a href="dashboard.php">Dashboard</a></li>
				<li ><a href="halamanprofil.php">My Profile</a></li>
				<li ><a href="index.php">Logout</a></li>
				<li><input type="text" id="search" value="search.."/></li>
			</ul>
		</div>

	<div class="content">
		<div id="leftside">
			
			<div id="list kategori">
				<ul class="ullistkategori">
					<li class="lilistkategori"><a href="progin.php">Progin</a></li>
					<li class="lilistkategori"><a href="konten4.php">KAP</a></li>
				</ul>
			</div>
		</div>
		<div id="mainarea">
			<div id="tombolnewtask">
				<a href="buattugasbaru.html">New Task</a>
			</div>
			<div id="judulkategori">
				<p>PROGIN</p>
			</div>
			<div id="tabellisttask">
				<table id="idnamatask" class="namatask">
				<tr>
					<th>Nama Task</th>
					<th>Deadline</th>
					<th>Tag Multivalue</th>
				</tr>
			
				</table>
			</div>
			
		</div>
		
	
	</div>
	<script language="javascript">
		function addTask(){
			var elemenjudultask = document.getElementById('namatask').value;
			var elemendeadline = document.getElementById('deadline').value;
			var elementag = document.getElementById('multivalue').value;
			var tabTabel = document.getElementById('idnamatask');
			row = document.createElement("tr");
			
			cell1 = document.createElement("td");
			cell1.className="namatask";
			a1=document.createElement("a");
			a1.className="atabelkategori";
			a1.href="spesifikasi.html";
			cell1.appendChild(a1);
			
			cell2 = document.createElement("td");
			cell3 = document.createElement("td");
			
			
			cell2.className="namatask";
			cell3.className="namatask";			
			row.className="namatask";
			
			a1.appendChild(document.createTextNode(elemenjudultask));
			cell2.appendChild(document.createTextNode(elemendeadline));
			cell3.appendChild(document.createTextNode(elementag));
			
			tabTabel.appendChild(row);			
			
			row.appendChild(cell1);
			row.appendChild(cell2);
			row.appendChild(cell3);
			
		}
	</script>
	<div class="footer">
	</div>

</body>
</html>
