<?
	require_once("connectdb.php");
	
	$query = "SELECT idtugas, nama, deadline, status_selesai FROM tugas, accounts_has_tugas WHERE kategori_idkategori = ".$_GET["id_kategori"]." AND tugas_idtugas = idtugas AND accounts_idaccounts = ".$_GET["idaccounts"];
	
	$result = mysql_query($query, $sql);
	
	while($row = mysql_fetch_array($result))
	{
		echo '<div class="listtask">';
		
		$query2 = "SELECT * FROM accounts_has_tugas WHERE accounts_idaccounts = ".$_GET["idaccounts"]." AND pembuat = 1";
		$result2 = mysql_query($query2, $sql);
		if(mysql_num_rows($result2) > 0)
		{
			echo '<a href="delete_task.php?idtugas='.$row["idtugas"].'"><div class="tombol_hapus_task">X</div></a>';
		}
		
		echo '<div class="nama_task"><a href="task.php?tujuan=lihat&id='.$row["idtugas"].'" class="link_tugas">'.$row["nama"].'</a></div>';
		echo '<div class="deadline">Deadline ';
		echo $row["deadline"];
		echo '</div>';
		echo '<div class="tags">Tags: ';
		
		$query_tag = "SELECT DISTINCT nama FROM tag, tugas_has_tag WHERE tugas_idtugas = ".$row["idtugas"];
		$result_tag = mysql_query($query_tag, $sql);
		while($row_tag = mysql_fetch_array($result_tag))
		{
			echo $row_tag["nama"].', ';
		}
		echo '</div>';
		
		if($row["status_selesai"] == 0)
		{
			echo '<div class="tombol_tugas_off" id="stat'.$row["idtugas"].'"onClick="ubahStatus(\'stat'.$row["idtugas"].'\')">Belum Selesai</div>';
		}
		else
		{
			echo '<div class="tombol_tugas_on" id="stat'.$row["idtugas"].'"onClick="ubahStatus(\'stat'.$row["idtugas"].'\')">Sudah Selesai</div>';
		}
		echo '</div>';
	}
?>