<?php
		session_start();
		$username = $_SESSION['username'];
		$namatugas = $_GET['t'];
		
		mysql_connect("localhost","root","") or die ("Cannot connect to MySQL");
		mysql_select_db("progin") or die ("Cannot connect to progin database");
		
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
		
		mysql_query("UPDATE tugas SET status=0 where namatugas='$namatugas'");
		$result = mysql_query("SELECT *  FROM tugas where username='$username' AND namatugas='$namatugas'");
		$resultTag - mysql_query("SELECT * FROM tag where username='$username'");
		while($row = mysql_fetch_array($result))  {
				echo "<a href='rinciantugas.html'>" . $row['namatugas'] . "</a> 
						<p> Deadline : " . $row['deadline'] ."</p>
						<p> Tag : " ; showTag($row['namatugas']) ; echo "</p>
						<p> Status : "; if ($row['status']==0) {echo "not done";} else {echo "done";} ;
						if($row['status']==0){echo "<br> <button onclick=\"setToDone('" . $row['namatugas'] . "')\">Done!</button>";} else {echo "<br> <button onclick=\"setToUndone('" . $row['namatugas'] . "')\">Set To Undone</button>";};
				cekAssigner($username,$row['namatugas']);

		}
?>