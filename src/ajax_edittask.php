<?php

	function TambahAssignee ($idtgs) {
	  $temp = split(", ",$_GET['assignee']);
	  foreach ($temp as $y) {
		if ($y!="") {
		  $sql = "SELECT idaccounts FROM accounts WHERE username = '".$y."'";
		  $result = mysql_query($sql);
		  $fetch = mysql_fetch_array($result);  
		  $sql = "INSERT
				  INTO accounts_has_tugas (accounts_idaccounts, tugas_idtugas, pembuat)
				  VALUES ('".$fetch["idaccounts"]."','".$idtgs."',false);";
		  $result = mysql_query($sql);
		}
	  }
	}
	function ClearAssignee($idtgs) {
	  $sql = "DELETE FROM accounts_has_tugas
			  WHERE tugas_idtugas = ".$idtgs.";";
	  $result = mysql_query($sql);
	}
	function TambahTag ($idtgs) {
	  $temp = split(",",$_GET['tag']);
	  $fetch = "";
	  foreach ($temp as $y) {
		if ($y!="") {
		  $sql = "SELECT idtag FROM tag WHERE nama = '".$y."'";
		  $result = mysql_query($sql);
		  $fetch = mysql_fetch_array($result);
		  // Kalau tag belum ada, bikin baru
		  if ($fetch["idtag"]=="") {
			$sql = "INSERT
					INTO tag (nama)
					VALUES ('".$y."');";
			$result = mysql_query($sql);
			$sql = "SELECT idtag FROM tag WHERE nama = '".$y."'";
			$result = mysql_query($sql);
			$fetch = mysql_fetch_array($result);
		  }
		  $sql = "INSERT
				  INTO tugas_has_tag (tag_idtag, tugas_idtugas)
				  VALUES ('".$fetch["idtag"]."','".$idtgs."');";
		  $result = mysql_query($sql);
		}
	  }
	}
	function ClearTag($idtgs) {
	  $sql = "DELETE FROM tugas_has_tag
			  WHERE tugas_idtugas = ".$idtgs.";";
	  $result = mysql_query($sql);
	}
	
	
	
	require_once("connectdb.php");
	require_once("php_class/viewtask.php");
	
	$id = $_GET['id'];
	$deadline = $_GET['deadline'];
	$assignee = $_GET['assignee'];
	$tag = $_GET['tag'];
	
	$query = "UPDATE tugas
			  SET deadline='".$deadline."'
			  WHERE idtugas='".$id."'";
	$result = mysql_query($query);
	
	ClearAssignee ($id);
	TambahAssignee ($id);
	ClearTag ($id);
	TambahTag ($id);
	
	// --- Tampilkan Hasil Perubahan
	
	$vt = new ViewTask($_GET['id']);
	$_COOKIE["lt_tugas"] = $vt->getTask();
	$_COOKIE["lt_attachment"] = $vt->getAttachment();
	$_COOKIE["lt_assignee"] = $vt->getAssignee();
	$_COOKIE["lt_tag"] = $vt->getTag();
	$_COOKIE["lt_komen"] = $vt->getKomen();
	$_COOKIE["lt_komentator"] = $vt->getKomentator();
	
	
	echo '
	  <label>DEADLINE</label>
	  <a id="deadline">
	  ';
		  $datetime = strtotime($_COOKIE["lt_tugas"]["deadline"]);
		  $mysqldate = date("d F Y", $datetime);
		  echo $mysqldate;
	echo  '
	  </a>
	  <br>
	  <br>
	  <label>ASSIGNEE</label>
	  <div id="asgdivwrapper">
	  ';
	foreach ($_COOKIE["lt_assignee"] as $x) {
	echo  '
		<a href ="#">
			<div class="asgdiv">
				<img class="asgava asgdivelemt" src="'. ($x['avatar']) .'"/>
				<a href ="#" class="asgdivelemt">'. $x['username']. '</a>
			</div>
		</a>
	';
	}
	echo  '
		  <br>
	  </div>
	  <label>TAG</label>
	  ';
	foreach ($_COOKIE["lt_tag"] as $x) {
	echo  '
	  <button class="btag" value="">'. $x["nama"]. '</button>
	  ';
	}
	echo  '
	  <br>
	';
		
	//while($row = mysql_fetch_array($result))
	//{
	//	echo '<div class="hasil_ac" onClick="autocomplete_diklik(\''.$row["username"].'\')">'.$row["username"].'</div>';
	//}
	
	mysql_close();
?>

