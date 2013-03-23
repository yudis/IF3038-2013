<?php
//This is only displayed if they have submitted the form 
if (!empty($_POST['searching'])) {
	$searching = $_GET['searching'];
	$find = $_GET['find'];
	$field = $_GET['field'];
} else {
	$searching = $_GET['searching'];
	$find = $_GET['find'];
	$field = $_GET['field'];
}

if ($field == "username" || $field == "semua") {
	$con = mysql_connect("localhost:3306", "root", "");
	if (!$con) {
		die('Could not connect: ' . mysql_error());
	}

	mysql_select_db("progin_439_13510057", $con);

	if ($searching == "yes") {
		echo "<div id=\"hasilcari\"><h2>Hasil Pencarian Username</h2>";
		echo "<p style='margin-left: 1em;'><b>Anda mencari : </b> " . $find . "</p></div>";
		//If they did not enter a search term we give them an error 
		if ($find == "") {
			echo "<p>Tolong masukkan data yang ingin anda cari";
			exit;
		}


		// We preform a bit of filtering 
		$find = strtoupper($find);
		$find = strip_tags($find);
		$find = trim($find);

		$anymatches = 0;

		//Bila pagenum belum diset di parameter maka akan di set menjadi 1
		if (!isset($pagenum)) {
			$pagenum = 1;
		}
		// jika parameter sudah diset maka dilakukan pengisian pageum dengna parameter
		if (empty($_GET['pagenum'])) {
			
		} else {
			$pagenum = $_GET['pagenum'];
		}
		//Now we search for our search term, in the field the user specified 
		$data = mysql_query("SELECT * FROM user WHERE upper(username) LIKE'%$find%'");
		$rows = mysql_num_rows($data);

		//Jumlah hasil tiap page
		$page_rows = 10;

		//Page terakhir
		$last = ceil($rows / $page_rows);

		//Memastikan pagenum ada di range 1 sampai last
		if ($pagenum < 1) {
			$pagenum = 1;
		} elseif ($pagenum > $last) {
			$pagenum = $last;
		}

		//Melakukan set fungsi LIMIT untuk melakukan query selanjutnya
		
		$max = 'LIMIT ' . ($pagenum - 1) * $page_rows . ',' . $page_rows;

		//Melakukan Query dengna menambahkan fungsi limit
		if ($rows == 0)
		{
			
		}
		else
		{
			$data_p = mysql_query("SELECT * FROM user WHERE upper(username) LIKE'%$find%' $max") or die(mysql_error());
			session_start();
			while ($info = mysql_fetch_array($data_p)) {
			echo "<div id=\"isi1\">";
			
			if($_SESSION['id'] == $info['username']) {
				echo "<p style='margin-left: 1em;'> Username : <a href=\"profile.php\">". $info['username'] ."</a></p>";
			} else {
				echo "<p style='margin-left: 1em;'> Username : <a href=\"profilesearch.php?idsearch=". $info['username']."\">". $info['username'] ."</a></p>";
			}
			echo "<p style='margin-left: 3em;'> Nama Lengkap : " . $info['fullname'] . "</p>";
			echo "<p style='margin-left: 3em;'> ";
			echo "<img src=\"" . $info['avatar'] . "\" alt=\"\" / height=\"100\" width=\"100\">";
			echo "</p>";
			echo "</div>";
			}
		}

		//Menampilkan hasil query
		

		// Menunjukkan halaman pencarian
		echo "<div id=\"hasilcari2\"><p style='margin-left: 5em;'>";
		echo " --Page $pagenum of $last-- </p>";
		echo "<p style='margin-left: 5em;'>";
		// Jika pagenum bukan 1 maka ditampilkan link untuk ke First yaitu pagenum 1 dan previous
		if ($pagenum == 1 || $pagenum == 0) {
			
		} else {
			
			echo "<a href=\"#\" onclick = \"searchWords1(1); return false;\" > <<-First</a>";
			echo " ";
			$previous = $pagenum - 1;
			echo "<a href=\"#\" onclick = \"searchWords1(".$previous."); return false;\" > <-Previous</a>";
		}

		echo " ---- ";

		
		//Jika pagenum bukan last maka ditampilkan next dan last

		if ($pagenum == $last) {
			
		} else {
			$next = $pagenum + 1;
			echo "<a href=\"#\" onclick = \"searchWords1(".$next."); return false;\" > Next-></a>";
			echo " ";
			echo "<a href=\"#\" onclick = \"searchWords1(".$last."); return false;\" > Last->></a>";
		}
		echo "</p>";
		$anymatches = mysql_num_rows($data);
	}

	//This counts the number or results - and if there wasn't any it gives them a little message explaining that 
	echo "<p style='margin-left: 5em;'>";
	echo "Hasil pencarian : " . $anymatches;
	echo "</p></div>";
	mysql_close($con);
}
?>
