<?php
session_start();
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

// ================== Insert ke database ===================
if (!empty($_GET['q']))
mysql_query("INSERT INTO komentar (idtugas, username, isikomentar, waktu) VALUES ('$_GET[r]', '$_GET[s]', '$_GET[q]', NOW())");

// ================ Menampilkan ke viewtask =====================
// jika parameter sudah diset maka dilakukan pengisian pageum dengna parameter
if (empty($_GET['pagenum'])) {
	$pagenum = 1;
} else {
	$pagenum = $_GET['pagenum'];
}

$result = mysql_query("SELECT * FROM komentar JOIN user WHERE komentar.idtugas='$_GET[r]' AND komentar.username=user.username order by komentar.waktu DESC;");
$count = mysql_num_rows($result);

//Jumlah hasil tiap page
$page_rows = 10;

//Page terakhir
$last = ceil($count / $page_rows);

//Memastikan pagenum ada di range 1 sampai last
if ($pagenum < 1) {
	$pagenum = 1;
} elseif ($pagenum > $last) {
	$pagenum = $last;
}

//Melakukan set fungsi LIMIT untuk melakukan query selanjutnya
$max = 'LIMIT ' . ($pagenum - 1) * $page_rows . ',' . $page_rows;


$response= "Jumlah komentar : ". $count;
$response.= "<br>-----------------------------------------";

$result_p = mysql_query("SELECT * FROM komentar JOIN user WHERE komentar.idtugas='$_GET[r]' AND komentar.username=user.username order by komentar.waktu DESC $max");
if ($result_p != false) {
	while($row = mysql_fetch_array($result_p)) {
		$response.= "<img alt=\"\" src=\"".$row['avatar']."\"/> "."<b>".$row['username']."</b><br>";
		$response.= $row['isikomentar']."<br>";
		if ($_GET['s'] == $row['username'])
			$response.= "<a href=\"deletecomment.php?q=".$row['isikomentar']."&r=".$row['idtugas']."&s=".$row['username']."\">(Delete)</a>"."<br><br>";
		else
			$response.= "<br>";
		$response.= "[".$row['waktu']."]"."<br>";
		if ($count == 1) {

		} else {
			$response.= "-----------------------------------------</p>";
		}
		$count--;
	}
}

// Menunjukkan halaman pencarian
echo "<p style='margin-left: 0em;'>";
echo " ========= Page $pagenum of $last =========<br><br>";
// Jika pagenum bukan 1 maka ditampilkan link untuk ke First yaitu pagenum 1 dan previous
if ($pagenum == 1 || $pagenum == 0) {
	
} else {
	
	echo "<a href=\"changepagenum.php?pg=1&q=".$_GET['r']."\"> <<-First</a>";
	echo " ";
	$previous = $pagenum - 1;
	echo "<a href=\"changepagenum.php?pg=".$previous."&q=".$_GET['r']."\"> <-Previous</a>";
}

echo " -- ";


//Jika pagenum bukan last maka ditampilkan next dan last

if ($pagenum == $last) {
	
} else {
	$next = $pagenum + 1;
	echo "<a href=\"changepagenum.php?pg=".$next."&q=".$_GET['r']."\"> Next-></a>";
	echo " ";
	echo "<a href=\"changepagenum.php?pg=".$last."&q=".$_GET['r']."\"> Last->></a>";
}

echo " ===========================";
echo "</p>";

echo $response;
?>