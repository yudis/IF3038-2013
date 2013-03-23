<?php
	$user_id = null;
	
	session_start();
	if (ISSET($_SESSION['username']))
	{
		//header("location: dashboard.php");
		$user_id = get_uid($_SESSION['username']);
	}
	else
	{	
		echo $_SESSION['username'];
		session_unset();
		session_destroy();
		header("location: index.php");
	}
	
	function get_uid($uname)
	{
		require_once("connectdb.php");
		$query = "SELECT idaccounts FROM accounts WHERE username = '".$uname."'";
		$result = mysql_query($query, $sql);
		
		while($row = mysql_fetch_array($result)){
			$uid = $row["idaccounts"];
		}
		return $uid;
		mysql_close($sql);
	}
	
	function printCategories($user_id)
	{
		require_once("connectdb.php");
		$query = "SELECT nama, idkategori FROM kategori, asignee_has_kategori WHERE idkategori = kategori_idkategori AND accounts_idaccounts = ".$user_id;
		//mysql_query($query);
		$result = mysql_query($query);
		
		while($row = mysql_fetch_array($result))
		{
			echo '<div class="tulcat" id ="id'.$row["idkategori"].'" onClick="showTasks('.$user_id.', '.$row["idkategori"].')">';
			$query_check_pembuat = "SELECT * FROM kategori, accounts WHERE pembuat = ".$user_id." AND idkategori = ".$row["idkategori"];
			$result_check_pembuat = mysql_query($query_check_pembuat);
			if(mysql_num_rows($result_check_pembuat) >0 )
			{
				echo '<a href="delete_kategori.php?idkategori='.$row["idkategori"].'"><div class="tombol_hapus">X</div></a>';
			}
			echo $row["nama"];
			echo '</div>';
		}

	}
?>

<html>
    <head>
        <title> Next | Dashboard </title>
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/dash.css">
        <script src="js/popup.js"></script>
		<script src="js/dash.js"></script>
	</head>
    <body>
        <?php require_once("header.php"); ?>
        <div class="main">
			<div id="menu_kiri">
				<a href="#">
					<div id="addcat" onclick="popup('popUpDiv')">
					</div>
				</a>
				<a href="buattask.html" id="link_buattask">
					<div id="addtask">
					</div>
				</a>
				<a href="#">
					<div id="delcat" onClick="show_del_cat()">
					</div>
				</a>
				<a href="#">
					<div id="deltask" onClick="show_del_task()">
					</div>
				</a>
			</div>
            <div id="category">
				<?php 
					printCategories($user_id); 
				?>
            </div>
            
            <div id="task">
				
			</div>
            
            <div id="blanket" style="display:none;"></div>
            <div id="popUpDiv" style="display: none;">
                <div id="newcategory">
                    <div id="closecat">
                        <a href="#" onclick="popup('popUpDiv')" >CLOSE</a>
                    </div>
                    <div id="cattitle">ADD CATEGORY</div>
                    <div id="elcategory">
                        <form action="buat_kategori.php" method="post">
							<input type="hidden" name="pembuat" value= <?php echo '"'.$user_id.'"'?> >
                            <label>category name</label>
                            <input name="catname" placeholder="category name" autocomplete="off">
                            <label>assignee</label>
                            <input name="catass" placeholder="assignee" id="input_assignee" autocomplete="off" onkeyup="showResult(this.value)">
							<div id="hasil_autocomplete"></div>
							</br></br>
                            <input class= "submitreg" name="submit" type="submit" value="Submit">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <?php require_once("footer.php") ?>        
    </body>
</html>