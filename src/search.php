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
	
	require_once("connectdb.php");
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
	mysql_close();
?>