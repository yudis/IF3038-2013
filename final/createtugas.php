<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
        <title>Todolist: Create Tugas</title>
        <link rel="stylesheet" type="text/css" href="default.css" />
        <link rel="stylesheet" type="text/css" href="mediaqueries.css" />
    </head>
    <script type="text/javascript">
	<?php
		$username = $_GET['uname'];
		$kategori = $_GET['cat'];
		if ($_SERVER['REQUEST_METHOD'] == 'POST'){
			$taskname = $_POST['namatask'];
			$deadline = $_POST['deadline'];
			$assignee = $_POST['assignee'];
			$tag = $_POST['tag'];
			
			echo "validate('".$taskname."','".$deadline."','".$assignee."','".$tag."');";		
		}		
		
		$assigneeArray = explode("~",$assignee);
		$tagArray = explode("~",$tag);
		
		$con=mysqli_connect("localhost","progin","progin","progin_405_13510029");
		if (mysqli_connect_errno($con))
		{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}
		
		$sql="SELECT task_id FROM task";
		$result = mysqli_query($con,$sql);
		$maxID = 0;
		$n = 0;
		$m = 0;
		
		while ($row = mysqli_fetch_array($result)) {
		if ((int)$row["task_id"] > $maxID) {
			$maxID = (int)$row["task_id"];
			}
		}
		$maxID++;
		
		
		$sql="SELECT tag_id FROM tag";
		$result = mysqli_query($con,$sql);
		$maxtagID = 0;
		
		while ($row = mysqli_fetch_array($result)) {
		if ((int)$row["tag_id"] > $maxtagID)
			$maxtagID = (int)$row["tag_id"];
		}
		$maxtagID++;
		
		$query = mysqli_query($con,"INSERT into task(task_id,task_name,status,deadline,assignee,task_category) VALUES('$maxID','$taskname',0,'$deadline','$assignee','$kategori')");
				
		$query = mysqli_query($con,"INSERT into task_creator(task_id,creator) VALUES('$maxID','$username')");
				
		$query = mysqli_query($con,"INSERT into task_incharge(task_id,people_incharge_task) VALUES('$maxID','$assignee')");
		
		while ($m < $assigneeArray.count()) {
			$sql="INSERT INTO task_incharge VALUES ('$maxID', '".$assigneeArray[$m]."')";
			mysqli_query($con,$sql);
			$m++;
		}
		
		while ($n < $tagArray.count()) {
			$sql="INSERT INTO tag VALUES ('$maxtagID', '".$tagArray[$n]."')";
			mysqli_query($con,$sql);
			$sql="INSERT INTO tasktag VALUES ('$maxID', '$maxtagID')";
			mysqli_query($con,$sql);
			$n++;
			$maxtagID++;
		}
		
		if(isset($_FILES['files'])){
			$attachment_id = (int)0;
			foreach($_FILES['files']['tmp_name'] as $key => $tmp_name ){
				$file_name = $key.$_FILES['files']['name'][$key];
				$file_size =$_FILES['files']['size'][$key];
				$file_tmp =$_FILES['files']['tmp_name'][$key];
				$file_type=$_FILES['files']['type'][$key];
							
				$desired_dir = "user_attachment";
						
				$attachment_id++;
				$tempnumb = $attachment_id;
						
				while (strlen($tempnumb)<3){
					$tempnumb = "0".$tempnumb;
				}
						
				$temp = "A-".$tempnumb;
				$link = "$desired_dir/".$file_name;
				echo "alert('".$temp."');";

				$query = mysqli_query($con,"INSERT into attachment(attachment_id,task_id,link) VALUES('$temp','$$maxID','$link')");
						
				if(is_dir($desired_dir)==false){
					mkdir("$desired_dir", 0700);		// Create directory if it does not exist
				}
						
				if(is_dir("$desired_dir/".$file_name)==false){
					move_uploaded_file($file_tmp,"user_attachment/".$file_name);
				} else {	//rename the file if another one exist
					$new_dir="user_attachment/".$file_name.time();
					rename($file_tmp,$new_dir) ;				
				}
					//mysqli_query($query);					
			}		
			mysqli_close($con);
		}
	?>
	</script>
    <body onload="initializeCreateTugas()">
        <div class="page">
            <header class="content">
                <?php include 'header.php' ?>
            </header>
            <div class ="content">
                <h1>Buat Tugas Baru</h1>
                <div class="formtugas">
                    <form name="form" method="POST" enctype="multipart/form-data">
                        <ul class="item">
                            <li id="folil1">
                                <label id="title1">Nama task:</label>
                                <div>
                                    <input id="namatask" name="namatask" type="text" maxlength="25" tabindex="1" pattern="[A-Za-z0-9 ]{1,25}"
                                           title="Nama task tidak diperbolehkan menggunakan karakter spesial"/>
                                </div>
                            </li>

                            <li id="folil2">
                                <label id="title2">Attachment:</label>
                                <div>
                                    	<input type="file" name="files[]" multiple accept="application/pdf,application/msword,image/*"/>
                                </div>
                            </li>

                            <li id="folil3">
                                <label id="title3">Deadline:</label>
                                <div>
                                    <input id="deadline" name="deadline" type="date" tabindex="3"/>
                                </div>
                            </li>

                            <li id="folil4">
                                <label for="assignee">Assignee</label>:<br />
								<input id="assignee" name="assignee" class="inputbox" type="text" placeholder="Type username here.." pattern="[A-Za-z0-9-, ]{1,}"  list="listuser" />
								<div id="datalistuser">
								</div>
							</li>

                            <li id="folil5">
                                <label id="title5">Tag:</label>
                                <div>
                                    <input id="tag" name="tag" type="text" tabindex="5" pattern="[A-Za-z0-9-, ]{1,}"/>
                                </div>
                            </li>

                            <li id="btn">
                                <input type="submit" value="Submit" class="button"/>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.</br>
                IF3094 - Pemrograman Internet.
            </footer>
        </div>

    </body>
 	<script src="scripts/formtugas.js" type="application/javascript"></script>
    
</html>