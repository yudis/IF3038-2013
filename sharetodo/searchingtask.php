<?php
    session_start();
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Dashboard</title>
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

 <div id="dynamicContainer">
        	<div id="dynamic_1" class="dynamicElmt">
		    <div class="rincianTitleDiv">
			    <h2 class="rincianText">Rincian Tugas</h2>
			<hr/>
		    </div>
		    <div id="dynamicSpace" class="dyn_elmt">
			<?php
			    $con = mysqli_connect("127.0.0.1","root","","distributedAgenda");
			    //check the connection
			    if (mysqli_connect_errno($con)) {
				echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
			    }else {
				$result = mysqli_query($con,"SELECT * FROM task JOIN tagging on task.namaTask = tagging.namaTask WHERE task.namaTask = 'Praktikum Kimia Dasar' OR tagging.tag = 'Kimia'");
				while ($row = mysqli_fetch_array($result)){
				    $nama = $row['namaTask'];
				    echo "<div id={$nama}space>";
					echo "<div id='taskTitle1' class='taskElmtLeft' onclick=toHalamanRincianTugas('taskTitle1');>";
					    echo "<p></strong>".$nama."</strong></p>";
					echo "</div>";
					echo "<div class='taskElmtRight'>";
					echo "</div>";
					echo "<div class='taskElmtLeft'>";
					    echo "<p>Deadline :</p>";
					echo "</div>";
					echo "/<div class='taskElmtRight'>";
					    echo "<p>".$row['deadline']."</p>";
					echo "</div>";
					echo "<div class='taskElmtLeft'>";
					    echo "<p>Tag :</p>";
					    echo "</div>";
					    echo "<div class='taskElmtRight'>";
					    
					    //mengambil semua tag untuk task tertentu
					    $tagQuery = "SELECT tag FROM tagging WHERE namaTask='".$row['namaTask']."'";
					    $resultTag = mysqli_query($con,$tagQuery);
					    $tagString = "";
					    while ($tag = mysqli_fetch_array($resultTag)){
						$tagString .= $tag['tag']." | ";
					    }
					    $refined = substr($tagString,0,strlen($tagString)-3);
					    echo "<p>".$refined."</p>";
					echo "</div>";
					echo "<div class='taskElmtLeft'>";
					    echo "<p>Status :</p>";
					echo "</div>";
					$idStatus = $nama."status";
					echo "<div class='taskElmtRight' id='".$idStatus."'>";
					    echo "<p>".$row['status']."</p>";
					echo "</div>";
					echo "<div class='taskElmtLeft'>";
					echo "</div>";
					echo "<div class='taskElmtRight'>";
					    $nama = $row['namaTask'];
					    //$nama = "apake sih";
					    echo "<button class='ubahStatusTask' onclick=\"changeTaskStatus('$nama','$idStatus')\";>Ubah Status</button>";
					echo "</div>";
					echo "<div class='taskElmtLeft'>";
					echo "</div>";
					echo "<div class='taskElmtRight'>";
					    echo "<button class='ubahStatusTask' onclick=\"deleteTask('$nama')\";>Hapus Tugas</button>";
					echo "</div>";
				    echo "</div>";
				}
			    }
			?>
	</body>
</html>	