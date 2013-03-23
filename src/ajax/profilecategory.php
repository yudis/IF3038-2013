<?php

session_start();

$con = mysql_connect('localhost', 'root', 'admin');
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510015", $con);


// $sql="
	// SELECT status, categories.id as id_kategori, tugas.id as id_tugas, categories.nama as kn, tugas.nama as tn, tgl_deadline, tag
	// FROM categories INNER JOIN tugas ON tugas.id_kategori = categories.id LEFT JOIN tags ON tugas.id = tags.id_tugas INNER JOIN users ON users.username = tugas.pemilik
	// WHERE username = '".$_SESSION["user"]["username"]."'
	// ORDER BY status, id_kategori, id_tugas, tag
// ";

$sql="
	SELECT status, categories.id as id_kategori, tugas.id as id_tugas, categories.nama as kn, tugas.nama as tn, tgl_deadline, tag
	FROM categories INNER JOIN tugas ON tugas.id_kategori = categories.id LEFT JOIN tags ON tugas.id = tags.id_tugas INNER JOIN users ON users.username = tugas.pemilik
	WHERE username = '".$_SESSION["user"]["username"]."'
	ORDER BY status, id_kategori, id_tugas, tag
";

$result = mysql_query($sql);
echo '<h2> </h2>';
echo '<h1> Undone </h1>';

if ($row = mysql_fetch_array($result))
{
////////////////
	while(true)
	{
		$lastKN = $row["id_kategori"];

		echo '
							<h2>'.$row["kn"].'</h2>';
		do {						
			echo '					<div class="tugas">
									<div><a href="tugas.php?id='.$row["id_tugas"].'">'.$row["tn"].'</a></div>
										 <div>Submission: <strong>'.$row["tgl_deadline"].'</strong></div>
										 <div>
											 Tags: 
											 <ul class="tag">
			';								 
										 
			$lastTN = $row["id_tugas"];
			
			do {
				echo '								<li>'.$row["tag"].'</li>';
			} while(($row = mysql_fetch_array($result)) && ($lastTN == $row["id_tugas"]));
			echo '
											  </ul>
										 </div>
								</div>
			';
		} while(($lastKN == $row["id_kategori"]));
		if ($row["status"] == 1 || $row == NULL){
			break;
		}
	}
		echo '<h2> </h2>';
	echo '<h1> Done </h1>';
	
	if ($row != NULL){

		while(true)
		{
			$lastKN = $row["id_kategori"];

			echo '
								<h2>'.$row["kn"].'</h2>';
			do {						
				echo '					<div class="tugas">
										<div><a href="#">'.$row["tn"].'</a></div>
											 <div>Submission: <strong>'.$row["tgl_deadline"].'</strong></div>
											 <div>
												 Tags: 
												 <ul class="tag">
				';								 
											 
				$lastTN = $row["id_tugas"];
				do {
					echo '								<li>'.$row["tag"].'</li>';
				} while(($row = mysql_fetch_array($result)) && ($lastTN == $row["id_tugas"]));
				echo '
												  </ul>
											 </div>
									</div>
				';
			} while(($lastKN == $row["id_kategori"]));
			if ($row == NULL){
				break;
			}
		}
	} else {

	}
}

mysql_close($con);
?>