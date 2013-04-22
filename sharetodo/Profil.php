<?php
    session_start();
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Halaman Profil</title>
        <link rel="stylesheet" type="text/css" href="css/style.css"></link>
        <script LANGUAGE="Javascript" src="js/script.js"></script>
    </head>
    
    <body>
	<form action="searching.php" method="get">
    	<div id="header">
	    <img id="logo" src="res/logo1.png" alt="to-do list"></img>
            <a id="dashboardLink" href="dashboard.php">dashboard</a>
            <input id="searchForm" type="text" name="keyword" onkeyup="showHint(this.value)" placeholder="search"></input>
            <select id="filter" name="filter">
                <!--<option selected>Select Filter ...</option>-->
                <option>all</option>
                <option>user</option>
                <option>category</option>
                <option>task</option>
            </select>
            <input id="submitForm" type="submit" name="search" value="search" onclick="toSearchResult(searchForm.value,filter.value)">
	    <a href="#"><img id="profile" src="res/profileLogo.png" onclick="keProfil()";/></a>
	    <a id="logout" href="logout.php">Log Out</a>
	    <div class="suggest">Suggestion : <span id="textHint"></span></div>
        </div>
	</form>
        
        <div id="spasi">
        </div>
        
        <div id="photoSpace">
        	<div id="userPhoto">
            	<img id="user" src="server/<? echo $_SESSION['username'] ?>.png" alt="userPhoto"/>
		<div id="photoUploader">
		    <label><em>Upload new Avatar : </em></label>
		    <form method="post" action="php/UploadFile.php" enctype="multipart/form-data" name="uploadImage">
			<input id="fileUpload" type="file" name="image"></input>
			<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo MAX_FILE_SIZE; ?>"/>
			<input type="submit" value="Upload new Avatar"/>
		    </form>
		</div>
            </div>
        </div>
        
	<?php
	    //create connection
	    $con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
	    
	    //check the connection
	    if (mysqli_connect_errno($con)) {
		echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
	    }
	    
	    $curUser = "smanurung"; //hanya dummy
	    if (isset($_SESSION["username"])) { //hanya untuk memastikan
		$curUser = $_SESSION["username"];
	    } else {
		header("Location:index.php"); //mengembalikan ke halaman utama untuk user yang belum login
	    }
	    
	    $result = mysqli_query($con,"SELECT * FROM user WHERE (username='".$curUser."')");
	    while($row = mysqli_fetch_array($result)){
		//echo $row1['username'] . "<br/>";
		echo "<div id='userData'>";
		    echo "<h2 id='biodataTitle'>BIODATA</h2>" . "<hr/>";
		    echo "<div id='biodataContent'>";
			echo "<div class='bioLeft'>";
			    echo "<p>Nama Lengkap :</p>";
			echo "</div>";
			echo "<div id='userFullName' class='bioRight'>";
			    echo "<p>" . $row['fullname'] . "</p>";
			echo "</div>";
			echo "<div class='bioLeft'>";
			    echo "<p>Username :</p>";
			echo "</div>";
			echo "<div class='bioRight'>";
			    echo "<p><em>" . $row['username'] . "</em></p>";
			echo "</div>";
			echo "<div class='bioLeft'>";
			    echo "<p>Tanggal Lahir :</p>";
			echo "</div>";
			echo "<div id='userBirthdate' class='bioRight'>";
			    echo "<p>" . $row['tanggalLahir'] . "</p>";
			echo "</div>";
			echo "<div class='bioLeft'>";
			    echo "<p>Email :</p>";
			echo "</div>";
			echo "<div class='bioRight'>";
			    echo "<p>" . $row['email'] . "</p>";
			echo "</div>";
			echo "<div class='bioLeft'>";
			    echo "<p></p>";
			echo "</div>";
			echo "<div class='bioRight'>";
			    echo "<button id='editProfileBtn' onclick='showEditForm();'>Edit</button>";
			echo "</div>";
		    echo "</div>";
		echo "</div>";
	    }
	?>
	    
	    <!--FORM EDIT SECTION-->
	    <div id=editForm>
		<form method="POST" enctype="multipart/form-data" name="uploadImage">
		    <div class="bioLeft">
			<p>new Full Name :</p>
		    </div>
		    <div class="bioRight">
			<input id="newFullName" type=text name="newFullName"></input>
		    </div>
		    <div class="bioLeft">
			<p>new Birthdate :</p>
		    </div>
		    <div class="bioRight">
			<input id="newBirthdate" type=date name="newBirthdate"></input>
		    </div>
		    <div class="bioLeft">
			<p>new Password :</p>
		    </div>
		    <div class="bioRight">
			<input id="newPassword" type="password" name="newPassword"></input>
		    </div>
		    <div class="bioLeft">
			<p>Confirm new Password :</p>
		    </div>
		    <div class="bioRight">
			<input id="newPasswordAgain" type="password" name="newPasswordAgain"></input>
		    </div>
		    <div class="bioLeft">
			<p></p>
		    </div>
		    <div class="bioRight">
			<input class="submitBtn" type="button" value="Submit Form" name='upload' onclick="hideEditForm(); updateProfile(newFullName.value, newBirthdate.value, newPassword.value, newPasswordAgain.value, fileUpload.value);"></input>
		    </div>
		</form>
	    </div>
	    
	    <?php
		//create connection
		$con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
		
		//check the connection
		if (mysqli_connect_errno($con)) {
		    echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
		}
		
		$curUser = $_SESSION['username']; //mengambil username yang sedang aktif
		$sql = "SELECT task.namaTask, deadline FROM usertotask, task WHERE usertotask.username='". $curUser . "' AND usertotask.namaTask = task.namaTask";
		
		echo "<h2 id='taskTitle'>TASKS</h2>";
		echo "<hr/>";
		$result = mysqli_query($con,$sql);
		while($row = mysqli_fetch_array($result)){
		    //echo $row['namaTask'] . " | " . $row['deadline'];
		    echo "<div id='taskContent'>";
			echo "<div class='bioLeft'>";
			    echo "<p>Nama</p>";
			echo "</div>";
			echo "<div class='bioRight'>";
			    echo "<p>Deadline</p>";
			echo "</div>";
	    		
			echo "<div class='tableElmtLeft'>";
			    echo "<p>".$row['namaTask']."</p>";
			echo "</div>";
			echo "<div class='tableElmtRight'>";
			    echo "<p>".$row['deadline']."</p>";
			echo "</div>";
		    echo "</div>";
		}	    
	    ?>
            
	    <?php
		//create connection
		$con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
		
		//check the connection
		if (mysqli_connect_errno($con)) {
		    echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
		}
		
		$curUser = $_SESSION['username']; //mengambil username yang sedang aktif
		//query database sementara
		$sql = "SELECT task.namaTask, status FROM usertotask, task WHERE usertotask.username='". $curUser . "' AND usertotask.namaTask = task.namaTask";
		
		echo "<h2 id='doneTaskTitle'>DONE TASKS</h2><hr/>";
		    echo "<div class='bioLeft'>";
			echo "<p>Nama</p>";
		    echo "</div>";
		    echo "<div class='bioRight'>";
			echo "<p>Tag</p>";
		    echo "</div>";
		    
		    $result = mysqli_query($con,$sql);
		    while($row = mysqli_fetch_array($result)){
			
			//mengambil semua tag untuk task tertentu
			$tagQuery = "SELECT tag FROM tagging WHERE namaTask='".$row['namaTask']."'";
			$resultTag = mysqli_query($con,$tagQuery);
			$tagString = "";
			while ($tag = mysqli_fetch_array($resultTag)){
			    $tagString .= $tag['tag']." | ";
			}
			$tagString = substr($tagString,0,strlen($tagString)-3);
			
			//menampilkan task beserta tag nya yang terkait
			echo "<div class='tableElmtLeft'>";
			    echo "<p>".$row['namaTask']."</p>";
			echo "</div>";
			echo "<div class='tableElmtRight'>";
			    echo "<p>".$tagString."</p>";
			echo "</div>";
		    }
		echo "</div>";
	    ?>
        </div>
    </body>
</html>