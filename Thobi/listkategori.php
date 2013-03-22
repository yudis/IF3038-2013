	<?php
		$username=$_GET["uname"];
		
		$con = mysql_connect('localhost', 'root', 'rootadmin');
		if (!$con)
		{
			die('Could not connect: ' . mysql_error());
		}
		
		mysql_select_db("progin_405_13510035", $con);
			
		
		$sql="SELECT category_name, category.category_id FROM category, category_incharge WHERE category.category_id = category_incharge.category_id AND people_incharge = '".$username."'";

		$result = mysql_query($sql);
		
		echo ("<ul id='Kategori' class='nav'>
							<li><a id='all' href='dashboard.php?uname=".$username."&cat=all' onclick='return RemoveKategoriFilter(this)'>All</a></li>");
		
		while ($row = mysql_fetch_array($result)) {
			echo ("<li><a id='".$row["category_id"]."' href='dashboard.php?uname=".$username."&cat=".$row["category_id"]."' onclick='return KategoriSelected(this)'>".$row["category_name"]."</a></li>");
		}
		
		echo ("</ul><ul class='nav'>
							<li><a href='#' onclick=\"popup('popUpDiv','blanket',300,600)\">Tambah Kategori...</a></li>
						</ul>");
		
		mysql_close($con);
		
	?>
