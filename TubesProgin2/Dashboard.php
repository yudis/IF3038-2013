<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<?php

// Inialize session
session_start();

// Check, if username session is NOT set then this page will jump to login page
if(!isset($_COOKIE['UserLogin'])){
    header('Location: index.php');
}

?>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css" media="screen" />
    </head>
    <body>
        <header>
            <a href="dashboard.php" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
            <div id="dashboard"><a title="Go to Dashboard" href="dashboard.php">Dashboard</a></div>
            <div id="profile"><a title="Go to Profile" href="profile.php">My Profile</a></div>
            <div id="logout"><a title="Log out from here" href="logout.php">Log Out</a></div>
            <form id="search">
                <input type="text" name="Search" id="box">
                <input type="submit" value="Search">
            </form>
        </header>

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
        
            <?php
                include 'addtask.php';
            ?>  
        
        <div id='add'>
            Category Name:<br/> <input type='text' id='cate'><br/>
            User:<br/> <input type='text'><br/>
            <input type="submit" onclick="addCat();" value="create">
            <input type="submit" onclick="restore();" value="cancel">
        </div>
     </body>
    <script type="text/javascript" src="script.js"></script>
</html>
