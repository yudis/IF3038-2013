

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
</div>

<div>


<?php
    $con = mysqli_connect("localhost", "progin", "progin", "progin_405_13510027");
    $filter = $_GET["filter"];
    $keyword = $_GET["keyword"];
    //    $keyword = "romikacid";
    //    $filter = "category";
    
    //    if($filter == "all") {
    $task_result = mysqli_query($con, "SELECT * FROM task WHERE namaTask = '$keyword'");
    $user_result = mysqli_query($con, "SELECT * FROM user WHERE username = '$keyword'");
    $category_result = mysqli_query($con, "SELECT * FROM kategori WHERE namaKategori = '$keyword'");
    
    if($filter == "all" || $filter == "task") {
        echo "TASK";
        echo "<br>";
        while($task_row = mysqli_fetch_assoc($task_result)) {
            //echo "<a href='taskdetail.php?task=$task_row[\"namaTask\"]'>".$task_row["namaTask"]."</a>";
            $nama = $task_row["namaTask"];
            echo "<div id='taskTitle1' onclick=\"toHalamanRincianTugas('$nama')\";>";
            echo "</strong>".$task_row["namaTask"]."</strong>";
            echo "</div>";
            echo $task_row["deadline"]; echo "<br>";
            echo $task_row["status"]; echo "<br>";
        }
        echo "<br><br>";
    }
    
    
    if($filter == "all" || $filter == "user") {
        echo "USER"; echo "<br>";
        while($user_row = mysqli_fetch_assoc($user_result)) {
            echo $user_row["username"]; 
            echo "<br>";
            echo $user_row["email"]; echo "<br>";
            echo $user_row["fullname"]; echo "<br>";
            echo $user_row["tanggalLahir"]; echo "<br>";
        }
        echo "<br><br>";
    }
    
    if($filter == "all" || $filter == "category") {
        echo "CATEGORY"; echo "<br>";
        while($category_row = mysqli_fetch_assoc($category_result)) {
            echo $category_row["namaKategori"];
            echo "<br>";
            echo $category_row["creatorKategoriName"];
        }
    }
    
    //    }
    
    ?>
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