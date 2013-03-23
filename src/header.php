<!--AJAX UNTUK SEARCH-->
<script>
	function autocomplete_search(jenis, str)
	{
		if(str.length == 0)
		{
			document.getElementById("hasil_ac_search").innerHTML="";
			return;
		}
		
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
				document.getElementById("hasil_ac_search").innerHTML=xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", "search.php?jenis="+jenis+"&q="+str+"&show=0&more=10", true);
		xmlhttp.send();
	}
</script>


<div class="header">
  <div id="logo">
	  <a hreg="dashboard.php">
	  <img src="pict/logo.png">
	  </a>
  </div>
  <div id="border">
	  
  </div>
  <div id="dashboard">
	  <a href="dashboard.php">DASHBOARD</a>
  </div>
  <div id="profile">
	  <a href="profile.php">PROFILE</a>
  </div>
  <div id="search">
	<form name="cari" method="get" action="search_result.php">
	<section id="searchdropdown">
		<select name="jenis" id="opsisearch">
			<option value="semua">Semua</option>
			<option value="username">Username</option>
			<option value="kategori">Kategori</option>
			<option value="task">Task</option>
		</select>
	</section>
	<section class="searchform cf">
		<input class="searchbox" type="search" name="q" placeholder="Search.." autocomplete="off" required onKeyUp="autocomplete_search(document.getElementById('opsisearch').value, this.value)">
		<div id="hasil_ac_search"></div>
		<input type="hidden" name="more" value="10">
	</section>
	<section class="searchbuttonbox cf">
		<button type="submit" id="tombol_search">
			<img class="searchbutton"src="pict/search button.jpg">
		</button>
	</section>	
	</form>
  </div>  
  <div id="wellfaiz">
	Welcome <?echo $_SESSION['username'] ?>
  </div>
  <div id="logout">
	  <a href="logout.php" onClick="localStorage.clear()">LOGOUT</a>
  </div>
</div>