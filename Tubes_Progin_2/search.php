<?php 
	require "config.php";
	$text = $_POST['Search'];
	$tipefilter = $_POST['namafilter'];
	
	if ( $tipefilter == "all result")
	{
		$sql = "SELECT task.name,tag.name as nama_tag FROM (task LEFT OUTER JOIN tasktag ON task.id_task = tasktag.id_task) LEFT OUTER JOIN tag ON task.id_tag = tag.id_tag";
		$user = mysqli_query($con,$sql);
		while (($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hasiltask[]=$current_user['name'];
		}
		
		for($i=0; $i<count($hasiltask); $i++)
		{
			echo $hasiltask[$i]."<br>";
		}
	}
	else if ($tipefilter == "username")
	{
	}
	else if ($tipefilter == "category")
	{
	}
	else if ($tipefilter == "username")
	{
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