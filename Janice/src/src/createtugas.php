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
    /*if ($_SERVER['REQUEST_METHOD'] == 'POST'){
		$taskname = $_POST['namatask'];
		$deadline = $_POST['deadline'];
		$assignee = $_POST['assignee'];
		$tag = $_POST['tag'];
		
		/*echo "validate('".$taskname."','".$deadline."','".$assignee."','".$tag."');";
		*/
		
	//}
		
		if(isset($_FILES['files'])){
			$attachment_id = (int)0;
			foreach($_FILES['files']['tmp_name'] as $key => $tmp_name ){
					$file_name = $key.$_FILES['files']['name'][$key];
					$file_size =$_FILES['files']['size'][$key];
					$file_tmp =$_FILES['files']['tmp_name'][$key];
					$file_type=$_FILES['files']['type'][$key];
					
					$con=mysqli_connect("localhost","progin","progin","progin_405_13510035");
					if (mysqli_connect_errno($con))
					  {
					  	echo "Failed to connect to MySQL: " . mysqli_connect_error();
					  }
						
					$desired_dir = "user_attachment";
					
					$attachment_id++;
					$tempnumb = $attachment_id;
					
					while (strlen($tempnumb)<3){
						$tempnumb = "0".$tempnumb;
					}
					
					$temp = "A-".$tempnumb;
					$temp2 = "T-".$tempnumb;
					$link = "$desired_dir/".$file_name;
					echo "alert('".$temp."');";
					$query = mysqli_query($con,"INSERT into attachment(attachment_id,task_id,link) VALUES('$temp','$temp2','$link')");
					
					if(is_dir($desired_dir)==false){
						mkdir("$desired_dir", 0700);		// Create directory if it does not exist
					}
					
					if(is_dir("$desired_dir/".$file_name)==false){
						move_uploaded_file($file_tmp,"user_attachment/".$file_name);
					}else{									//rename the file if another one exist
						$new_dir="user_attachment/".$file_name.time();
						rename($file_tmp,$new_dir) ;				
					}
					//mysqli_query($query);
					
				}
			
			mysqli_close($con);
	}

	?>
	</script>
    <body>
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="dashboard.html"><img alt="Home" src="images/logo.png" /></a></div>
                    <ul>
                        <li><div><a href="dashboard.html">Dashboard</a></div></li><li><div><a href="profile.html">Profile</a></div></li><li><div><a href="index.html">Logout</a></div></li>
                    </ul>
                    <div class="search">
                        <div id="searchwrapper">
                            <form action="#">
                                <input type="text" class="searchbox" name="q" value="" placeholder="Enter task name here.." />
                                <input type="image" src="images/search.png" name="sumbit" class="searchbox_submit" alt="search..."/>
                            </form>
                        </div>
                    </div>
                </nav>
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
                                <label id="title4">Assignee:</label>
                                <div>
                                    <input id="assignee" name="assignee" type="text" tabindex="4" pattern="[A-Za-z0-9 ]{1,}"  list="user" />
                                    <datalist id="user">
                                        <option value="Abraham Krisnanda Santoso">
                                        <option value="Edward Samuel Pasaribu">
                                        <option value="Stefanus Thobi Sinaga">
                                    </datalist>
                                </div>
                            </li>

                            <li id="folil5">
                                <label id="title5">Tag:</label>
                                <div>
                                    <input id="tag" name="tag" type="text" tabindex="5" pattern="[A-Za-z0-9 ]{1,}"/>
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