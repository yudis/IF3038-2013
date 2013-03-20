<?php 
include 'header.php';

$result=mysqli_query($con,"SELECT DISTINCT `category` FROM `tasks`");
$cats=mysqli_num_rows($result);
?>
			
<div id="main2">
    <div id ="dashboardcontainer">
        <div id ="staticcomponentlist">
            <div class ="letterpanel">
            <a href="#login_form" id="login_pop">Kategori</a>
            </div>
            <!-- popup form #1 jadi ceritanya awalnya gk keliatan -->
            <a href="#x" class="overlay" id="login_form"></a>
            <div class="popup">
				<form name="add_category" method="post" action="category.php">
					<h2>Tambah Kategori Tugas</h2>
					<!--<h2>Welcome Guest!</h2>
					<p>Please enter your login and password here</p>-->
					<div>
						<label>Nama Kategori &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: </label>
						<input type ="text" name="category_name"></input>
					</div>
					<div>
						<label for="password">Pengguna Terkait :</label>
						<input type ="text" name="relateduser"></input>
					</div>
					<input type="submit" name="btn_addCat" value="Ok" />
				</form>

				<a class="close" href="#close"></a>
            </div>
            <?php                                    
			$id = $_SESSION['id'];
			$result1=mysqli_query($con,"SELECT * FROM `categories` ORDER BY id");
			while ($cat=mysqli_fetch_array($result1)) {
				$cat_id=$cat['id'];
				$result2=mysqli_query($con,"SELECT * FROM `tasks` WHERE category=$cat_id");
				while ($task=mysqli_fetch_array($result2)) {
					$task_id=$task['id'];
					$result3=mysqli_query($con,"SELECT * FROM `assignees` WHERE member=$id AND task=$task_id");
					$count=mysqli_num_rows($result3);
					if ($count == 1) {
            ?>
            <!--<a href ="rinciantugas.php">
                <img onmouseover="javascript:getDashboardFocus('task1');" src ="images/Prog_In.png" id="task1" alt="task" style="cursor:pointer" />
            </a>
            <a href ="post.php"><input id ="newtask" type="button" name="Tugas Baru" value="newtask" disabled="true"/></a>
            <img onmouseover="javascript:getDashboardFocus('task2');" src ="images/dateschedule.png" id="task2" alt="task2" style="cursor:pointer" />
            <a href ="post.php"><input id ="newtask" type="button" name="Tugas Baru" value="newtask" disabled="true"/></a>-->
            <div onclick="javascript:showtask(<?php echo $cat['id'];?>,<?php echo $cats;?>);"><a href="#"><?php echo $cat['name'];?></a></div>
            <a href ="post.php?id=<?php echo $cat['id'];?>"><input id ="newtask" type="button" name="Tugas Baru" value="New Task"/></a>
            <?php
            			break;
        			}
        		}
			}
            ?>
        </div>
        <div id="rincian">
			<?php
			for ($idc=1; $idc<=$cats; $idc++) {
				echo "<div id='".$idc."'>";
				$result3=mysqli_query($con,"SELECT * FROM `tasks` WHERE category='$idc'");
				while ($task=mysqli_fetch_array($result3)) {
					$task_id = $task['id'];
					$result4=mysqli_query($con,"SELECT * FROM `assignees` WHERE task=$task_id AND member=$id");
					if (mysqli_num_rows($result4) == 1) {
			?>
			<a href="rinciantugas.php?id=<?php echo $task['id'];?>"><?php echo $task['name']?></a><br />
			<?php
					}
				}
				echo "<br /></div>";
			}
			?>
            <!--end of pop up-->
            <!--<div class="atas"></div>
			<div class="tengah"><img src ="images/ProginBig.png" alt="task" />
			<img src ="images/DateBig.png" alt="tasklv" /></div>
			<div class="bawah"></div>-->
		</div>
	</div>
	
<?php include 'footer.php';?>
