<?
	require_once("connectdb.php");
	
	$query = "SELECT idtugas, nama, deadline, status_selesai from tugas where kategori_idkategori = ".$_GET["id_kategori"];
	
	$result = mysql_query($query, $sql);
	
	while($row = mysql_fetch_array($result))
	{
		echo '<div class="listtask">';
		echo '<a href="#"><div class="tombol_hapus_task">X</div></a>';
		echo '<div class="nama_task"><a href="task.php?tujuan=lihat&id='.$row["idtugas"].'" class="link_tugas">'.$row["nama"].'</a></div>';
		echo '<div class="deadline">Deadline ';
		echo $row["deadline"];
		echo '</div>';
		echo '<div class="tags">Tags: ';
		
		$query_tag = "SELECT nama FROM tag, tugas_has_tag WHERE tugas_idtugas = ".$row["idtugas"];
		$result_tag = mysql_query($query_tag, $sql);
		while($row_tag = mysql_fetch_array($result_tag))
		{
			echo $row_tag["nama"].', ';
		}
		echo '</div>';
		echo '</div>';
	}
?>