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
	
        
        <div id="spasi">
        	<div id="dashboardTitle">
            	<p>DASHBOARD</p>
            </div>
        </div>
        
        <div id="staticContainer">
            <div id="kategoriTitle">
            	<p>KATEGORI</p>
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
		
		echo "<div id='kategoriContent'>";
		    $result = mysqli_query($con,"SELECT namaKategori FROM kategori");
		    while ($row = mysqli_fetch_array($result)){
			$tempKategori = $row['namaKategori'];
			//echo $tempKategori;
			echo "<div id=" . $tempKategori . "menu class='kategoriElmt' onclick=\"showKategori('$tempKategori')\">";
				echo "<p>".$row['namaKategori']."</p>";
			echo "</div>";
		    }
		    echo "<div id='semuaTugas' class='kategoriElmt' onclick=\"showAllKategori()\";>";
			echo "<p>Semua Kategori Tugas</p>";
		    echo "</div>";
		    echo "<div id='addKategori' onclick=\"showPopUp()\";>";
			    echo "<button name='addKategori'>tambah kategori</button>";
		    echo "</div>";
		echo "</div>";
	    ?>
        </div>
        
        <div id="dynamicContainer">
        	<div id="dynamic_1" class="dynamicElmt">
		    <div class="rincianTitleDiv">
			    <h2 class="rincianText">Rincian Tugas</h2>
			<hr/>
		    </div>
		    <div id="dynamicSpace" class="dyn_elmt">
			<?php
			    $con = mysqli_connect("127.0.0.1","progin","progin","progin_405_13510027");
			    //check the connection
			    if (mysqli_connect_errno($con)) {
				echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
			    }else {
				$result = mysqli_query($con,"SELECT * FROM task");
				while ($row = mysqli_fetch_array($result)){
				    $nama = $row['namaTask'];
				    echo "<div id={$nama}space>";
					echo "<div id='taskTitle1' class='taskElmtLeft' onclick=\"toHalamanRincianTugas('$nama')\";>";
					    echo "<p></strong>".$nama."</strong></p>";
					echo "</div>";
					echo "<div class='taskElmtRight'>";
					echo "</div>";
					echo "<div class='taskElmtLeft'>";
					    echo "<p>Deadline :</p>";
					echo "</div>";
					echo "<div class='taskElmtRight'>";
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
		    </div>
		</div>
        </div>
        
        <div id="popUp">
	    <div id="newTugasTitle">
            	<h2>Form Tambah Kategori</h2>
            </div>
            
            <div id="newTugasForm">
                    <div class="Task">Nama Kategori</div> <div class="TaskContent">: <input id="kategoriName" type="text" name="namaTask"/></div>
                    <div class="Task">Pengguna Terpercaya</div> <div class="TaskContent">: <input id="userList" type="text"/></div>
			
		    <div id="submitNewKategori" onclick="closeKategoriForm('popUp');">
                    	<button>cancel</button>
                    </div>
                    <div id="submitNewKategori" onclick="closeKategoriForm('popUp');addKategori(kategoriName.value,userList.value);">
                    	<button>submit</button>
                    </div>
            </div>
        </div>
    </body>
</html>