<?php
    session_start();
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	    $con = mysqli_connect("localhost","root","","distributedAgenda");
	    
	    //check the connection
	    if (mysqli_connect_errno($con)) {
		echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
	    }
		
		$curUser = $_SESSION['username'];
		
		$sql = "SELECT * FROM task t JOIN attach att ON att.namaTask = t.namaTask JOIN usertotask utt ON utt.username = t.creatorTaskName JOIN tasktoasignee tta ON tta.asigneeName = t.creatorTaskName JOIN tagging tgg ON tgg.namaTask = t.namaTask JOIN komentar kmn ON kmn.namaTask = t.namaTask JOIN user ON user.username = kmn.komentator";	   
		$result = mysqli_query($con,$sql) or die(mysqli_error($con));
		while($row = mysqli_fetch_array($result)){
        echo "<div id='taskdetailcontainer'>";
			echo"<p>".$row['namaTask']."</p>";
			echo"<p>Status Tugas</p>";
			echo"<div id ='statustask'>";
				echo"<p>".$row['namaTask']."</p>";
			echo"</div>";
			echo"<div id = 'buttonstatus'>";
				echo"<button class='ChangeTaskStatus'>Change</button>";
			echo"</div>";
			echo"<div id = 'attachmentbox'>";
				echo"<p>".$row['attachment']."</p>";
			echo"</div>";
			echo"<p>".$row['deadline']."</p>";
			echo"<p>".$row['asigneeName']."</p>";
			echo"<p>Komentar</p>";
			echo"<div id = 'commentbox'>";		
				echo"<p>Jumlah Komentar</p>";
				echo"<p>".$row['avatar']."</p>";
				echo"<p>".$row['timestamp']."</p>";
				echo"<p>".$row['isikomentar']."</p>";
				echo"<button class='DeleteComment'>Delete</button>";
				echo"<!-- input text komentar -->";
			echo"</div>";
			echo"<p>".$row['tag']."</p>";	
        echo"</div>";
		}
     ?>   
    </body>
</html>