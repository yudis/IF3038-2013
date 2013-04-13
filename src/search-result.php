<!DOCTYPE html>
<?php
	session_start();
	$user = $_SESSION["login"];
	if ($user == ""){
		header("Location:index.php");
	}
?>
<html dir="ltr" lang="en-US">
    <head>
        <!--[if lt IE 9]>
        <script src="html5.js" type="text/javascript"></script>
        <![endif]-->
        <title>ToDo</title><script src="myscript.js"></script>
        <link rel="stylesheet" type="text/css" media="all" href="css.css" />
    </head>

    <body>
     <?php
	require_once("database.php");
	$con = connectDatabase();

	$resultavatar = mysqli_query($con,"SELECT * FROM user
			WHERE username='$user'");

	$rowavatar = mysqli_fetch_array($resultavatar);
?>
<header>	
			<div id="tes">
			<br></br>
			<a href="profil.php"><img id="avatar" src="<?php echo $rowavatar['avatar']?>"></a>
			<h3 id="username"><a href="profil.php"><?php echo "$user"?></a>
			<h1 id="logo"><a href="dashboard.php"><img src="images/logo2.png"/></a>
			<form name="formsearch" action="search-result.php" method="get">
			<input name="searchquery" size="30" type="text" maxlength="30">
			<select name="filtersearch">
			<option value="0" selected="selected">Semua</option>
			<option value="1">Username</option>
			<option value="2">Judul Kategori</option>
			<option value="3">Task</option></select><img src="images/search-icon.png" onclick='SearchDatabase();'>
			</form>
			<h3 id="logout"><a href="logout.php">Logout</a>
			</div>
	</header>
<div id="page" >
	<header id="branding">
		<hgroup>
			<h1 id="site-title">              <a href="dashboard.php"></a></h1>
			<h2 id="site-description">            </h2>
		</hgroup>

		<nav id="access" role="navigation">
		<ul class="menu">
			<li class="menu-item"><a href="dashboard.php">Dashboard</a></li>
			<li class="menu-item"><a href="profil.php">Profile</a></li>

                        <ul>
                            <li class="menu-item"><a href="index.html">Subpage 1</a></li>
                            <li class="menu-item"><a href="index.html">Subpage 2</a></li>
                            <li class="menu-item"><a href="index.html">Subpage 3</a></li>
                        </ul></li>
                    </ul>
                </nav>
            </header>

            <div align="center">
                <h1>Search Result</h1>
                <?php
                require_once("database.php");
                $con = connectDatabase();
                $search = $_GET["searchquery"];
                $filter = $_GET["filtersearch"];

                if ($filter == 1 || $filter==0) {
                    $result = mysqli_query($con, "SELECT * FROM user
			WHERE username LIKE '%$search%'");
                    echo "<hr>";
                    echo "<h4>User Search Result</h4>";
                    while ($row = mysqli_fetch_array($result)) {
                        echo '<a href=viewprofile.php?user='.$row['username'].">"."Username : " . $row['username']."</a>";
                        echo "<br>";
                        echo "Full Name : " . $row['fullname'];
                        echo "<br>";
                        echo '<img src="' . $row['avatar'] . '">';
                        echo "<br>";
                        echo "==========================<br>";
                    }
                }
                if ($filter == 2 || $filter==0) {
                    $result = mysqli_query($con, "SELECT * FROM kategori
			WHERE namaKategori LIKE '%$search%'");

                    echo "<hr>";
                    echo "<h4>Category Search Result</h4>";
                    while ($row = mysqli_fetch_array($result)) {
                        echo "Nama Kategori : " . $row['namaKategori'];
                        echo "<br>Creator : " . $row['creatorKategoriName'];
                        echo "<br><br>";
                        echo "<br>";
                    }
                }
                if ($filter == 3 || $filter==0) {
                    $result = mysqli_query($con, "SELECT * FROM task
			WHERE namaTask LIKE '%$search%'");

                    echo "<hr>";
                    echo "<h4>Task Search Result</h4>";
                    while ($row = mysqli_fetch_array($result)) {
                        echo '<a href="viewtask.php?nama='.$row['namaTask']. '">Nama Task : ' . $row['namaTask']."</a>";
                        echo "<br> Status : " . $row['status'];
                        echo '<br><a href="changetaskstatus.php?target=search&searchquery='.$search.'&filter='.$filter.'&task='.$row['namaTask'].'"><input type="button" value="Ubah Status">'."</a>";
                        echo "<br> Deadline : " . $row['deadline'];
                        echo "<br>";

                        $nmt = $row['namaTask'];
                        $result2 = mysqli_query($con, "SELECT * FROM tagging
			WHERE namaTask='$nmt'");
                        echo "Tag : ";
                        while ($row = mysqli_fetch_array($result2)) {
                            echo $row['tag']." , ";
                        }
                        echo "<br>==========================<br>";
                    }
                }
                ?>
            </div>

        </div>
    </body>
</html>