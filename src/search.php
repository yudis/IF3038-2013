<?php
	function suggest_username($input, $limit1, $limit2)
	{
		$query = "SELECT * FROM accounts WHERE username like '%".$input."%' LIMIT ".$limit1.", ".$limit2;
		$result = mysql_query($query);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<a href="#">';				//BELUM DIKASIH LINK YANG BENER
			echo '<div class="hasil_suggest">';
			echo $row["username"];
			echo '</div>';
			echo '</a>';
		}
	}
	
	function suggest_kategori($input, $limit1, $limit2)
	{
		$query = "SELECT * FROM kategori WHERE nama like '%".$input."%' LIMIT ".$limit1.", ".$limit2;
		$result = mysql_query($query);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<a href="#">';				//BELUM DIKASIH LINK YANG BENER
			echo '<div class="hasil_suggest">';
			echo $row["nama"];
			echo '</div>';
			echo '</a>';
		}
	}
	
	function suggest_task($input, $limit1, $limit2)
	{
		$query = "SELECT DISTINCT idtugas, tugas.nama FROM tag, tugas, tugas_has_tag WHERE (tugas_idtugas = idtugas AND tag.nama like '%".$input."%' AND tag_idtag = idtag) OR (tugas.nama LIKE '%".$input."%') LIMIT ".$limit1.", ".$limit2;
		$result = mysql_query($query);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<a href="#">';				//BELUM DIKASIH LINK YANG BENER
			echo '<div class="hasil_suggest">';
			echo $row["nama"];
			echo '</div>';
			echo '</a>';
		}
	}
	
	function hasil_username($input, $limit1, $limit2)
	{
		$query = "SELECT * FROM accounts WHERE username like '%".$input."%' LIMIT ".$limit1.", ".$limit2;
		$result = mysql_query($query);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<a href="#">';				//BELUM DIKASIH LINK YANG BENER
			echo '<div class="hasil_username">';
			echo '<div class="user_ava">';
			echo '<img src="'.$row["avatar"].'"/>';
			echo '</div>';
			echo '<div class="username">';
			echo $row["username"];
			echo '</div>';
			echo '<div class="fullname">';
			echo $row["nama_lengkap"];
			echo '</div>';
			echo '</div>';
			echo '</a>';
		}
	}
	
	function hasil_kategori($input, $limit1, $limit2)
	{
		$query = "SELECT * FROM kategori WHERE nama like '%".$input."%' LIMIT ".$limit1.", ".$limit2;
		$result = mysql_query($query);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<a href="#">';				//BELUM DIKASIH LINK YANG BENER
			echo '<div class="hasil_kategori">';
			echo $row["nama"];
			echo '</div>';
			echo '</a>';
		}
	}
	
	function hasil_task($input, $limit1, $limit2)
	{
		$query = "SELECT DISTINCT idtugas, tugas.nama, deadline, status_selesai FROM tag, tugas, tugas_has_tag WHERE (tugas_idtugas = idtugas AND tag.nama like '%".$input."%' AND tag_idtag = idtag) OR (tugas.nama LIKE '%".$input."%') LIMIT ".$limit1.", ".$limit2;
		$result = mysql_query($query);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<a href="#">';				//BELUM DIKASIH LINK YANG BENER
			echo '<div class="hasil_task">';
			
			echo '<div class="nama_task">';
			echo $row["nama"];
			echo '</div>';
			
			echo '<div class="deadline">';
			echo $row["deadline"];
			echo '</div>';
			
			echo '<div class="tags">';
			echo 'Tags: ';
			$query_tag = "SELECT DISTINCT nama FROM tag, tugas_has_tag WHERE tugas_idtugas = ".$row["idtugas"];
			$result_tag = mysql_query($query_tag);
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
			echo '</a>';
		}
	}
	
	require_once("connectdb.php");
	
	
	if($_GET["show"] == 1)
	{
		if($_GET["jenis"] == "username")
		{
			echo '<div id="kolom_username">';
			hasil_username($_GET["q"], 0, 10);
			echo '</div>';
		}
		else if($_GET["jenis"] == "kategori")
		{
			echo '<div id="kolom_kategori">';
			hasil_kategori($_GET["q"], 0, 10);
			echo '</div>';
		}
		else if($_GET["jenis"] == "task")
		{
			echo '<div id="kolom_task">';
			hasil_task($_GET["q"], 0, 10);
			echo '</div>';
		}
		else if($_GET["jenis"] == "semua")
		{
			echo '<div id="kolom_kategori">';
			echo '<div class="judul_search">Kategori</div>';
			hasil_kategori($_GET["q"], 0, 10);
			echo '</div>';
			
			echo '<div id="kolom_task">';
			echo '<div class="judul_search">Tasks</div>';
			hasil_task($_GET["q"], 0, 10);
			echo '</div>';
			
			echo '<div id="kolom_username">';
			echo '<div class="judul_search">User</div>';
			hasil_username($_GET["q"], 0, 10);
			echo '</div>';
		}
	}
	else
	{
		if($_GET["jenis"] == "username")
		{
			suggest_username($_GET["q"], 0, 10);
		}
		else if($_GET["jenis"] == "kategori")
		{
			suggest_kategori($_GET["q"], 0, 10);
		}
		else if($_GET["jenis"] == "task")
		{
			suggest_task($_GET["q"], 0, 10);
		}
		else if($_GET["jenis"] == "semua")
		{
			echo '<fieldset>';
			echo '<legend>Username</legend>';
			suggest_username($_GET["q"], 0, 10);
			echo '</fieldset>';
			
			echo '<fieldset>';
			echo '<legend>Kategori</legend>';
			suggest_kategori($_GET["q"], 0, 10);
			echo '</fieldset>';
			
			echo '<fieldset>';
			echo '<legend>Task</legend>';
			suggest_task($_GET["q"], 0, 10);
			echo '</fieldset>';
		}
	}
	
	mysql_close();
?>