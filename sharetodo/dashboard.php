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
		$con = mysqli_connect("127.0.0.1","root","root","distributedAgenda");
		
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
		    echo "<div id='static_1' class='kategoriElmt' onclick=showKategori()>";
			    echo "<p>".$row['namaKategori']."</p>";
		    echo "</div>";
		    echo "<div id='semuaTugas' class='kategoriElmt' onclick=active_semuaTugas();>";
			    echo "<p>Semua Kategori Tugas</p>";
		    echo "</div>";
		}
		echo "</div>";
	    ?>
	    
            <div id="kategoriContent">
            	<!--<div id="static_1" class="kategoriElmt" onclick="active_1()";>-->
            	<!--<div id="static_1" class="kategoriElmt" onclick="changeKategori('static_1');">
                	<p>Pemrograman Internet</p>
                </div>-->
                <div id="static_2" class="kategoriElmt" onclick="active_2()";>
                	<p>Sistem Terdistribusi</p>
                </div>
                <div id="static_3" class="kategoriElmt" onclick="active_3()";>
                	<p>Branding</p>
                </div>
                
                <div id="semuaTugas" class="kategoriElmt" onclick="active_semuaTugas()";>
                	<p>Semua Kategori Tugas</p>
                </div>
                
                <div id="addKategori" onclick="showPopUp()";>
                	<button name="addKategori">tambah kategori</button>
                </div>
            </div>
        </div>
        
        <div id="dynamicContainer">
        	<div id="dynamic_1" class="dynamicElmt">
		    <div class="rincianTitleDiv">
			    <h2 class="rincianText">Rincian Tugas</h2>
			<hr/>
		    </div>
		    <div id="dynamicSpace" class="dyn_elmt">
			<!--<div id="taskTitle1" class="taskElmtLeft" onclick="toHalamanRincianTugas('taskTitle1');">
			    <p><strong>Tugas Besar I</strong></p>
			</div>
			<div class="taskElmtRight">
			</div>
			<div class="taskElmtLeft">
			    <p>Deadline :</p>
			</div>
			<div class="taskElmtRight">
			    <p>2013/03/23</p>
			</div>
			<div class="taskElmtLeft">
			    <p>Tag :</p>
			</div>
			<div class="taskElmtRight">
			    <p>HTML5 | CSS3 | PHP | MySQL</p>
			</div>
			<div class="taskElmtLeft">
			    <p>Status :</p>
			</div>
			<div class="taskElmtRight" id=taskStatus1>
			    <p>belum selesai</p>
			</div>
			<div class="taskElmtLeft">
			</div>
			<div class="taskElmtRight">
			    <button class="ubahStatusTask" onclick="changeTaskStatus('taskStatus1');">Ubah Status</button>
			</div>
			<div class="taskElmtLeft">
			</div>
			<div class="taskElmtRight">
			    <button class="ubahStatusTask">Hapus Tugas</button>
			</div>-->
		    </div>
		    <button class="addTask" onclick="toHalamanPembuatanTugas();">tambah tugas</button>
		    <button class="addTask">hapus kategori</button>
		</div>
            <div id="dynamic_2" class="dynamicElmt">
            	<div class="rincianTitleDiv">
                	<h2 class="rincianText">Rincian Tugas</h2>
                    <hr/>
                    
                    <div class="dyn_elmt">
                    </div>
		    <button class="addTask" onclick="toHalamanPembuatanTugas();">tambah tugas</button>
		    <button class="addTask">hapus kategori</button>
                </div>
            </div>
            <div id="dynamic_3" class="dynamicElmt">
            	<div class="rincianTitleDiv">
                	<h2 class="rincianText">Rincian Tugas</h2>
                    <hr/>
                    
                    <div class="dyn_elmt">
                    </div>
		    <button class="addTask" onclick="toHalamanPembuatanTugas();">tambah tugas</button>
		    <button class="addTask">hapus kategori</button>
                </div>
            </div>
            
            <div id="dynamic_semuaTugas" class="dynamicElmt">
            	<div class="rincianTitleDivSemua">
                	<h2 class="rincianText">Semua Tugas</h2>
                    <hr/>
                    
                    <div class="dyn_elmt">
                    </div>
                    
                    <div class="dyn_elmt">
                    </div>
                    
                    <div class="dyn_elmt">
                    </div>
                    
                </div>
            </div>
        </div>
        
        <div id="popUp">
	    <div id="newTugasTitle">
            	<h2>Form Tambah Kategori</h2>
            </div>
            
            <div id="newTugasForm">
                <form>
                    <div class="Task">Nama Task</div> <div class="TaskContent">: <input type="text" name="namaTask"/></div>
                    <div class="Task">Tag</div> <div class="TaskContent">: <input type="text" /></div>
                    <div class="Task">Pengguna Terpercaya</div> <div class="TaskContent">: <input type="text"/></div>
			
                    <div id="submitNewKategori" onclick="closeKategoriForm()";>
                    	<button>submit</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>