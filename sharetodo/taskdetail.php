<?php
    session_start();
    $namaTask = $_GET['task'];
    //echo $namaTask;
?>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Detail Tugas</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"></link>
        <script LANGUAGE="Javascript" src="js/script.js"></script>
    </head>
    
    <body>
    	<div id="header">
	    <img id="logo" src="res/logo1.png" alt="to-do list"></img>
            <a id="dashboardLink" href="dashboard.php">dashboard</a>
            <input id="searchForm" type="text" name="keyword" onkeyup="showHint(this.value)" placeholder="search"></input>
            <select id="filter" name="filter">
                <!--<option selected>Select Filter ...</option>-->
                <option>All</option>
                <option>Username</option>
                <option>Judul Kategori</option>
                <option>Task</option>
            </select>
            <input id="submitForm" type="submit" name="search" value="search">
			<a href="#"><img id="profile" src="res/profileLogo.png" onclick="keProfil()";/></a>
            <a id="logout" href="#">Log Out</a>
	    <div class="suggest">Suggestion : <span id="textHint"></span></div>
        </div>
        
        <div id="spasi">
        </div>
    <?php
	//create connection
	$con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
    
	//check the connection
	if (mysqli_connect_errno($con)) {
	    echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
	}
		
		$curUser = $_SESSION['username'];
		//echo $curUser;
		
		//$sql = "SELECT * FROM task";
		//$sql = "SELECT * FROM task,attach, tasktoasignee, tagging, komentar";
		$sql = "SELECT * FROM task, attach, tasktoasignee, tagging, komentar WHERE task.namaTask = attach.namaTask AND task.namaTask = attach.namaTask AND task.namaTask = tasktoasignee.namaTask AND task.namaTask = tagging.namaTask AND task.namaTask = komentar.namaTask AND task.namaTask = '$namaTask'";
		$result = mysqli_query($con,$sql) /*or die(mysqli_error($con))*/;
		$taskArr = array();
		while($row = mysqli_fetch_array($result)){
		    if (!in_array($row['namaTask'],$taskArr)) {
			array_push($taskArr,$row['namaTask']);
			echo "<div id='taskdetailcontainer'>";
			    echo "<div class='taskElmtLeft'>";
				echo"<p><strong>".$row['namaTask']."</strong></p>";
			    echo "</div>";
			    echo "<div class='taskElmtRight'>";
			    echo "</div>";
			    echo "<div class='taskElmtLeft'>";
				echo "<p>Status Tugas :</p>";
			    echo "</div>";
			    echo"<div id ='statustask' class='taskElmtRight'>";
				echo "<p>".$row['status']."</p>";
			    echo"</div>";
			    echo "<div class='taskElmtLeft'>";
			    echo "</div>";
			    echo "<div class='taskElmtRight'>";
				$nama = $row['namaTask'];
				$idStatus = $row['status'];
				echo "<button class='ubahStatusTask' onclick=\"changeTaskStatus('$nama','$idStatus')\";>Ubah Status</button>";
			    echo "</div>";
			    echo "<div class='taskElmtLeft'>";
				echo "<p><em>Attachment : </em></p>";
			    echo "</div>";
			    echo"<div id = 'attachmentbox' class='taskElmtRight'>";
				    echo"<p>".$row['attachment']."</p>";
			    echo"</div>";
			    echo "<div class='taskElmtLeft'>";
				echo "<p>Deadline :</p>";
			    echo "</div>";
			    echo "<div class='taskElmtRight'>";
				echo"<p>".$row['deadline']."</p>";
			    echo "</div>";
			    echo "<div class='taskElmtLeft'>";
				echo "<p>Assignee :</p>";
			    echo "</div>";
			    echo "<div class='taskElmtRight'>";
				echo"<p>".$row['asigneeName']."</p>";
			    echo "</div>";
			    echo "<div class='taskElmtLeft'>";
				echo"<p>Komentar :</p>";
			    echo "</div>";
			    echo "<div class='taskElmtRight'>";
			    echo "</div>";
			    //echo"<div id = 'commentbox'>";
				    //echo"<p>".$row['avatar']."</p>";
			    echo "<div class='taskElmtLeft'>";
				echo"<p>".$row['timestamp']."</p>";
			    echo "</div>";
			    echo "<div class='taskElmtRight'>";
				echo"<p>".$row['isikomentar']."</p>";
			    echo "</div>";
			    echo "<div class='taskElmtLeft'>";
			    echo "</div>";
			    echo "<div class='taskElmtRight'>";
				echo"<button class='DeleteComment'>Delete</button>";
			    echo "</div>";
			    $tagTotal = "";
			    $taskPrev = "";
			    $isKeluar = false;
			    $resultTag = mysqli_query($con,$sql);
			    while ($rowTag = mysqli_fetch_array($resultTag)) {
				$isAda = strpos($tagTotal,$rowTag['tag']);
				if (($isAda == false)) {
				    $tagTotal .= " " . $rowTag['tag'] . " | ";
				}
			    }
			    $tagTotal = substr($tagTotal,0,strlen($tagTotal)-3);
			    echo "<div class='taskElmtLeft'>";
				echo "<p>Tag :</p>";
			    echo "</div>";
			    echo "<div class='taskElmtRight'>";
				echo "<p>".$tagTotal."</p>";
			    echo "</div>";
			echo"</div>";
		    }
		}
     ?>
    </body>
</html>