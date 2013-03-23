<?php 
include 'header.php';

$result=mysqli_query($con,"SELECT DISTINCT `category` FROM `tasks`");
$cats=mysqli_num_rows($result);   

$id = $_SESSION['id'];
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
					<input type="hidden" name="creator_id" value="<?php echo $id;?>" />
					<input type="submit" name="btn_addCat" value="Ok" />
				</form>

				<a class="close" href="#close"></a>
            </div>
            <?php
			$result1=mysqli_query($con,"SELECT * FROM `categories` ORDER BY id");
			while ($cat=mysqli_fetch_array($result1)) {
				$found = false;
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
            <div onclick="javascript:gettask(<?php echo $_SESSION['id'];?>,<?php echo $cat['id'];?>);"><a href="#"><?php echo $cat['name'];?></a></div>
            <?php
			            $result3 = mysqli_query($con, "SELECT * FROM editors WHERE member=$id AND category=$cat_id");
			        	$count = mysqli_num_rows($result3);
			        	if ($count == 1) {
        	?>
            <a href ="post.php?id=<?php echo $cat['id'];?>"><input id ="newtask" type="button" name="Tugas Baru" value="New Task"/></a>
            <?php
            				if ($cat['creator'] == $id) 
            				{
            ?>
            <form action="deletecategory.php" method="post">
            	<input type="hidden" name="id" value="<?php echo $cat['id'];?>" />
            	<input type="submit" value="Delete Category" />
            </form>
            <?php
            				}
        				}
            			$found = true;
            			break;
        			}
        		}
        		if ($found == false) {
        			$result3 = mysqli_query($con, "SELECT * FROM editors WHERE member=$id AND category=$cat_id");
        			$count = mysqli_num_rows($result3);
        			if ($count == 1) {
        	?>
        	<div onclick="javascript:gettask(<?php echo $_SESSION['id'];?>,<?php echo $cat['id'];?>);"><a href="#"><?php echo $cat['name'];?></a></div>
            <a href ="post.php?id=<?php echo $cat['id'];?>"><input id ="newtask" type="button" name="Tugas Baru" value="New Task"/></a><br />
            <?php
            			if ($cat['creator'] == $id) 
            			{
            ?>
            <form action="deletecategory.php" method="post">
            	<input type="hidden" name="id" value="<?php echo $cat['id'];?>" />
            	<input type="submit" value="Delete Category" />
            </form>
            <?php
            			}
        			}
        		}
        		echo "<br />";
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
						$assignee=mysqli_fetch_array($result4);
			?>
			<a href="rinciantugas.php?id=<?php echo $task['id'];?>"><?php echo $task['name']?></a><br />
			Deadline: <strong><?php echo $task['deadline'];?></strong><br />
			<?php
						$res = mysqli_query($con,"SELECT * FROM tags WHERE tagged=$task_id");
						$count_tag = 0;
						while ($tagged = mysqli_fetch_array($res)) {
							$tag[$count_tag] = $tagged['name'];
							$count_tag++;
						}
			?>
			Tag: <strong>
			<?php
						for ($i = 0; $i < $count_tag; $i++) {
							echo $tag[$i];
							if ($i < $count_tag - 1) echo ",";
						}
			?>
			</strong>
			<br />
			Status : <strong><?php if ($assignee['finished'] == 1) echo 'Selesai'; else echo 'Belum selesai';?></strong><br />
			<?php
						if ($task['creator'] == $id) {
			?>
			<form action="deletetask.php" method="post">
				<input type="hidden" name="deltask" value="<?php echo $task_id?>" />
				<input type="submit" name="submit" value="Delete" />
			</form>
			<?php
						}
					}
					echo "<br />";
				}
				echo "</div>";
			}
			?>
			
			
			<!--coba checkbox-->
			<?php

  			 // if(isset($_POST['BtnSubmit']))
   			// {
			   //    echo "Notif : {$_POST['YourChoice']}</br>";
			   //    echo "<hr>";
			   // }
			
			?>
			
			<!--<form name="checkbox" method="POST" action="#">
			      <input name="YourChoice" type="checkbox" value="selesai" <?php if($_POST['YourChoice']=="selesai") echo "checked=checked"; ?> > Selesai
			      <input name="YourChoice" type="checkbox" value="belum selesai" <?php if($_POST['YourChoice']=="belum selesai") echo "checked=checked"; ?> > Belum selesai
			      <br/><br/>
			      <input name="BtnSubmit" type="submit" value="Submit">
			</form>-->
            <!--end of pop up-->
            
		</div>
	</div>
	
<?php include 'footer.php';?>
