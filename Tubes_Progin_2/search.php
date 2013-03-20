<?php 
	require "config.php";
	$text = $_POST['Search'];
	$tipefilter = $_POST['namafilter'];
	
	if ( $tipefilter == "all result")
	{
		$sql = "SELECT DISTINCT task.name FROM (task LEFT OUTER JOIN tasktag ON task.id_task = tasktag.id_task) LEFT OUTER JOIN tag ON tasktag.id_tag = tag.id_tag LEFT OUTER JOIN comment ON task.id_task = comment.id_task WHERE task.name LIKE '%$text%' or tag.name LIKE '%$text%' or comment.content LIKE '$text%'";
		
		$user = mysqli_query($con,$sql);
		$hasiltask = array();
		while (($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hasiltask[]=$current_user['name'];
		}
		
		$sql = "SELECT DISTINCT username FROM user WHERE username LIKE '%$text%' or fullname LIKE '%$text%' or birthday LIKE '%$text%' or email LIKE '%$text%'";
		$user = mysqli_query($con,$sql);
		$hasiluser = array();
		while(($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hasiluser[] = $current_user['username'];
		}
		
		$sql = "SELECT DISTINCT name FROM category WHERE name LIKE '%$text%'";
		$user = mysqli_query($con,$sql);
		$hasilcategory = array();
		while(($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hasilcategory[] = $current_user['name'];
		}
	}
	else if ($tipefilter == "username")
	{
		$sql = "SELECT DISTINCT username FROM user WHERE username LIKE '%$text%' or fullname LIKE '%$text%' or birthday LIKE '%$text%' or email LIKE '%$text%'";
		$user = mysqli_query($con,$sql);
		$hasiluser = array();
		while(($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hasiluser[] = $current_user['username'];
		}
	}
	else if ($tipefilter == "category")
	{
		$sql = "SELECT DISTINCT name FROM category WHERE name LIKE '%$text%'";
		$user = mysqli_query($con,$sql);
		$hasilcategory = array();
		while(($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hasilcategory[] = $current_user['name'];
		}
	}
	else if ($tipefilter == "task")
	{
		$sql = "SELECT DISTINCT task.name FROM (task LEFT OUTER JOIN tasktag ON task.id_task = tasktag.id_task) LEFT OUTER JOIN tag ON tasktag.id_tag = tag.id_tag LEFT OUTER JOIN comment ON task.id_task = comment.id_task WHERE task.name LIKE '%$text%' or tag.name LIKE '%$text%' or comment.content LIKE '$text%'";
		
		$user = mysqli_query($con,$sql);
		$hasiltask = array();
		while (($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hasiltask[]=$current_user['name'];
		}
	}
?>

<!DOCTYPE html>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
		
    </head>
    <body>
		<?php
        include "header.php";
		?>
            <div id="category">
                <div class="kategori" onclick="showList();"><a>Fraud</a></div>
                <div class="kategori" onclick="showList3();"><a>Robbery</a></div>
                <div class="kategori" onclick="showList2();"><a>Gambling</a></div>
                <div class="kategori" onclick="showList();"><a>Public Drunkenness</a></div>
                <div class="kategori" onclick="showList3();"><a>Drug Law Violation</a></div>
                <div class="kategori" onclick="showList2();"><a>Motor Vehicle Theft</a></div>
            </div>
        <div id="addCat">
            <a onclick="addCategory();">+ category</a>
        </div>
        
        <div id ="listtugas" class="list">
            <a class="listTugas" onclick="showRinci();"></a> 
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a onclick='showBuat()' class='addTask'></a>
        </div>
        <div id ="listtugas2" class="list">
            <a class="listTugas" onclick="showRinci();"></a> 
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a onclick='showBuat()' class='addTask'></a>
        </div>
        <div id ="listtugas3" class="list">
            <a class="listTugas" onclick="showRinci();"></a> 
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a class="listTugas" onclick="showRinci();"></a>
            <a onclick="showBuat()" class="addTask"></a>
        </div>
        
        <div class="tugas" id="rincitugas">
                Name: Nama Tugas <br/>
                Attachment: 
                    <div class="attachment">
                        <a href="img/file.zip">file.zip</a><br/>
                        <a href="img/badge.png">picture.png</a><br/>
                    </div><br/>
                Deadline: 17-12-2014<br/>
                Assignee: <a href="" class="asignee">Timo</a>, <a href="" class="asignee">Stefan</a>, <a href="" class="asignee">Frilla</a><br/>
                Tag: <a href="" class="tag">dangerous</a>, <a href="" class="tag">novice</a> <br/>
                <br/>Comment:<br/>
                <div class="komentar">Dangerous criminal. Proceed with caution.</div><br/>
                <form>
                    <textArea></textarea>
                    <input type="button" name="submit" value="submit">
                </form>
                <br/><br/>
                <a onclick="showEdit();" class="button">edit</a><br/>
            </div>
            
            <datalist id="assignee">
                <option value="Frilla" />
                <option value="Stefan" />
                <option value="Timo" />
                <option value="Yosef" />
                <option value="Hasby" />
            </datalist>
        
            <div class="tugas" id ="edittugas">
                <form>
                    Name: Nama Task<br/>
                    Attachment: <div class="attachment"><input id="upload" type="file"></div><br/>
                    Deadline: <input type="date"><br/>
                    Assignee: <div class="assignee"><input type="text" list="assignee"></div><br/>
                    Tag: <div class="tag"> <input type="text"></div> <br/>
                    Comment: <br/>
                <div class="komentar">Dangerous criminal. Proceed with caution.</div><br/>
                </form> <br/>
                <a onclick="showRinci()" class="button">save</a><br/>
            </div>
        
			<div id="wanted">
			<img src="img/kertas2.png">
			</div>
        
            <div class="tugas" id="buattugas">
                <br/>
                Name: <div class="nama"><input type="text" id="namaTask"></div><br/>
                Attachment: <div class="attachment"><input type="file"></div><br/>
                Deadline: <div class="deadline"><input type="date"></div><br/>
                Assignee: <div class="asignee"><input type="text"></div><br/>
                Tag: <div class="tag"> <input type="text"></div> <br/>
                <br/>
                <a onclick="createTask();" class="button">create</a><br/>
            </div>
        
        <div id='add'>
            Category Name:<br/> <input type='text' id='cate'><br/>
            User:<br/> <input type='text'><br/>
            <input type="submit" onclick="addCat();" value="create">
            <input type="submit" onclick="restore();" value="cancel">
        </div>
    </body>
</html>