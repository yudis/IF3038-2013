<?php
	session_start();
?>

<html>
<script>

function setToDone(namaTugas){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById(namaTugas).innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET", "setDone.php?t="+namaTugas, true);
	xmlhttp.send();
}

function setToUndone(namaTugas){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById(namaTugas).innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET", "setToUndone.php?t="+namaTugas, true);
	xmlhttp.send();
}

</script>

<?php
	mysql_connect("localhost","root","") or die ("Cannot connect to MySQL");
	mysql_select_db("progin") or die ("Cannot connect to progin database");
		
	$username = $_SESSION['username'];
	
	function showTag($taskName){
		$tag = mysql_query("SELECT tag FROM tag WHERE namatugas='$taskName'");
		while($row = mysql_fetch_array($tag)){
			echo $row['tag'] . "; ";
		}
	}
	
	function cekAssigner($user,$taskname){
		$name = mysql_query("SELECT username FROM asigner WHERE namatugas='$taskname'");
		while($row = mysql_fetch_array($name)){
			if($row['username']==$user){
				echo "<button onclick=\"delTask('" . $taskname . "')\">Delete Task</button>";}
		}
	}	
	
	echo "<div id=\"alltask\">";
	$result = mysql_query("SELECT *  FROM tugas");
	while($row = mysql_fetch_array($result)){
			if($username == $row['username']){
				echo "<div id ='taskbox'>
						<div id='taskimg'>
							<img src='img/note1.png' alt='Logo'> </br>
						</div>
						<div id='taskdesc'>
							<div id='". $row['namatugas'] . "'>
							<a href='rinciantugas.html'>" . $row['namatugas'] . "</a> 
							<p> Deadline : " . $row['deadline'] ."</p>
							<p> Tag : " ; showTag($row['namatugas']) ; echo "</p>
							<p> Status : "; if ($row['status']==0) {echo "not done";} else {echo "done";} ;
							if($row['status']==0){echo "<br> <button onclick=\"setToDone('" . $row['namatugas'] . "')\">Done!</button>";} else {echo "<br> <button onclick=\"setToUndone('" . $row['namatugas'] . "')\">Set To Undone</button>";};
							cekAssigner($username,$row['namatugas']);

							echo "</div> </div>";
				}
			}
		echo "</div>";
?>
</html>